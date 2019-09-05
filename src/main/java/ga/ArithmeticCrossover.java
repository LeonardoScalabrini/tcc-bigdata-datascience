package ga;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArithmeticCrossover {

    private static final double ONE = 1.0;

    public List<Double> crossover(Double x1, Double x2, Random random) throws ChromosomeNotFoundException{

        if(x1 == null || x2 == null)
            throw new ChromosomeNotFoundException();

        Double a = random.nextDouble();

        Double aMinusOne = ONE - a.doubleValue();
        Double x1Line = (a * x1) + (aMinusOne * x2);
        Double x2Line = (aMinusOne * x1) + (a * x2);

        return Arrays.asList(x1Line, x2Line);
    }
}
