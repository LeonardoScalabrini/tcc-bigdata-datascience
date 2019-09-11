package ga;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AleatoryPopulationFactoryTest {

    @Test
    public void deveGerarPopulacaoAleatoria(){
        AleatoryPopulationFactory aleatoryPopulationFactory = new AleatoryPopulationFactory();
        assertEquals(50, aleatoryPopulationFactory.create(50).size());
    }

}