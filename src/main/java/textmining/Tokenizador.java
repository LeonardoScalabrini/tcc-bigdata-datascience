package textmining;

import java.util.Arrays;
import java.util.List;

public class Tokenizador {

    public static final String SPACE = "\\s";

    public List<String> token(String text){
        return Arrays.asList(text.split(SPACE));
    }
}
