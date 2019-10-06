package textmining;

import java.util.List;

public class RemoveStopWord {

    private static final String EMPTY = "";
    private static final String REGEX = "(%s)";
    private final List<String> stopWords;


    public RemoveStopWord(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public String remove(String text){

        String result = text;
        for (String stopWord: stopWords) {
            result = result.replaceAll(stopWord, EMPTY);
        }

        return result;
    }
}
