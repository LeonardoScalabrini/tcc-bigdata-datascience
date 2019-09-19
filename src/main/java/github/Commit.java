package github;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;
import java.util.Objects;

@JsonAdapter(CommitDeserializer.class)
public class Commit {

    private String project;

    private String sha;

    private String message;

    private List<Parent> parents;

    private List<File> files;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return Objects.equals(sha, commit.sha) &&
                Objects.equals(message, commit.message) &&
                Objects.equals(parents, commit.parents) &&
                Objects.equals(files, commit.files);
    }
}
