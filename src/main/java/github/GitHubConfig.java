package github;

public class GitHubConfig {

    public final String username;
    public final String password;
    public final String repository;

    public GitHubConfig(String username, String password, String repository) {
        this.username = username;
        this.password = password;
        this.repository = repository;
    }
}
