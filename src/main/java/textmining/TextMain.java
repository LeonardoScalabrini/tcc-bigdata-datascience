package textmining;

import github.Commit;
import github.CommitRepository;

import java.util.List;

public class TextMain {

    private static final String JAVA = ".java";
    private static FindIssue findIssue = new FindIssue();
    private static GeneratePrevious generatePrevious = new GeneratePrevious();
    private static ExtractChange extractChange = new ExtractChange();
    private static PipelineTextMining pipelineTextMining = new PipelineTextMining();

    public static void main(String[] args) {
        //new Thread(() -> mining("spring-boot")).start();
        new Thread(() -> mining("guava")).start();
        //new Thread(() -> mining("okhttp")).start();
        //new Thread(() -> mining("elasticsearch")).start();
    }

    private static void mining(String database){
        CommitRepository commitRepository = new CommitRepository(database);
        List<Commit> commits = commitRepository.findMany(Commit.class);

        commits.forEach(commit -> {
            String issue = findIssue.find(commit);
            if (issue != null)
                commit.getFiles().forEach(file -> {
                    if (file.getFilename().endsWith(JAVA) && file.getRaw() != null){
                        String previous = generatePrevious.generate(file.getPatch(), file.getRaw());
                        extractChange.extract(previous, file.getRaw()).forEach(change -> {
                            System.out.println(database + " : " + issue + " : " + pipelineTextMining.mine(change));
                        });
                    }
                });
        });
    }
}
