package github;

import java.util.Objects;

public class Issue {

    private String project;

    private Integer number;

    private PullRequest pull_request;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(project, issue.project) &&
                Objects.equals(number, issue.number) &&
                Objects.equals(pull_request, issue.pull_request);
    }
}
