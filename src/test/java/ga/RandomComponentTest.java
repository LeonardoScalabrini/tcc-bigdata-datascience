package ga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RandomComponentTest {

    @Mock
    private Random random;

    @Before
    public void init(){
        when(random.nextDouble()).thenReturn(0.5);
    }

    @Test
    public void deveGerarCompomenteRandomico(){
        Component component = new Component(0.0, 5.0, null);
        RandomComponent randomComponent = new RandomComponent();
        Component newComponent = randomComponent.create(component, random);

        assertEquals(component.min, newComponent.min);
        assertEquals(new Double(2.5), newComponent.value);
        assertEquals(component.max, newComponent.max);
    }

}