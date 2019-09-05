package ga;

import java.util.Collections;
import java.util.List;

public class Chromosome implements Comparable<Chromosome>{

    private final List<Component> values;

    private final Integer fitness;

    public Chromosome(List<Component> values){
        this.values = values;
        this.fitness = fitness();
    }

    public Integer getFitness(){
        return fitness;
    }

    public List<Component> getValues(){
        return Collections.unmodifiableList(values);
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
        for (Component c : values) {
            aptidao += c.value.intValue();
        }
        return aptidao;
    }
}
