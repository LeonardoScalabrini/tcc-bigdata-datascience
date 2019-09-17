package github;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class JsonConverter {

    private Gson gson = new Gson();

    public <T> T fromJson(String json, Class<T> tClass){
        return gson.fromJson(json, tClass);
    }

    public <T> List<T> fromJsons(String json, Class<T[]> tArray){

        return Arrays.asList(gson.fromJson(json, tArray));
    }
}
