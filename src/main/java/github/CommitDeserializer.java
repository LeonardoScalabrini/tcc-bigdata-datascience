package github;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommitDeserializer implements JsonDeserializer<Commit> {

    @Override
    public Commit deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        Commit commit = new Commit();

        if(jsonElement.getAsJsonObject().get("sha") != null)
            commit.setSha(jsonElement.getAsJsonObject().get("sha").getAsString());

        if(jsonElement.getAsJsonObject().get("commit") != null)
            commit.setMessage(jsonElement.getAsJsonObject().get("commit").getAsJsonObject().get("message").getAsString());

        if(jsonElement.getAsJsonObject().get("message") != null)
            commit.setMessage(jsonElement.getAsJsonObject().get("message").getAsString());

        List<File> files = new ArrayList<File>();
        if(jsonElement.getAsJsonObject().get("files") != null)
            jsonElement.getAsJsonObject().get("files").getAsJsonArray().forEach(file -> {
                File newFile = new File();
                newFile.setFilename(file.getAsJsonObject().get("filename").getAsString());
                newFile.setPatch(file.getAsJsonObject().get("patch").getAsString());
                files.add(newFile);
            });
        commit.setFiles(files);

        List<Parent> parents = new ArrayList<Parent>();
        if(jsonElement.getAsJsonObject().get("parents") != null)
            jsonElement.getAsJsonObject().get("parents").getAsJsonArray().forEach(parent -> {
                Parent newParent = new Parent();
                newParent.setSha(parent.getAsJsonObject().get("sha").getAsString());
                parents.add(newParent);
            });
        commit.setParents(parents);

        return commit;

    }
}
