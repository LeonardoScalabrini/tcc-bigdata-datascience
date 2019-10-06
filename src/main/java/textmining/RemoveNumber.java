package textmining;

public class RemoveNumber {

    public static final String REGEX = "[0-9]+(\\.[0-9][0-9]?)?";
    public static final String EMPTY = "";

    String remove(String text){
        return text.replaceAll(REGEX, EMPTY);
    }
}
