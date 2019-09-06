package ga;

import java.util.Collections;
import java.util.List;

public class Chromosome {

    private final List<Component> values;

    public Chromosome(List<Component> values){
        this.values = values;
    }

    public List<Component> getValues(){
        return Collections.unmodifiableList(values);
    }
}
