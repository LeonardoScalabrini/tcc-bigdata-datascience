package github;

import org.junit.Test;
import util.FileUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IssueTest {

    private JsonConverter jsonConverter = new JsonConverter();

    @Test
    public void deveSerializarIssue(){
        String json = FileUtil.loadJson("issues.json");
        List<Issue> issues = jsonConverter.fromJsons(json, Issue[].class);
        assertEquals(new Integer(921), issues.get(0).getNumber());
    }

    @Test
    public void deveSerializarIssueWithPullRequest(){
        String json = FileUtil.loadJson("issue_with_pull_request.json");
        Issue issue = jsonConverter.fromJson(json, Issue.class);
        assertEquals(new Integer(921), issue.getNumber());
        assertEquals("https://api.github.com/repos/iluwatar/java-design-patterns/pulls/921", issue.getPull_request().getUrl());
    }

}