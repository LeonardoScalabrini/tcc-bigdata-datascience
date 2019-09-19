package github;

import java.io.IOException;

public class GitHubMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        GitHubService gitHubService = new GitHubService();
        HttpUtil httpUtil = new HttpUtil();
        JsonConverter jsonConverter = new JsonConverter();
        IssueRepository issueRepository = new IssueRepository("develop");
        CommitRepository commitRepository = new CommitRepository("develop");
        GitHubMining gitHubMining = new GitHubMining(gitHubService, httpUtil, jsonConverter, issueRepository, commitRepository);

        String username = "";
        String password = "";
        String repository = "https://api.github.com/repos/iluwatar/java-design-patterns";

        GitHubConfig gitHubConfig = new GitHubConfig(username, password, repository);
        gitHubMining.mine(gitHubConfig);
    }
}
