package ga;

import java.util.ArrayList;
import java.util.List;

public class Chromosome implements Comparable<Chromosome>{

    private List<Integer> values = new ArrayList<Integer>();

    private final Integer fitness;

    public Chromosome(){
        for (int i = 0; i < 3; i++) {
            values.add(Math.random() >= 0.5 ? 1 : 0);
        }
        this.fitness = fitness();
    }

    public Chromosome(List<Integer> values){
        this.values = values;
        this.fitness = fitness();
    }

    public Integer getFitness(){
        return fitness;
    }

    public List<Integer> getValues(){
        return values;
    }

    public int compareTo(Chromosome outherChromosome) {
        if (this.getFitness() > outherChromosome.getFitness())
            return -1;

        if (this.getFitness() < outherChromosome.getFitness())
            return 1;

        return 0;
    }

    private Integer fitness() {
        Integer aptidao = 0;
        for (Integer gene : values) {
            aptidao += gene;
        }
        return aptidao;
    }
}
