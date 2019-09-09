package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GATest {

    @InjectMocks
    private GA ga;
    @Mock
    private StartPopulationFactory startPopulationFactory;
    @Mock
    private FitnessExecutor fitnessExecutor;
    @Mock
    private FitnessParallelFactory fitnessParallelFactory;
    @Mock
    private PopulationFactory evolutionPopulationFactory;

    private List<Chromosome> chromosomes = new ArrayList<Chromosome>();

    private List<Fitness> fitnesses = new ArrayList<Fitness>();

    @Before
    public void init() throws ChromosomeNotFoundException {
        when(startPopulationFactory.create(50)).thenReturn(chromosomes);
        when(fitnessParallelFactory.execute(chromosomes, fitnessExecutor, 50)).thenReturn(fitnesses);
        when(evolutionPopulationFactory.create(fitnesses, 50)).thenReturn(chromosomes);
    }

    @Test
    public void deveExecutarGA() throws ChromosomeNotFoundException {
        ga.execute(50, 50);

        verify(startPopulationFactory, times(1)).create(50);
        verify(fitnessParallelFactory, times(50)).execute(chromosomes, fitnessExecutor, 50);
        verify(evolutionPopulationFactory, times(50)).create(fitnesses, 50);
    }
}