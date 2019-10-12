package jaccard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculateAverageJaccard {

    private Jaccard jaccard = new Jaccard();

    public Double calcule(Map<String, Set<Integer>> mapIssue, Map<Integer, Set<Integer>> mapTheta){

        List<Double> maxIndexJaccards = new ArrayList<>();
        mapIssue.values().stream().forEach(integersIssue -> {
           Double maxIndexJaccard = Double.MIN_VALUE;
            for (Set<Integer> integersTheta : mapTheta.values()){
                Double indexJaccard = jaccard.index(integersIssue, integersTheta);
                if (indexJaccard > maxIndexJaccard)
                    maxIndexJaccard = indexJaccard;
            }
            maxIndexJaccards.add(maxIndexJaccard);
        });

        Double sum = maxIndexJaccards.stream().mapToDouble(value -> value).sum();

        return sum/maxIndexJaccards.size();
    }
}
