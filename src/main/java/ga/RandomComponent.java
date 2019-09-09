package ga;

import java.util.Random;

public class RandomComponent {

    public Component create(Component component, Random random){
        Double randomValue = component.min + (random.nextDouble() * (component.max - component.min));
        return new Component(component.min, component.max, randomValue);
    }
}
