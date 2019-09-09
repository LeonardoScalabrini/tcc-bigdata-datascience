package ga;

import java.util.List;

public class GA {

    private final StartPopulationFactory startPopulationFactory;
    private final FitnessExecutor fitnessExecutor;
    private final FitnessParallelFactory fitnessParallelFactory;
    private final PopulationFactory evolutionPopulationFactory;

    public GA(StartPopulationFactory startPopulationFactory, FitnessExecutor fitnessExecutor, FitnessParallelFactory fitnessParallelFactory, PopulationFactory evolutionPopulationFactory) {
        this.startPopulationFactory = startPopulationFactory;
        this.fitnessExecutor = fitnessExecutor;
        this.fitnessParallelFactory = fitnessParallelFactory;
        this.evolutionPopulationFactory = evolutionPopulationFactory;
    }

    public void execute(final Integer populationSize, final Integer iterations) throws ChromosomeNotFoundException {

        List<Chromosome> population = startPopulationFactory.create(populationSize);

        for (int i = 0; i < iterations; i++) {
            List<Fitness> fitnesses = fitnessParallelFactory.execute(population, fitnessExecutor, populationSize);
            List<Chromosome> newPopulation = evolutionPopulationFactory.create(fitnesses, populationSize);
            population.clear();
            population.addAll(newPopulation);
        }
    }
}
