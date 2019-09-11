package ga;

import java.util.List;

public interface PopulationFactory {

    List<Chromosome> create(List<Fitness> fitnesses, Integer populationSize, Double crossoverProbability, Double mutationProbability);
}
