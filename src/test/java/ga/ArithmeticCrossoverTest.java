package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArithmeticCrossoverTest {

    @Mock
    private Random random;
    private Chromosome chromosome1;
    private Chromosome chromosome2;

    @Before
    public void init(){
        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);
        Component c3 = new Component(0.0, 4.0, 3.0);

        this.chromosome1 = new Chromosome(Arrays.asList(c0, c1, c2));
        this.chromosome2 = new Chromosome(Arrays.asList(c0, c1, c3));

        when(random.nextDouble()).thenReturn(new Double(.5));
        when(random.nextInt(anyInt())).thenReturn(2);
    }

    @Test
    public void deveRetornarCrossover(){
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);
        Component c3 = new Component(0.0, 4.0, 3.0);

        Chromosome chromosome1 = new Chromosome(Arrays.asList(c0, c1, c2));
        Chromosome chromosome2 = new Chromosome(Arrays.asList(c0, c1, c3));

        List<Chromosome> chromosomes = arithmeticCrossover.crossover(chromosome1, chromosome2, random);

        assertEquals(c0.value, chromosomes.get(0).getValues().get(0).value, 0.0001);
        assertEquals(c1.value, chromosomes.get(0).getValues().get(1).value, 0.0001);
        assertEquals(2.5, chromosomes.get(0).getValues().get(2).value, 0.0001);

        assertEquals(c0.value, chromosomes.get(1).getValues().get(0).value, 0.0001);
        assertEquals(c1.value, chromosomes.get(1).getValues().get(1).value, 0.0001);
        assertEquals(2.5, chromosomes.get(1).getValues().get(2).value, 0.0001);

        verify(random, times(1)).nextDouble();
        verify(random, times(1)).nextInt(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoX1sejaNulo(){
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(null, chromosome2, random);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoX2sejaNulo(){
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(chromosome1, null, random);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoX1eX2sejaNulo(){
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(null, null, random);
    }
}