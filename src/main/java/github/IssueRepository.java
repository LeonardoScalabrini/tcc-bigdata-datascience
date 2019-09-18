package github;

public class IssueRepository extends Repository<Issue> {

    public IssueRepository(String databaseName) {
        super(databaseName);
    }
}
