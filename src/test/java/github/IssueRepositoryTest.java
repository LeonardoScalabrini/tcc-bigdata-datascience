package github;

import de.flapdoodle.embed.mongo.MongodExecutable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MongoUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IssueRepositoryTest {

    private IssueRepository issueRepository = new IssueRepository("test");

    private MongodExecutable mongodExecutable = MongoUtil.create();

    @Before
    public void init() throws IOException {
        mongodExecutable.start();
        issueRepository.removeAll(Issue.class);
    }

    @After
    public void after(){
        mongodExecutable.stop();
    }

    @Test
    public void deveSalvarIssue(){

        Issue issue = new Issue();
        issue.setNumber(100);

        issueRepository.save(issue);

        assertEquals(issue, issueRepository.findOne(Issue.class));
    }

    @Test
    public void deveBuscarTodos(){
        Issue issue = new Issue();
        issue.setNumber(100);

        Issue issue2 = new Issue();
        issue2.setNumber(90);

        issueRepository.save(issue);
        issueRepository.save(issue2);

        List<Issue> issues = issueRepository.findMany(Issue.class);

        assertEquals(issue, issues.get(0));
        assertEquals(issue2, issues.get(1));
    }

}