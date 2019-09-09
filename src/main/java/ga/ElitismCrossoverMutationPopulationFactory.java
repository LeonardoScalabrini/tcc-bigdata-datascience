package ga;

import java.util.ArrayList;
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

    public List<Chromosome> create(List<Fitness> fitnesses, Integer populationSize) throws ChromosomeNotFoundException {
        List<Chromosome> newPopulation = new ArrayList<Chromosome>();
        newPopulation.addAll(elitismTwoIndividuals.elect(fitnesses));
        rouletteWheelSelection.sum(fitnesses, random);
        for (int r = 2; r < populationSize - 2; r+=2) {
            Chromosome c1 = rouletteWheelSelection.selection();
            Chromosome c2 = rouletteWheelSelection.selection();
            newPopulation.addAll(arithmeticCrossover.crossover(c1, c2, random));
        }
        Chromosome c1 = rouletteWheelSelection.selection();
        Chromosome c2 = rouletteWheelSelection.selection();
        newPopulation.add(uniformMutation.mutation(c1, random));
        newPopulation.add(uniformMutation.mutation(c2, random));
        return newPopulation;
}
}
