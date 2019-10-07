package textmining;

import java.util.List;

public class RemoveTwoCharacter {
    public List<String> remove(List<String> words){
        words.removeIf(s -> s.length() <= 2);
        return words;
    }
}
