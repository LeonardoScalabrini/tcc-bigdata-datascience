package ga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UniformMutationTest {

    @Mock
    private Random random;

    @Test
    public void deveCriarCromossomoMutado(){

        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        Chromosome chromosome = new Chromosome(Arrays.asList(c0, c1, c2));

        when(random.nextInt(any(Integer.class))).thenReturn(1);
        when(random.nextDouble()).thenReturn(0.75);

        UniformMutation uniformMutation = new UniformMutation();
        Chromosome mutation = uniformMutation.mutation(chromosome, random);

        assertTrue(c0.equals(mutation.getValues().get(0)));
        assertEquals(c1.min, mutation.getValues().get(1).min);
        assertEquals(c1.max, mutation.getValues().get(1).max);
        assertEquals(3.0, mutation.getValues().get(1).value, 0.00001);
        assertTrue(c2.equals(mutation.getValues().get(2)));

        verify(random, times(1)).nextInt(anyInt());
        verify(random, times(1)).nextDouble();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoChromosomeEstejaNulo(){
        new UniformMutation().mutation(null, random);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarErroCasoValoresEstejaVazio(){
        new UniformMutation().mutation(new Chromosome(Collections.EMPTY_LIST), random);
    }
}