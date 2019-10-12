package jaccard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CalculateAverageJaccardTest {

    private CalculateAverageJaccard calculateAverageJaccard = new CalculateAverageJaccard();

    @Test
    public void deveCalcular(){
        Map<String, Set<Integer>> mapIssue = new HashMap<>();
        mapIssue.put("A", new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        mapIssue.put("B", new HashSet<Integer>(Arrays.asList(4, 5, 6)));
        mapIssue.put("C", new HashSet<Integer>(Arrays.asList(7, 8, 9)));

        Map<Integer, Set<Integer>> mapTheta = new HashMap<>();
        mapTheta.put(0, new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        mapTheta.put(1, new HashSet<Integer>(Arrays.asList(4, 5, 6)));
        mapTheta.put(2, new HashSet<Integer>(Arrays.asList(7, 8, 9)));

        Double jaccard = calculateAverageJaccard.calcule(mapIssue, mapTheta);
        Assert.assertEquals(1.0, jaccard, 0.0001);
    }

}