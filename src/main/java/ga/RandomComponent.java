package ga;

import java.util.Random;

public class RandomComponent {

    private final Random random;

    public RandomComponent(Random random) {
        this.random = random;
    }

    public Component getComponent(Component component){
        Double randomValue = component.min + (random.nextDouble() * (component.max - component.min));
        return new Component(component.min, component.max, randomValue);
    }
}
