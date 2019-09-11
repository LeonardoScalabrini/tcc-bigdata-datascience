package ga;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ElitismTwoIndividualsTest {

    @Test
    public void deveElegerDoisIndividuos() {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);

        List<Fitness> fitnessList = Arrays.asList(
                new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0),
                new Fitness(new Chromosome(Arrays.asList(c3, c4, c5)), 12.0));

        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        List<Chromosome> twoIndividuals = elitismTwoIndividuals.elect(fitnessList);
        assertEquals(Arrays.asList(c3, c4, c5), twoIndividuals.get(0).getValues());
        assertEquals(Arrays.asList(c0, c1, c2), twoIndividuals.get(1).getValues());
    }

    @Test
    public void deveElegerDoisIndividuosDeMaisIndividuos() {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);

        List<Fitness> fitnessList = Arrays.asList(
                new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0),
                new Fitness(new Chromosome(Arrays.asList(c3, c4, c5)), 12.0),
                new Fitness(new Chromosome(Arrays.asList(c3, c1, c2)), 5.0));

        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        List<Chromosome> twoIndividuals = elitismTwoIndividuals.elect(fitnessList);
        assertEquals(Arrays.asList(c3, c4, c5), twoIndividuals.get(0).getValues());
        assertEquals(Arrays.asList(c3, c1, c2), twoIndividuals.get(1).getValues());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoTenhaApenasUmIndividuo() {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        List<Fitness> fitnessList = Arrays.asList(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0));
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        elitismTwoIndividuals.elect(fitnessList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoNaoTenhaIndividuo() {
        List<Fitness> fitnessList = Collections.EMPTY_LIST;
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        elitismTwoIndividuals.elect(fitnessList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoIndividuoSejaNulo() {
        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        elitismTwoIndividuals.elect(null);
    }
}