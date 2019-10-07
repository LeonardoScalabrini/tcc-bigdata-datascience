package textmining;

import java.util.List;

public class RemoveNumber {

    public List<String> remove(List<String> words){
        words.removeIf(word -> {
            for(int i=0; i< word.length(); i++){
                char c = word.charAt(i);
                if (Character.isDigit(c))
                    return true;
            }
            return false;
        });
        return words;
    }
}
