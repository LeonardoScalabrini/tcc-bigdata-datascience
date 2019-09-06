package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElitismTwoIndividuals {

    private final List<Fitness> fitnesses;

    public ElitismTwoIndividuals(List<Fitness> fitnesses) {
        this.fitnesses = fitnesses;
    }

    public List<Chromosome> elect() throws ChromosomeNotFoundException {

        if(fitnesses == null || fitnesses.size() < 2)
            throw new ChromosomeNotFoundException();

        List<Fitness> copyFitnesses = new ArrayList<Fitness>(fitnesses);
        Collections.sort(copyFitnesses);

        return Arrays.asList(copyFitnesses.get(0).chromosome, copyFitnesses.get(1).chromosome);
    }
}
