package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElitismTwoIndividuals {

    private final List<Chromosome> chromosomes;

    public ElitismTwoIndividuals(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public List<Chromosome> elect() throws ChromosomeNotFoundException {

        if(chromosomes == null)
            throw new ChromosomeNotFoundException();

        List<Chromosome> copyChromosomes = new ArrayList<Chromosome>(chromosomes);
        Collections.sort(copyChromosomes);

        List<Chromosome> result = new ArrayList<Chromosome>();
        int count = 0;
        for (Chromosome chromosome : copyChromosomes) {
            result.add(chromosome);
            count++;

            if(count == 2)
                return result;
        }

        throw new ChromosomeNotFoundException();
    }
}
