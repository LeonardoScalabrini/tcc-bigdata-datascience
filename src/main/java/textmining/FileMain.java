package textmining;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileMain {

    private static final int PAGE_SIZE = 10;
    private static final String SPACE = " ";
    private static final String BREAKE = "\n";

    public static void main(String... args) {
        new Thread(() -> writeFile("elasticsearch")).start();
        new Thread(() -> writeFile("spring-boot")).start();
        new Thread(() -> writeFile("okhttp")).start();
        new Thread(() -> writeFile("guava")).start();
    }

    private static void writeFile(String database) {
        MinedCommitRepository minedCommitRepository = new MinedCommitRepository(database);

        Integer pageNum = 1;
        BufferedWriter bufferedWriter = FileUtil.write(database + ".txt");
        BufferedWriter bufferedWriterIssue = FileUtil.write(database + "-issue.txt");
        while (true){
            List<MinedCommit> minedCommits = minedCommitRepository.findPagination(MinedCommit.class, PAGE_SIZE, pageNum);

            if (minedCommits.isEmpty())
                break;

            minedCommits.forEach(minedCommit -> {
                try {
                    bufferedWriter.write(String.join(SPACE, minedCommit.getWords()) + BREAKE);
                    bufferedWriterIssue.write(minedCommit.getIssue() + BREAKE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            pageNum++;
        }
        try {
            bufferedWriter.close();
            bufferedWriterIssue.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
