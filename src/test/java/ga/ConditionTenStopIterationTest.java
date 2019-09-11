package ga;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConditionTenStopIterationTest {

    @Test
    public void deveParaCasoFique10IteracoesSemEvolucao(){
        ConditionTenStopIteration conditionTenStopIteration = new ConditionTenStopIteration();

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(null, 10.0));

        assertFalse(conditionTenStopIteration.shouldStop(fitnesses));

        for (int i = 0; i < 9; i++) {
            assertFalse(conditionTenStopIteration.shouldStop(fitnesses));
        }

        assertTrue(conditionTenStopIteration.shouldStop(fitnesses));
    }

    @Test
    public void devePararCasoEvoluaMasDepoisPareDeEvoluir(){
        ConditionTenStopIteration conditionTenStopIteration = new ConditionTenStopIteration();

        List<Fitness> fitnesses = new ArrayList<Fitness>();
        fitnesses.add(new Fitness(null, 10.0));

        assertFalse(conditionTenStopIteration.shouldStop(fitnesses));

        for (int i = 0; i < 9; i++) {
            assertFalse(conditionTenStopIteration.shouldStop(fitnesses));
        }

        fitnesses.add(new Fitness(null, 20.0));
        assertFalse(conditionTenStopIteration.shouldStop(fitnesses));

        for (int i = 0; i < 9; i++) {
            assertFalse(conditionTenStopIteration.shouldStop(fitnesses));
        }

        assertTrue(conditionTenStopIteration.shouldStop(fitnesses));
    }
}