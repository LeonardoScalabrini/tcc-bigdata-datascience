package textmining;

import java.util.List;

public class RemoveStopWord {

    private static final String SPACE = " ";
    private final List<String> stopWords;

    public RemoveStopWord(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public List<String> remove(List<String> words){
        words.removeAll(stopWords);
        return words;
    }

    public String remove(String text){
        String result = text;
        for (String stopWord: stopWords) {
            result = result.replaceAll(stopWord, SPACE);
        }

        return result;
    }
}