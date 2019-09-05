package ga;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ElitismTwoIndividualsTest {

    @Test
    public void deveElegerDoisIndividuos() throws ChromosomeNotFoundException{

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);

        List<Chromosome> chromosomeList = Arrays.asList(new Chromosome(Arrays.asList(c0, c1, c2)), new Chromosome(Arrays.asList(c3, c4, c5)));
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(chromosomeList);
        List<Chromosome> twoIndividuals = elitismTwoIndividuals.elect();
        assertEquals(Arrays.asList(c3, c4, c5), twoIndividuals.get(0).getValues());
        assertEquals(Arrays.asList(c0, c1, c2), twoIndividuals.get(1).getValues());
    }

    @Test
    public void deveElegerDoisIndividuosDeMaisIndividuos() throws ChromosomeNotFoundException{

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);

        List<Chromosome> chromosomeList = Arrays.asList(new Chromosome(Arrays.asList(c0, c1, c2)), new Chromosome(Arrays.asList(c3, c4, c5)), new Chromosome(Arrays.asList(c3, c1, c2)));
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(chromosomeList);
        List<Chromosome> twoIndividuals = elitismTwoIndividuals.elect();
        assertEquals(Arrays.asList(c3, c4, c5), twoIndividuals.get(0).getValues());
        assertEquals(Arrays.asList(c3, c1, c2), twoIndividuals.get(1).getValues());
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoTenhaApenasUmIndividuo() throws ChromosomeNotFoundException{

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        List<Chromosome> chromosomeList = Arrays.asList(new Chromosome(Arrays.asList(c0, c1, c2)));
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(chromosomeList);
        elitismTwoIndividuals.elect();
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoNaoTenhaIndividuo() throws ChromosomeNotFoundException{
        List<Chromosome> chromosomeList = Collections.EMPTY_LIST;
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(chromosomeList);
        elitismTwoIndividuals.elect();
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoIndividuoSejaNulo() throws ChromosomeNotFoundException{
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(null);
        elitismTwoIndividuals.elect();
    }
}