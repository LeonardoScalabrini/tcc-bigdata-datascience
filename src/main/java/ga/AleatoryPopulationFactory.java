package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AleatoryPopulationFactory implements StartPopulationFactory {

    private Random random = new Random();

    public List<Chromosome> create(Integer populationSize){

        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        for (int i = 0; i < populationSize; i++) {
            List<Component> components = new ArrayList<Component>();
            //[k, n, α, β].
            components.add(new Component(1681.0, 1681.0, 1681.0));//k
            components.add(new Component(500.0, 500.0, 500.0));//n
            components.add(new Component(0.0, 1.0, random.nextDouble()));//α
            components.add(new Component(0.0, 1.0, random.nextDouble()));//β
            chromosomes.add(new Chromosome(components));
        }

        return chromosomes;
    }
}
