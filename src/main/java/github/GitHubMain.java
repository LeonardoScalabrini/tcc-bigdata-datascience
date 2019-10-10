package github;

public class GitHubMain {

    public static void main(String[] args) {

        GitHubService gitHubService = new GitHubService();
        HttpUtil httpUtil = new HttpUtil();
        JsonConverter jsonConverter = new JsonConverter();

        String username = "";
        String password = "";

        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "elasticsearch", "https://api.github.com/repos/elastic/elasticsearch");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "spring-boot", "https://api.github.com/repos/spring-projects/spring-boot");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "okhttp", "https://api.github.com/repos/square/okhttp");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "guava", "https://api.github.com/repos/google/guava");

        new Thread(() -> createRaw(gitHubService, httpUtil, jsonConverter, username, password, "elasticsearch", "https://api.github.com/repos/elastic/elasticsearch", "https://raw.githubusercontent.com/elastic/elasticsearch")).start();
        new Thread(() -> createRaw(gitHubService, httpUtil, jsonConverter, username, password, "okhttp", "https://api.github.com/repos/square/okhttp", "https://raw.githubusercontent.com/square/okhttp")).start();
        new Thread(() -> createRaw(gitHubService, httpUtil, jsonConverter, username, password, "guava", "https://api.github.com/repos/google/guava", "https://raw.githubusercontent.com/google/guava")).start();
        new Thread(() -> createRaw(gitHubService, httpUtil, jsonConverter, username, password, "spring-boot", "https://api.github.com/repos/spring-projects/spring-boot", "https://raw.githubusercontent.com/spring-projects/spring-boot")).start();

        //TextMain.main(args);
    }

    private static void createMinining(GitHubService gitHubService, HttpUtil httpUtil, JsonConverter jsonConverter, String username, String password, String repository, String url) {
        IssueRepository issueRepository = new IssueRepository(repository);
        CommitRepository commitRepository = new CommitRepository(repository);
        GitHubMining gitHubMining = new GitHubMining(gitHubService, httpUtil, jsonConverter, issueRepository, commitRepository);

        GitHubConfig gitHubConfig = new GitHubConfig(username, password, url, null);
        gitHubMining.mine(gitHubConfig);
    }

    private static void createRaw(GitHubService gitHubService, HttpUtil httpUtil, JsonConverter jsonConverter, String username, String password, String repository, String url, String raw) {
        IssueRepository issueRepository = new IssueRepository(repository);
        CommitRepository commitRepository = new CommitRepository(repository);
        GitHubMining gitHubMining = new GitHubMining(gitHubService, httpUtil, jsonConverter, issueRepository, commitRepository);

        GitHubConfig gitHubConfig = new GitHubConfig(username, password, url, raw);
        gitHubMining.extractRawFiles(gitHubConfig);
    }
}
