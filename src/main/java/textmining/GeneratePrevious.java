package textmining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GeneratePrevious {

    private static final String EMPTY = "";
    private static final String BREAKE = "\r\n";
    private static final String BREAKE_N = "\n";
    private static final String PREFIX = "@@";
    private static final String MINUS = "-";
    private static final String MORE = "+";
    private static final String COMMA = ",";

    public String generate(String diff, String raw){

        try {
            List<String> list = Arrays.asList(Optional.ofNullable(diff).orElse(EMPTY).split(BREAKE_N));

            List<String> toOld = new ArrayList<>(Arrays.asList(raw.split(BREAKE_N)));

            Integer start = 0;
            Integer removed = 0;
            for (String s : list) {

                if (s.startsWith(MINUS))
                    continue;

                if (s.startsWith(PREFIX)) {
                    int indexMore = s.indexOf(MORE);
                    start = Integer.valueOf(s.substring(indexMore, s.indexOf(COMMA, indexMore)));
                    start -= removed;
                    start--;
                }

                if (s.startsWith(MORE)) {

                    if (toOld.isEmpty()) {
                        removed++;
                        continue;
                    }

                    if (toOld.size() == (start - 1)) {
                        start = toOld.size();
                    }

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

                if (s.startsWith(PREFIX)) {
                    start = Integer.valueOf(s.substring(s.indexOf(MINUS) + 1, s.indexOf(COMMA)));
                    start--;
                }

                if (toOld.size() == (start - 1)) {
                    start = toOld.size();
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
        }catch (Exception e){
            return EMPTY;
        }
    }
}
