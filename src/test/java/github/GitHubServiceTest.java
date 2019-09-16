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
    public void deveAutenticarGitHub() throws IOException {
        String response = FileUtil.loadJson("commits.json");
        serverUtil.commits(HttpStatus.SC_OK, response);
        String httpResponse = gitHubService.commits();
        assertEquals(response, httpResponse);
    }
}