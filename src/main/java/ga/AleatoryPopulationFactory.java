package ga;

import java.util.ArrayList;
import java.util.List;

public class AleatoryPopulationFactory implements StartPopulationFactory {

    public List<Chromosome> create(Integer populationSize){

        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        for (int i = 0; i < populationSize; i++) {
            List<Component> components = new ArrayList<Component>();
            //[k, n, α, β].
            components.add(new Component(100.0, 100.0, 100.0));//k
            components.add(new Component(500.0, 500.0, 500.0));//n
            components.add(new Component(0.0, 1.0, null));//α
            components.add(new Component(0.0, 1.0, null));//β
            chromosomes.add(new Chromosome(components));
        }

        return chromosomes;
    }
}
