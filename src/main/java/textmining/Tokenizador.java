package textmining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizador {

    public static final String SPACE = "\\s";
    public static final String REGEX = "\\n";
    public static final String EMPTY = "";

    public List<String> token(String text){
        return new ArrayList<>(Arrays.asList(text.replaceAll(REGEX, EMPTY).split(SPACE)));
    }
}
