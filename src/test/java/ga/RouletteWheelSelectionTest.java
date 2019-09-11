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
    public void deveRetornarCromossomo(){

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0));

        when(random.nextInt(3)).thenReturn(2);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection();
        rouletteWheelSelection.sum(fitnesses, random);
        assertEquals(Arrays.asList(c0, c1, c2), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(3);
    }

    @Test
    public void deveRetornarCromossomoPorRoleta(){

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);
        Component c6 = new Component(0.0, 4.0, 6.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c1, c2, c3)), 6.0));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c4, c5, c6)), 15.0));

        when(random.nextInt(24)).thenReturn(10);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection();
        rouletteWheelSelection.sum(fitnesses, random);
        assertEquals(Arrays.asList(c4, c5, c6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test
    public void deveRetornarCromossomoCasoRSejaIgualSomatoriaMaxima(){

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Component c3 = new Component(0.0, 4.0, 3.0);
        Component c4 = new Component(0.0, 4.0, 4.0);
        Component c5 = new Component(0.0, 4.0, 5.0);
        Component c6 = new Component(0.0, 4.0, 6.0);

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c1, c2, c3)), 6.0));
        fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c4, c5, c6)), 15.0));

        when(random.nextInt(24)).thenReturn(24);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection();
        rouletteWheelSelection.sum(fitnesses, random);
        assertEquals(Arrays.asList(c4, c5, c6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarCromossomoNaoEncontrado(){
        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(new Chromosome(Collections.EMPTY_LIST), 0.0));

        when(random.nextInt(0)).thenReturn(2);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection();
        rouletteWheelSelection.sum(fitnesses, random);
        assertNull(rouletteWheelSelection.selection());
        verify(random, times(1)).nextInt(0);
    }
}