package jaccard;

import textmining.FileUtil;

import java.util.*;

public class ClusterManager {

    public Map<String, Set<Integer>> cluster(String database) {
        List<String> issues = FileUtil.load(database + "-issue.txt");
        Map<String, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < issues.size(); i++) {
            String issue = issues.get(i);
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
}
