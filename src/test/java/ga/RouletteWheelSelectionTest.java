package ga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouletteWheelSelectionTest {

    @Mock
    private Random random;

    @Test
    public void deveRetornarCromossomo() throws ChromosomeNotFoundException {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2))));

        when(random.nextInt(3)).thenReturn(2);

        for (Fitness fitness : fitnesses) {
            fitness.run();
        }

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(fitnesses, random);
        assertEquals(Arrays.asList(c0, c1, c2), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(3);
    }

    @Test
    public void deveRetornarCromossomoPorRoleta() throws ChromosomeNotFoundException {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);
        Component c6 = new Component(0.0, 4.0, 6.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2))));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c1, c2, c3))));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c4, c5, c6))));

        when(random.nextInt(24)).thenReturn(10);

        for (Fitness fitness : fitnesses) {
            fitness.run();
        }

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(fitnesses, random);
        assertEquals(Arrays.asList(c4, c5, c6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test
    public void deveRetornarCromossomoCasoRSejaIgualSomatoriaMaxima() throws ChromosomeNotFoundException {

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);
        Component c6 = new Component(0.0, 4.0, 6.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2))));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c1, c2, c3))));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c4, c5, c6))));

        when(random.nextInt(24)).thenReturn(24);

        for (Fitness fitness : fitnesses) {
            fitness.run();
        }

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(fitnesses, random);
        assertEquals(Arrays.asList(c4, c5, c6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarCromossomoNaoEncontrado() throws ChromosomeNotFoundException {
        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Collections.EMPTY_LIST)));

        when(random.nextInt(0)).thenReturn(2);

        for (Fitness fitness : fitnesses) {
            fitness.run();
        }

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(fitnesses, random);
        assertNull(rouletteWheelSelection.selection());
        verify(random, times(1)).nextInt(0);
    }
}