package textmining;

public class RemoveAnotation {

    private static final String REGEX = "@([^<\\s]*)";
    private static final String EMPTY = "";

    public String remove(String text){
        return text.replaceAll(REGEX, EMPTY);
    }
}
