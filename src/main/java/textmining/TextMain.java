package textmining;

import github.Commit;
import github.CommitRepository;

import java.util.List;

public class TextMain {

    private static final String JAVA = ".java";
    private static final String ERRO = "ERRO";
    private static final String TWO_POINT = " : ";
    private static FindIssue findIssue = new FindIssue();
    private static GeneratePrevious generatePrevious = new GeneratePrevious();
    private static ExtractChange extractChange = new ExtractChange();
    private static PipelineTextMining pipelineTextMining = new PipelineTextMining();

    public static void main(String[] args) {
        //mining("spring-boot");
        //mining("guava");
        mining("okhttp");
        //mining("elasticsearch");
    }

    private static void mining(String database){

        CommitRepository commitRepository = new CommitRepository(database);
        List<Commit> commits = commitRepository.findMany(Commit.class);

        commits.forEach(commit -> {
            String issue = findIssue.find(commit);
            if (issue != null)
                commit.getFiles().forEach(file -> {
                    if (file.getFilename().endsWith(JAVA) && file.getRaw() != null){

                        try {
                            String previous = generatePrevious.generate(file.getPatch(), file.getRaw());
                            System.out.println(database + TWO_POINT + issue + TWO_POINT + pipelineTextMining.mine(extractChange.extract(previous, file.getRaw(), file.getPatch())));
                        }catch (Exception e){
                            System.out.println(ERRO + TWO_POINT + database + TWO_POINT + issue + TWO_POINT + e.getMessage());
                        }

                    }
                });
        });
    }
}
