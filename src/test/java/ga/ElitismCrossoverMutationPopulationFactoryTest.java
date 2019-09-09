package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ElitismCrossoverMutationPopulationFactoryTest {

    @InjectMocks
    private ElitismCrossoverMutationPopulationFactory elitismCrossoverMutationPopulationFactory;
    @Mock
    private ElitismTwoIndividuals elitismTwoIndividuals;
    @Mock
    private RouletteWheelSelection rouletteWheelSelection;
    @Mock
    private ArithmeticCrossover arithmeticCrossover;
    @Mock
    private UniformMutation uniformMutation;
    @Mock
    private Random random;
    @Mock
    private Chromosome chromosome;

    private List<Fitness> fitnesses;

    private List<Chromosome> electChromosomes;

    private List<Chromosome> crossoverChromosomes;

    @Before
    public void init() throws ChromosomeNotFoundException {
        Component c0 = new Component(0.0, 4.0, 0.0);
        Component c1 = new Component(0.0, 4.0, 1.0);
        Component c2 = new Component(0.0, 4.0, 2.0);

        this.fitnesses = new ArrayList<Fitness>();
        this.fitnesses.add(new Fitness(new Chromosome(Arrays.asList(c0, c1, c2)), 3.0));

        this.electChromosomes = new ArrayList<Chromosome>();
        this.electChromosomes.add(new Chromosome(Arrays.asList(c0, c1, c2)));
        this.electChromosomes.add(new Chromosome(Arrays.asList(c0, c1, c2)));

        this.crossoverChromosomes = new ArrayList<Chromosome>();
        this.crossoverChromosomes.add(new Chromosome(Arrays.asList(c0, c1, c2)));
        this.crossoverChromosomes.add(new Chromosome(Arrays.asList(c0, c1, c2)));

        when(elitismTwoIndividuals.elect(fitnesses)).thenReturn(electChromosomes);
        when(rouletteWheelSelection.selection()).thenReturn(chromosome);
        when(arithmeticCrossover.crossover(chromosome, chromosome, random)).thenReturn(crossoverChromosomes);
        when(uniformMutation.mutation(chromosome, random)).thenReturn(chromosome);
    }

    @Test
    public void deveCriarPopulacao() throws ChromosomeNotFoundException {
        List<Chromosome> chromosomes = elitismCrossoverMutationPopulationFactory.create(fitnesses, 50);
        assertEquals(50, chromosomes.size());
        verify(elitismTwoIndividuals, times(1)).elect(fitnesses);
        verify(rouletteWheelSelection, times(1)).sum(fitnesses, random);
        verify(rouletteWheelSelection, times(48)).selection();
        verify(arithmeticCrossover, times(23)).crossover(chromosome, chromosome, random);
        verify(uniformMutation, times(2)).mutation(chromosome, random);
    }
}