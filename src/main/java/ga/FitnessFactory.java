package ga;

import java.util.List;

public interface FitnessFactory {

    List<Fitness> execute(List<Chromosome> population, FitnessExecutor fitnessExecutor, Integer populationSize);
}
