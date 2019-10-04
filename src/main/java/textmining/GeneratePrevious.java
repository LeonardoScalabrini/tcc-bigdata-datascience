package textmining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GeneratePrevious {

    public static final String BREAKE = "\r\n";
    public static final String PATTERN_N = Pattern.quote("\\n");
    public static final String PREFIX = "@@";
    public static final String MINUS = "-";
    public static final String MORE = "+";
    public static final String COMMA = ",";
    public static final String PATTERN_RAW = Pattern.quote(BREAKE);

    public String generate(String diff, String raw){

        List<String> list = Arrays.asList(diff.replaceAll(PATTERN_N, BREAKE).split(PATTERN_RAW));

        List<String> toOld = new ArrayList<>(Arrays.asList(raw.split(BREAKE)));

        Integer start = 0;
        Integer removed = 0;
        for (String s : list) {

            if (s.startsWith(MINUS))
                continue;

            if (s.startsWith(PREFIX)){
                start = Integer.valueOf(s.substring(s.indexOf(MORE), s.lastIndexOf(COMMA)));
                start -= removed;
                start--;
            }

            if (s.startsWith(MORE)) {
                toOld.remove(start - 1);
                removed++;
                continue;
            }

            start++;
        }

        start = 0;
        for (String s : list) {

            if (s.startsWith(MORE))
                continue;

            if (s.startsWith(PREFIX)){
                start = Integer.valueOf(s.substring(s.indexOf(MINUS) + 1, s.indexOf(COMMA)));
                start--;
            }

            if (s.startsWith(MINUS))
                toOld.add(start - 1, s.substring(1, s.length()));

            start++;
        }

        StringBuilder generate = new StringBuilder();
        for (String n : toOld) {
            generate.append(n).append(BREAKE);
        }

        return generate.toString();
    }
}
