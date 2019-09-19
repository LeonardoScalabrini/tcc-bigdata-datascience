package github;

import de.flapdoodle.embed.mongo.MongodExecutable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MongoUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommitRepositoryTest {

    private CommitRepository commitRepository = new CommitRepository("test");

    private MongodExecutable mongodExecutable = MongoUtil.create();

    @After
    public void clean() {
        mongodExecutable.stop();
    }

    @Before
    public void init() throws IOException {
        mongodExecutable.start();
        commitRepository.removeAll(Commit.class);
    }

    @Test
    public void deveSalvarCommit(){
        Commit commit = new Commit();
        commit.setSha("SHA");
        commit.setProject("PROJECT");

        Parent parent = new Parent();
        parent.setSha("SHA");
        commit.setParents(Arrays.asList(parent));

        File file = new File();
        file.setPatch("PATCH");
        file.setFilename("FILENAME");
        commit.setFiles(Arrays.asList(file));

        commit.setMessage("MESSAGE");
        commitRepository.save(commit);

        Commit commitDB = commitRepository.findOne(Commit.class);
        assertEquals(commit, commitDB);
    }

    @Test
    public void deveBuscarTodos(){
        Commit commit1 = new Commit();
        commit1.setSha("SHA");
        commit1.setProject("PROJECT");

        Parent parent = new Parent();
        parent.setSha("SHA");
        commit1.setParents(Arrays.asList(parent));

        File file = new File();
        file.setPatch("PATCH");
        file.setFilename("FILENAME");
        commit1.setFiles(Arrays.asList(file));

        commit1.setMessage("MESSAGE");

        Commit commit2 = new Commit();
        commit2.setSha("SHA2");
        commit2.setProject("PROJECT2");

        Parent parent2 = new Parent();
        parent2.setSha("SHA2");
        commit2.setParents(Arrays.asList(parent2));

        File file2 = new File();
        file2.setPatch("PATCH2");
        file2.setFilename("FILENAME2");
        commit2.setFiles(Arrays.asList(file2));

        commit2.setMessage("MESSAGE2");

        commitRepository.save(commit1);
        commitRepository.save(commit2);

        List<Commit> commits = commitRepository.findMany(Commit.class);
        assertEquals(commit1, commits.get(0));
        assertEquals(commit2, commits.get(1));
    }

}