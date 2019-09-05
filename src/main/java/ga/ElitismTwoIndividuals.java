package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElitismTwoIndividuals {

    private final List<Chromosome> chromosomes;

    public ElitismTwoIndividuals(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public List<Chromosome> elect() throws ChromosomeNotFoundException {

        if(chromosomes == null || chromosomes.size() < 2)
            throw new ChromosomeNotFoundException();

        List<Chromosome> copyChromosomes = new ArrayList<Chromosome>(chromosomes);
        Collections.sort(copyChromosomes);

        return Arrays.asList(copyChromosomes.get(0), copyChromosomes.get(1));
    }
}
