package jaccard;

import textmining.FileUtil;

import java.util.*;

public class LDAClusterManager {

    private static final String SPACE = "\\s";

    public Map<Integer, Set<Integer>> cluster(String dir, String uuid) {
        List<String> lines = FileUtil.load(dir, uuid + ".theta");
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            List<String> nubers = Arrays.asList(lines.get(i).split(SPACE));
            Integer issue = getMaxIndex(nubers);
            Set<Integer> integers = map.get(issue);

            if(integers == null || integers.isEmpty()) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                map.put(issue, set);
                continue;
            }

            integers.add(i);
        }

        return map;
    }

    private Integer getMaxIndex(List<String> nubers){

        Double maxNumber = Double.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < nubers.size(); i++) {
            Double number = Double.parseDouble(nubers.get(i));
            if(number > maxNumber) {
                maxNumber = number;
                index = i;
            }
        }

        return index;
    }
}
