package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConditionTenStopIteration implements ConditionStopIteration {

    private Fitness oldBestFitness;

    private Integer count = 0;

    public boolean shouldStop(List<Fitness> fitnesses) {
        List<Fitness> copyFitnesses = new ArrayList<Fitness>(fitnesses);
        Collections.sort(copyFitnesses);
        Fitness bestFitness = copyFitnesses.get(0);

        if (oldBestFitness == null) {
            oldBestFitness = bestFitness;
            return false;
        }

        if(bestFitness.value > oldBestFitness.value){
            oldBestFitness = bestFitness;
            count = 0;
            return false;
        }

        count++;

        return count == 10;
    }
}
