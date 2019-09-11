package ga;

import java.util.List;

public interface ConditionStopIteration {

    boolean shouldStop(List<Fitness> fitnesses);
}
