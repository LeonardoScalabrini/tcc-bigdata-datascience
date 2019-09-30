package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private static final String BASE_PATH = "src/test/resources/jsons/";
    private static final String BASE_PATH_JAVA = "src/test/resources/java/";

    public static String loadJson(String path) {
        try {
            String formattedPath = BASE_PATH + path;
            return new String(Files.readAllBytes(Paths.get(formattedPath)));
        } catch (IOException e) {
            return "Error loading json.";
        }
    }

    public static String loadJava(String path) {
        try {
            String formattedPath = BASE_PATH_JAVA + path;
            return new String(Files.readAllBytes(Paths.get(formattedPath)));
        } catch (IOException e) {
            return "Error loading java.";
        }
    }
}
