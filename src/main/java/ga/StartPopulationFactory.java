package ga;

import java.util.List;

public interface StartPopulationFactory {

    List<Chromosome> create(Integer populationSize);
}
