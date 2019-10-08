package textmining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizador {

    private static final String SPACE = "\\s";
    private static final String REGEX = "\\n";

    public List<String> token(String text){
        return new ArrayList<>(Arrays.asList(text.replaceAll(REGEX, SPACE).split(SPACE)));
    }
}
