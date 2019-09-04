package ga;

import java.util.ArrayList;
import java.util.List;

public class Individuo {

    private List<Integer> cromossomo = new ArrayList<Integer>();

    public Individuo(){
        for (int i = 0; i < 3; i++) {
            cromossomo.add(Math.random() >= 0.5 ? 1 : 0);
        }
    }

    public Individuo(List<Integer> cromossomo){
        this.cromossomo = cromossomo;
    }

    public Integer calcularAptidao(){
        Integer aptidao = 0;
        for (Integer gene : cromossomo) {
            aptidao += gene;
        }
        return aptidao;
    }

    public List<Integer> getCromossomo(){
        return cromossomo;
    }
}
