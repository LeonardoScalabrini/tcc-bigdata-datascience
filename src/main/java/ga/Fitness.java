package ga;

public class Fitness implements Comparable<Fitness>{

    public final Chromosome chromosome;

    public final Double value;

    public Fitness(Chromosome chromosome, Double value) {
        this.chromosome = chromosome;
        this.value = value;
    }

    public int compareTo(Fitness outherFitness) {
        if (this.value > outherFitness.value)
            return -1;

        if (this.value < outherFitness.value)
            return 1;

        return 0;
    }
}
