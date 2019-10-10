package textmining;

import github.Repository;

public class MinedCommitRepository extends Repository<MinedCommit> {

    protected MinedCommitRepository(String databaseName) {
        super(databaseName);
    }
}