package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelSelection {

    private final List<Chromosome> chromosomes;
    private final Random random;

    public RouletteWheelSelection(List<Chromosome> chromosomes, Random random) {
        this.chromosomes = chromosomes;
        this.random = random;
    }

    public List<Chromosome> selection(){
        Integer sum = 0;
        for (Chromosome chromosome : chromosomes) {
            sum += chromosome.fitness();
        }

        Integer r = random.nextInt(sum);
        Integer s = 0;
        List<Chromosome> result = new ArrayList<Chromosome>();
        for (Chromosome chromosome : chromosomes) {
            s += chromosome.fitness();

            if (s > r) {
                result.add(chromosome);

                if(result.size() == 2)
                    break;
            }
        }

        return result;
    }
}
