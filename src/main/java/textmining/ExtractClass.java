package textmining;

public class ExtractClass {

    private static final String START_OF_CLASS = "{";
    private static final String END_OF_CLASS = "}";

    public String extract(String code){
        int index = code.indexOf(START_OF_CLASS);
        return code.substring(0, index + 1) + END_OF_CLASS;
    }
}
