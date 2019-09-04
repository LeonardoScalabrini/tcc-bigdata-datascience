package ga;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {

    private List<Integer> values = new ArrayList<Integer>();

    public Chromosome(){
        for (int i = 0; i < 3; i++) {
            values.add(Math.random() >= 0.5 ? 1 : 0);
        }
    }

    public Chromosome(List<Integer> values){
        this.values = values;
    }

    public Integer fitness(){
        Integer aptidao = 0;
        for (Integer gene : values) {
            aptidao += gene;
        }
        return aptidao;
    }

    public List<Integer> getValues(){
        return values;
    }
}
