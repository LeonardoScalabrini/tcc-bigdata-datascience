package ga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouletteWheelSelectionTest {

    @Mock
    private Random random;

    @Test
    public void deveRetornarCromossomo() throws ChromosomeNotFoundException {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        chromosomes.add(new Chromosome(Arrays.asList(0, 1, 2)));

        when(random.nextInt(3)).thenReturn(2);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(chromosomes, random);
        assertEquals(Arrays.asList(0, 1, 2), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(3);
    }

    @Test
    public void deveRetornarCromossomoPorRoleta() throws ChromosomeNotFoundException {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        chromosomes.add(new Chromosome(Arrays.asList(0, 1, 2)));
        chromosomes.add(new Chromosome(Arrays.asList(1, 2, 3)));
        chromosomes.add(new Chromosome(Arrays.asList(4, 5, 6)));

        when(random.nextInt(24)).thenReturn(10);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(chromosomes, random);
        assertEquals(Arrays.asList(4, 5, 6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test
    public void deveRetornarCromossomoCasoRSejaIgualSomatoriaMaxima() throws ChromosomeNotFoundException {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        chromosomes.add(new Chromosome(Arrays.asList(0, 1, 2)));
        chromosomes.add(new Chromosome(Arrays.asList(1, 2, 3)));
        chromosomes.add(new Chromosome(Arrays.asList(4, 5, 6)));

        when(random.nextInt(24)).thenReturn(24);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(chromosomes, random);
        assertEquals(Arrays.asList(4, 5, 6), rouletteWheelSelection.selection().getValues());
        verify(random, times(1)).nextInt(24);
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarCromossomoNaoEncontrado() throws ChromosomeNotFoundException {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        chromosomes.add(new Chromosome(Collections.EMPTY_LIST));

        when(random.nextInt(0)).thenReturn(2);

        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(chromosomes, random);
        assertNull(rouletteWheelSelection.selection());
        verify(random, times(1)).nextInt(0);
    }
}