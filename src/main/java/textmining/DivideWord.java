package textmining;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DivideWord {

    public List<String> divide(List<String> words){

        List<String> result = new ArrayList<>();
        for (String word : words) {
            result.addAll(extractWords(word));
        }

        return result;
    }

    private List<String> extractWords(String word) {
        List<String> result = new ArrayList<>();
        String lowerCaseWord = "";
        for(int i=0; i< word.length(); i++){
            char c = word.charAt(i);
            String letter = Character.toString(c);

            boolean isUpperCase = Character.isUpperCase(c);
            boolean isAllUpperCase = StringUtils.isAllUpperCase(lowerCaseWord);
            boolean isBlank = StringUtils.isBlank(lowerCaseWord);
            int length = lowerCaseWord.length();

            if (isUpperCase && (isAllUpperCase || isBlank)){
                lowerCaseWord += letter;
                continue;
            }

            if (!isUpperCase && isAllUpperCase && length > 1){


                result.add(lowerCaseWord.substring(0, length - 1).toLowerCase());

                String upperCaseWord = lowerCaseWord.substring(length - 1, length);
                lowerCaseWord = upperCaseWord + letter;

                continue;
            }

            if (isUpperCase){
                result.add(lowerCaseWord.toLowerCase());
                lowerCaseWord = letter;
                continue;
            }

            lowerCaseWord += letter;
        }

        result.add(lowerCaseWord.toLowerCase());

        return result;
    }
}
