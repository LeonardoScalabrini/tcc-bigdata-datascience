package jaccard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class CalculateAverageJaccard {

    private Jaccard jaccard = new Jaccard();

    public Double calcule(Map<String, Set<Integer>> mapIssue, Map<Integer, Set<Integer>> mapTheta){

        List<Double> maxIndexJaccards = new ArrayList<>();
        mapIssue.values().parallelStream().forEach(integersIssue -> {
           AtomicReference<Double> maxIndexJaccard = new AtomicReference<>(Double.MIN_VALUE);
            mapTheta.values().parallelStream().forEach(integersTheta -> {
                Double indexJaccard = jaccard.index(integersIssue, integersTheta);
                if (indexJaccard > maxIndexJaccard.get())
                    maxIndexJaccard.set(indexJaccard);
           });
            maxIndexJaccards.add(maxIndexJaccard.get());
        });

        Double sum = maxIndexJaccards.parallelStream().mapToDouble(value -> value).sum();

        return sum/maxIndexJaccards.size();
    }
}
