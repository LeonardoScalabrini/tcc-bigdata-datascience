package ga;

public class Fitness implements Runnable, Comparable<Fitness>{

    public final Chromosome chromosome;

    private Double value;

    public Fitness(Chromosome chromosome) {
        this.chromosome = chromosome;
    }

    public void run() {
        Double aptidao = 0.0;
        for (Component c : chromosome.getValues()) {
            aptidao += c.value;
        }
        this.value = aptidao;
    }

    public int compareTo(Fitness outherFitness) {
        if (this.value > outherFitness.getValue())
            return -1;

        if (this.value < outherFitness.getValue())
            return 1;

        return 0;
    }

    public Double getValue(){
        return value;
    }
}
