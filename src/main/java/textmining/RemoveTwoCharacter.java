package textmining;

public class RemoveTwoCharacter {

    public static final String REGEX = "\\s([a-z])\\s";
    public static final String REGEX_DUPLO = "\\s([a-z][a-z])\\s";
    public static final String EMPTY = "";

    public String remove(String text){
        return text.replaceAll(REGEX, EMPTY)
                .replaceAll(REGEX_DUPLO, EMPTY);
    }
}
