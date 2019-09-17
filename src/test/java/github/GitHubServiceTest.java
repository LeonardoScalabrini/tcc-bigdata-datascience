package github;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import util.FileUtil;
import util.ServerUtil;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GitHubServiceTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(ServerUtil.PORT);

    private ServerUtil serverUtil = new ServerUtil();

    private GitHubService gitHubService = new GitHubService();

    @After
    public void after(){
        wireMockRule.stop();
    }

    @Test
    public void deveRetornarCommits() throws IOException {
        String response = FileUtil.loadJson("commits.json");
        serverUtil.commits(HttpStatus.SC_OK, response);

        GitHubConfig gitHubConfig = new GitHubConfig("username", "password", "http://localhost:8888/github");
        String httpResponse = gitHubService.commits(gitHubConfig, "master");
        assertEquals(response, httpResponse);
    }

    @Test
    public void deveRetornarIssues() throws IOException {
        String response = FileUtil.loadJson("issues.json");
        serverUtil.issues(HttpStatus.SC_OK, response);

        GitHubConfig gitHubConfig = new GitHubConfig("username", "password", "http://localhost:8888/github");
        String httpResponse = gitHubService.issues(gitHubConfig, 1, 100);
        assertEquals(response, httpResponse);
    }

    @Test
    public void deveRetornarCommit() throws IOException {
        String response = FileUtil.loadJson("commit.json");
        serverUtil.commit(HttpStatus.SC_OK, response);

        GitHubConfig gitHubConfig = new GitHubConfig("username", "password", "http://localhost:8888/github");
        String httpResponse = gitHubService.commit(gitHubConfig, "019abc9980b9855bf04086c44b68f3282119c6b3");
        assertEquals(response, httpResponse);
    }
}