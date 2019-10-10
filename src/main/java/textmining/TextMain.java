package textmining;

import github.Commit;
import github.CommitRepository;

import java.util.List;

public class TextMain {

    private static final String JAVA = ".java";
    private static final String ERRO = "ERRO";
    private static final String TWO_POINT = " : ";
    private static final Integer PAGE_SIZE = 10;
    private static final FindIssue findIssue = new FindIssue();
    private static final GeneratePrevious generatePrevious = new GeneratePrevious();
    private static final ExtractChange extractChange = new ExtractChange();
    private static final PipelineTextMining pipelineTextMining = new PipelineTextMining();

    public static void main(String[] args) {
        new Thread(() -> mining("elasticsearch")).start();
        new Thread(() -> mining("spring-boot")).start();
        new Thread(() -> mining("guava")).start();
        new Thread(() -> mining("okhttp")).start();
    }

    private static void mining(String database){
        MinedCommitRepository minedCommitRepository = new MinedCommitRepository(database);
        Integer pageNum = 1;
        CommitRepository commitRepository = new CommitRepository(database);

        while (true){
            List<Commit> commits = commitRepository.findPagination(Commit.class, PAGE_SIZE, pageNum);

            if (commits.isEmpty())
                break;

            commits.forEach(commit -> {
                String issue = findIssue.find(commit);
                if (issue != null)
                    commit.getFiles().forEach(file -> {
                        if (file.getFilename().endsWith(JAVA) && file.getRaw() != null){

                            try {
                                String previous = generatePrevious.generate(file.getPatch(), file.getRaw());
                                List<String> words = pipelineTextMining.mine(extractChange.extract(previous, file.getRaw(), file.getPatch()));
                                MinedCommit minedCommit = new MinedCommit();
                                minedCommit.setIssue(issue);
                                minedCommit.setWords(words);
                                minedCommitRepository.save(minedCommit);
                                System.out.println(database + TWO_POINT + issue + TWO_POINT + commit.getSha());
                            }catch (Exception e){
                                System.out.println(ERRO + TWO_POINT + database + TWO_POINT + issue + TWO_POINT + e.getMessage());
                            }

                        }
                    });
            });
            pageNum++;
        }

    }
}