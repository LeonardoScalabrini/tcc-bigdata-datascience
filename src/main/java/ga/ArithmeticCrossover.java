package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArithmeticCrossover {

    private static final double ONE = 1.0;

    public List<Chromosome> crossover(Chromosome c1, Chromosome c2, Random random) throws IllegalArgumentException{

        if(c1 == null || c1.getValues() == null || c1.getValues().isEmpty())
            throw new IllegalArgumentException();

        if(c2 == null || c2.getValues() == null || c2.getValues().isEmpty())
            throw new IllegalArgumentException();

        Double a = random.nextDouble();
        Double aMinusOne = ONE - a.doubleValue();

        Integer i = random.nextInt(c1.getValues().size());
        Component x1 = c1.getValues().get(i);
        Component x2 = c2.getValues().get(i);

        Double x1Line = (a * x1.value) + (aMinusOne * x2.value);
        Double x2Line = (aMinusOne * x1.value) + (a * x2.value);

        List<Component> componentList = new ArrayList<Component>(c1.getValues());
        componentList.remove(i);
        componentList.add(i, new Component(x1.min, x1.max, x1Line));

        List<Component> componentListY = new ArrayList<Component>(c2.getValues());
        componentListY.remove(i);
        componentListY.add(i, new Component(x2.min, x2.max, x2Line));

        return Arrays.asList(new Chromosome(componentList), new Chromosome(componentListY));
    }
}
