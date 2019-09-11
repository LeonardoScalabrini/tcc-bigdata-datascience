package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ElitismCrossoverMutationPopulationFactory implements PopulationFactory {

    private final ElitismTwoIndividuals elitismTwoIndividuals;
    private final RouletteWheelSelection rouletteWheelSelection;
    private final ArithmeticCrossover arithmeticCrossover;
    private final UniformMutation uniformMutation;
    private final Random random;

    public ElitismCrossoverMutationPopulationFactory(ElitismTwoIndividuals elitismTwoIndividuals,
                                                     RouletteWheelSelection rouletteWheelSelection,
                                                     ArithmeticCrossover arithmeticCrossover,
                                                     UniformMutation uniformMutation,
                                                     Random random) {
        this.elitismTwoIndividuals = elitismTwoIndividuals;
        this.rouletteWheelSelection = rouletteWheelSelection;
        this.arithmeticCrossover = arithmeticCrossover;
        this.uniformMutation = uniformMutation;
        this.random = random;
    }

    public List<Chromosome> create(List<Fitness> fitnesses, Integer populationSize, Double crossoverProbability, Double mutationProbability){
        List<Chromosome> newPopulation = new ArrayList<Chromosome>();
        newPopulation.addAll(elitismTwoIndividuals.elect(fitnesses));
        rouletteWheelSelection.sum(fitnesses, random);
        for (int r = 2; r < populationSize; r+=2) {

            Chromosome c1 = rouletteWheelSelection.selection();
            Chromosome c2 = rouletteWheelSelection.selection();

            if (random.nextDouble() <= crossoverProbability){
                newPopulation.addAll(arithmeticCrossover.crossover(c1, c2, random));
                continue;
            }

            newPopulation.addAll(Arrays.asList(c1, c2));
        }

        List<Chromosome> toRemove = new ArrayList<Chromosome>();
        List<Chromosome> toAdd = new ArrayList<Chromosome>();
        for (Chromosome chromosome : newPopulation) {
            if (random.nextDouble() <= mutationProbability){
                toAdd.add(uniformMutation.mutation(chromosome, random));
                toRemove.add(chromosome);
            }
        }
        newPopulation.removeAll(toRemove);
        newPopulation.addAll(toAdd);

        return newPopulation;
}
}
