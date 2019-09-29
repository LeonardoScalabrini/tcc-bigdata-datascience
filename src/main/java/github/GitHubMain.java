package github;

import java.io.IOException;

public class GitHubMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        GitHubService gitHubService = new GitHubService();
        HttpUtil httpUtil = new HttpUtil();
        JsonConverter jsonConverter = new JsonConverter();

        String username = "";
        String password = "";

        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "elasticsearch", "https://api.github.com/repos/elastic/elasticsearch");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "spring-boot", "https://api.github.com/repos/spring-projects/spring-boot");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "okhttp", "https://api.github.com/repos/square/okhttp");
        createMinining(gitHubService, httpUtil, jsonConverter, username, password, "guava", "https://api.github.com/repos/google/guava");
    }

    private static void createMinining(GitHubService gitHubService, HttpUtil httpUtil, JsonConverter jsonConverter, String username, String password, String repository, String url) throws IOException, InterruptedException {
        IssueRepository issueRepository = new IssueRepository(repository);
        CommitRepository commitRepository = new CommitRepository(repository);
        GitHubMining gitHubMining = new GitHubMining(gitHubService, httpUtil, jsonConverter, issueRepository, commitRepository);

        GitHubConfig gitHubConfig = new GitHubConfig(username, password, url);
        gitHubMining.mine(gitHubConfig);
    }
}
