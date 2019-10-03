package github;

public class GitHubConfig {

    public final String username;
    public final String password;
    public final String repository;
    public final String raw;

    public GitHubConfig(String username, String password, String repository, String raw) {
        this.username = username;
        this.password = password;
        this.repository = repository;
        this.raw = raw;
    }
}
