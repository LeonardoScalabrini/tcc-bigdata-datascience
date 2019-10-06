package textmining;

public class RemoveAnotation {

    public static final String REGEX = "@([^<\\s]*)";
    public static final String EMPTY = "";

    public String remove(String text){
        return text.replaceAll(REGEX, EMPTY);
    }
}
