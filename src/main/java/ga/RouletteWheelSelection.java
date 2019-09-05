package ga;

import java.util.List;
import java.util.Random;

public class RouletteWheelSelection {

    private final List<Chromosome> chromosomes;
    private final Random random;
    private final int sumFitness;

    public RouletteWheelSelection(List<Chromosome> chromosomes, Random random) {
        this.chromosomes = chromosomes;
        this.random = random;
        int sum = 0;
        for (Chromosome chromosome : chromosomes) {
            sum += chromosome.getFitness();
        }
        this.sumFitness = sum;
    }

    public Chromosome selection() throws ChromosomeNotFoundException{
        Integer r = random.nextInt(sumFitness);
        Integer s = 0;
        for (Chromosome chromosome : chromosomes) {
            s += chromosome.getFitness();

            if (s >= r) {
                return chromosome;
            }
        }

        throw new ChromosomeNotFoundException();
    }
}
