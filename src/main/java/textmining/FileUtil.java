package textmining;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    private static final String BASE_PATH = "src/main/resources";
    private static final String REGEX_N = "\n";
    private static final String REGEX_R = "\r";
    private static final String EMPTY = "";
    private static final String SPACE = " ";

    public static List<String> load(String path) {
        return load(BASE_PATH, path);
    }

    public static List<String> load(String dir, String path) {
        try {
            String formattedPath = dir + "/" + path;
            return Arrays.asList(new String(Files.readAllBytes(Paths.get(formattedPath))).replaceAll(REGEX_R, EMPTY).split(REGEX_N));
        } catch (IOException e) {
            return Arrays.asList("Error loading json.");
        }
    }
    public static BufferedWriter write(String path) {
        try {
            String formattedPath = BASE_PATH + path;
            File file = new File(formattedPath);
            return new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            return null;
        }
    }
}
