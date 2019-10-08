package textmining;

import java.util.List;

public class RemoveTwoCharacter {

    private static final int TWO = 2;

    public List<String> remove(List<String> words){
        words.removeIf(s -> s.length() <= TWO);
        return words;
    }
}
