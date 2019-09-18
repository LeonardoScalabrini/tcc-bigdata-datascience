package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FitnessParallelFactoryTest {

    private FitnessParallelFactory fitnessParallelFactory;

    @Mock
    private FitnessExecutor fitnessExecutor;
    @Mock
    private Chromosome chromosome;

    private List<Chromosome> chromosomes;

    @Before
    public void init(){

        this.fitnessParallelFactory = new FitnessParallelFactory();

        this.chromosomes = new ArrayList<Chromosome>();
        for (int i = 0; i < 50; i++) {
            chromosomes.add(chromosome);
        }
    }

    @Test
    public void deveExecutarAptidao(){
        List<Fitness> fitnesses = fitnessParallelFactory.execute(chromosomes, fitnessExecutor, 50);

        assertEquals(50, fitnesses.size());
        verify(fitnessExecutor, times(50)).execute(chromosome);
    }
}