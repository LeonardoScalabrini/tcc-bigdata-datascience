package ga;

import java.util.ArrayList;
import java.util.List;

public class GA {

    private final StartPopulationFactory startPopulationFactory;
    private final FitnessExecutor fitnessExecutor;
    private final FitnessParallelFactory fitnessParallelFactory;
    private final PopulationFactory evolutionPopulationFactory;
    private final ConditionStopIteration conditionStopIteration;

    public GA(StartPopulationFactory startPopulationFactory, FitnessExecutor fitnessExecutor, FitnessParallelFactory fitnessParallelFactory, PopulationFactory evolutionPopulationFactory, ConditionStopIteration conditionStopIteration) {
        this.startPopulationFactory = startPopulationFactory;
        this.fitnessExecutor = fitnessExecutor;
        this.fitnessParallelFactory = fitnessParallelFactory;
        this.evolutionPopulationFactory = evolutionPopulationFactory;
        this.conditionStopIteration = conditionStopIteration;
    }

    public void execute(final Integer populationSize, final Integer iterations, Double crossoverProbability, Double mutationProbability) {

        List<Chromosome> population = startPopulationFactory.create(populationSize);
        List<Fitness> fitnesses = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            fitnesses = fitnessParallelFactory.execute(population, fitnessExecutor, populationSize);

            if (conditionStopIteration.shouldStop(fitnesses))
                break;

            List<Chromosome> newPopulation = evolutionPopulationFactory.create(fitnesses, populationSize, crossoverProbability, mutationProbability);
            population.clear();
            population.addAll(newPopulation);
        }
        System.out.println("FIM GA");
        fitnesses.forEach(fitness -> {
            System.out.println("FITNESS VALUE");
            System.out.println(fitness.value.doubleValue());
            fitness.chromosome.getValues().forEach(component -> {
                System.out.println("COMPONENT");
                System.out.println(component.value.doubleValue());
            });
        });
    }
}
