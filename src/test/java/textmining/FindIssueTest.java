package textmining;

import github.Commit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FindIssueTest {

    private FindIssue findIssue = new FindIssue();

    @Test
    public void deveEncontrarIssueNoMeioDaMensagem(){
        Commit commit = new Commit();
        commit.setMessage("MESSAGE MESSAGE #123 MESSAGE");
        String issue = findIssue.find(commit);

        assertEquals("123", issue);
    }

    @Test
    public void deveEncontrarIssueNoInicioDaMensagem(){
        Commit commit = new Commit();
        commit.setMessage("#123 MESSAGE MESSAGE MESSAGE");
        String issue = findIssue.find(commit);

        assertEquals("123", issue);
    }

    @Test
    public void deveEncontrarIssueNoFimDaMensagem(){
        Commit commit = new Commit();
        commit.setMessage("MESSAGE MESSAGE MESSAGE #123");
        String issue = findIssue.find(commit);

        assertEquals("123", issue);
    }

    @Test
    public void deveEncontrarIssueApenasComIssueNaMensagem(){
        Commit commit = new Commit();
        commit.setMessage("#123");
        String issue = findIssue.find(commit);

        assertEquals("123", issue);
    }

    @Test
    public void naoDeveEncontrarIssueComMensagemSemIssue(){
        Commit commit = new Commit();
        commit.setMessage("MESSAGE MESSAGE MESSAGE");
        String issue = findIssue.find(commit);

        assertNull(issue);
    }

    @Test
    public void naoDeveEncontrarIssueComMensagemComHashtag(){
        Commit commit = new Commit();
        commit.setMessage("#");
        String issue = findIssue.find(commit);

        assertNull(issue);
    }

    @Test
    public void naoDeveEncontrarIssueComIssueSemHashtag(){
        Commit commit = new Commit();
        commit.setMessage("123");
        String issue = findIssue.find(commit);

        assertNull(issue);
    }

    @Test
    public void naoDeveEncontrarIssueComMenssagemVazia(){
        Commit commit = new Commit();
        commit.setMessage("");
        String issue = findIssue.find(commit);

        assertNull(issue);
    }

    @Test
    public void naoDeveEncontrarIssueComMenssagemNula(){
        Commit commit = new Commit();
        commit.setMessage(null);
        String issue = findIssue.find(commit);

        assertNull(issue);
    }
}