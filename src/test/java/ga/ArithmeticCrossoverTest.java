package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArithmeticCrossoverTest {

    @Mock
    private Random random;

    @Before
    public void init(){
        when(random.nextDouble()).thenReturn(new Double(.5));
    }

    @Test
    public void deveRetornarCrossover() throws ChromosomeNotFoundException{
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        List<Double> crossover = arithmeticCrossover.crossover(0.5, 0.5, random);
        assertEquals(0.5, crossover.get(0).doubleValue(), 0.0001);
        assertEquals(0.5, crossover.get(1).doubleValue(), 0.0001);
        verify(random, times(1)).nextDouble();
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoX1sejaNulo() throws ChromosomeNotFoundException{
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(null, 0.5, random);
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoX2sejaNulo() throws ChromosomeNotFoundException{
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(0.5, null, random);
    }

    @Test(expected = ChromosomeNotFoundException.class)
    public void deveRetornarErroCasoX1eX2sejaNulo() throws ChromosomeNotFoundException{
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.crossover(null, null, random);
    }
}