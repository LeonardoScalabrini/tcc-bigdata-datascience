package github;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GitHubMining {

    private static final String HEADER_LIMIT = "X-RateLimit-Remaining";
    private static final String HEADER_RESET = "X-RateLimit-Reset";
    private static final Integer PAGE_SIZE = 10;
    private static final String COMMIT = "COMMIT: ";
    private static final String JAVA = ".java";
    private static final String RAW = "RAW: ";
    private static final String PERSISTIDO_COMMIT = "PERSISTIDO COMMIT";
    private final GitHubService gitHubService;

    private final HttpUtil httpUtil;

    private final JsonConverter jsonConverter;

    private final IssueRepository issueRepository;

    private final CommitRepository commitRepository;

    public GitHubMining(GitHubService gitHubService, HttpUtil httpUtil, JsonConverter jsonConverter, IssueRepository issueRepository, CommitRepository commitRepository) {
        this.gitHubService = gitHubService;
        this.httpUtil = httpUtil;
        this.jsonConverter = jsonConverter;
        this.issueRepository = issueRepository;
        this.commitRepository = commitRepository;
    }

    public void mine(GitHubConfig gitHubConfig) {

        extractIssues(gitHubConfig);

        List<Commit> commits = findCommits(gitHubConfig);

        if(commits.isEmpty()){
            System.out.println("COMMITS VAZIO");
            return;
        }

        extractDetailCommits(gitHubConfig, commits);
    }

    private void extractDetailCommits(GitHubConfig gitHubConfig, List<Commit> commits) {
        System.out.println("INICIANDO COMMIT");
        Integer i = 0;
        while (true){

            System.out.println("COMMIT: " + i);
            HttpResponse httpResponse = null;
            try {
                httpResponse = gitHubService.commit(gitHubConfig, commits.get(i).getSha());

                if (refreshLimit(httpResponse)) continue;

                String json = httpUtil.fromHttpResponse(httpResponse);

                System.out.println("COMMIT: ");
                System.out.println(json);
                Commit commit = jsonConverter.fromJson(json, Commit.class);

                if(i == commits.size() - 1){
                    System.out.println("FIM COMMIT");
                    break;
                }

                commit.setProject(gitHubConfig.repository);
                commitRepository.save(commit);
                System.out.println("PERSISTIDO COMMIT");

                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Commit> findCommits(GitHubConfig gitHubConfig) {
        System.out.println("INICIANDO COMMITS");
        List<Commit> commits = new ArrayList<>();
        Integer i = 0;

        while (true){

            Integer page = i + 1;
            System.out.println("PAGINA: " + page);
            HttpResponse httpResponse = null;

            try {
                httpResponse = gitHubService.commits(gitHubConfig, "master", page, ((page) * 100));

                if (refreshLimit(httpResponse)) continue;

                List<Commit> newCommits = jsonConverter.fromJsons(httpUtil.fromHttpResponse(httpResponse), Commit[].class);

                if(newCommits.isEmpty()){
                    System.out.println("FIM COMMITS");
                    break;
                }

                commits.addAll(newCommits);

                System.out.println("PERSISTIDO COMMITS");

                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return commits;
    }

    private void extractIssues(GitHubConfig gitHubConfig) {
        System.out.println("INICIANDO ISSUE");
        Integer i = 0;
        while (true){

            Integer page = i + 1;
            System.out.println("PAGINA: " + page);
            HttpResponse httpResponse = null;

            try {
                httpResponse = gitHubService.issues(gitHubConfig, page, ((page) * 100));
                if (refreshLimit(httpResponse)) continue;

                List<Issue> issues = jsonConverter.fromJsons(httpUtil.fromHttpResponse(httpResponse), Issue[].class);

                if(issues.isEmpty()){
                    System.out.println("FIM ISSUE");
                    break;
                }

                for (Issue issue : issues) {
                    if (issue.getPull_request() != null)
                        continue;

                    issue.setProject(gitHubConfig.repository);
                    issueRepository.save(issue);
                }

                System.out.println("PERSISTIDO ISSUE");
                i++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void extractRawFiles(GitHubConfig gitHubConfig) {

        Integer pageNum = 1;

        while (true) {

            List<Commit> commits = commitRepository.findPagination(Commit.class, PAGE_SIZE, pageNum);

            if (commits.isEmpty())
                break;

            for (Commit commit : commits) {

                System.out.println(COMMIT + commit.getSha());
                HttpResponse httpResponse = null;
                try {

                    for (File file : commit.getFiles()) {

                        if (!file.getFilename().endsWith(JAVA))
                            continue;

                        if (StringUtils.isNotBlank(file.getRaw()))
                            continue;

                        httpResponse = gitHubService.raw(gitHubConfig, commit, file);

                        if (refreshLimit(httpResponse)) continue;

                        String raw = httpUtil.fromHttpResponse(httpResponse);

                        System.out.println(RAW);
                        file.setRaw(raw);
                        commitRepository.update(commit.getSha(), commit);
                        System.out.println(PERSISTIDO_COMMIT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            pageNum++;
        }
    }

    private boolean refreshLimit(HttpResponse httpResponse) throws InterruptedException {

        Integer rateLimit = Integer.valueOf(httpUtil.fromHeader(httpResponse, HEADER_LIMIT));

        System.out.println("RATE LIMIT: " + rateLimit);

        if (rateLimit != 0)
            return false;

        System.out.println("RATE LIMIT: " + rateLimit);

        Long reset = Long.valueOf(httpUtil.fromHeader(httpResponse, HEADER_RESET));
        Date future = new Date(reset * 1000);
        Date now = new Date();

        Long millis = future.getTime() - now.getTime();

        System.out.println("ESPERANDO ATE: " + future);
        Thread.sleep(millis);
        System.out.println("CONTINUANDO");

        return true;
    }
}
