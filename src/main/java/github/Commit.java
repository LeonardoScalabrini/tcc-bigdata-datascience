package github;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;

@JsonAdapter(CommitDeserializer.class)
public class Commit {

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
}
