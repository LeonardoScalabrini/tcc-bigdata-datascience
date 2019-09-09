package ga;

import java.util.List;
import java.util.Random;

public class RouletteWheelSelection {

    private List<Fitness> fitnesses;
    private Random random;
    private Double sumFitness;

    public void sum(List<Fitness> fitnesses, Random random) {
        this.fitnesses = fitnesses;
        this.random = random;
        Double sum = 0.0;
        for (Fitness fitness : this.fitnesses) {
            sum += fitness.value;
        }
        this.sumFitness = sum;
    }

    public Chromosome selection() throws ChromosomeNotFoundException{
        Integer r = random.nextInt(sumFitness.intValue());
        Double s = 0.0;
        for (Fitness fitness : fitnesses) {
            s += fitness.value;

            if (s >= r) {
                return fitness.chromosome;
            }
        }

        throw new ChromosomeNotFoundException();
    }
}
