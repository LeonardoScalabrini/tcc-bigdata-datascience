package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElitismTwoIndividuals {

    public List<Chromosome> elect(List<Fitness> fitnesses) throws ChromosomeNotFoundException {

        if(fitnesses == null || fitnesses.size() < 2)
            throw new ChromosomeNotFoundException();

        List<Fitness> copyFitnesses = new ArrayList<Fitness>(fitnesses);
        Collections.sort(copyFitnesses);

        return Arrays.asList(copyFitnesses.get(0).chromosome, copyFitnesses.get(1).chromosome);
    }
}
