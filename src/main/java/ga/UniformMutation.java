package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformMutation {

    public Chromosome mutation(Chromosome chromosome, Random random) throws ChromosomeNotFoundException{

        if(chromosome == null || chromosome.getValues() == null || chromosome.getValues().isEmpty())
            throw new ChromosomeNotFoundException();

        int size = chromosome.getValues().size();
        int randomNextInt = random.nextInt(size - 1);

        List<Component> values = new ArrayList<Component>(chromosome.getValues());

        Component component = values.get(randomNextInt);
        Double value = component.min + (random.nextDouble() * (component.max - component.min));

        values.remove(randomNextInt);
        values.add(randomNextInt, new Component(component.min, component.max, value));

        return new Chromosome(values);
    }
}
