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

}