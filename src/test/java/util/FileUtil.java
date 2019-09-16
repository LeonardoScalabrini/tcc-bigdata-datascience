package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private static final String BASE_PATH = "src/test/resources/jsons/";

    public static String loadJson(String path) {
        try {
            String formattedPath = BASE_PATH + path;
            return new String(Files.readAllBytes(Paths.get(formattedPath)));
        } catch (IOException e) {
            return "Error loading json.";
        }
    }
}
