package textmining;

import de.flapdoodle.embed.mongo.MongodExecutable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.MongoUtil;

import java.io.IOException;
import java.util.Arrays;

public class MinedCommitRepositoryTest {

    private final MinedCommitRepository minedCommitRepository = new MinedCommitRepository("test");

    private MongodExecutable mongodExecutable = MongoUtil.create();

    @After
    public void clean() {
        mongodExecutable.stop();
    }

    @Before
    public void init() throws IOException {
        mongodExecutable.start();
        minedCommitRepository.removeAll(MinedCommit.class);
    }

    @Test
    public void deveSalvarCommitMinerado(){
        MinedCommit minedCommit = new MinedCommit();
        minedCommit.setIssue("ISSUE");
        minedCommit.setWords(Arrays.asList("WORD 1", "WORD 2"));
        minedCommitRepository.save(minedCommit);

        MinedCommit minedCommitDB = minedCommitRepository.findOne(MinedCommit.class);

        Assert.assertEquals(minedCommitDB.getIssue(), minedCommit.getIssue());
        Assert.assertEquals(minedCommitDB.getWords(), minedCommit.getWords());
    }

}