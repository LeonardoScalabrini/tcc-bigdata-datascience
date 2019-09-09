package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FitnessParallelFactory implements FitnessFactory {

    public List<Fitness> execute(List<Chromosome> population, final FitnessExecutor fitnessExecutor, Integer populationSize) {
        final List<Fitness> fitnesses = new ArrayList<Fitness>();
        ExecutorService executor = Executors.newFixedThreadPool(populationSize);
        for (final Chromosome chromosome : population) {
            executor.execute(new Runnable() {
                public void run() {
                    Fitness fitness = fitnessExecutor.execute(chromosome);
                    addFitness(fitnesses, fitness);
                }
            });
        }
        executor.shutdown();
        while(!executor.isTerminated());

        return fitnesses;
    }

    private synchronized void addFitness(List<Fitness> fitnesses, Fitness fitness){
        fitnesses.add(fitness);
    }
}
