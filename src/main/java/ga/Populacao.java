package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Populacao {

    private final int tamanho;
    private List<Individuo> individuos = new ArrayList<Individuo>();


    public Populacao(int tamanho) {
        this.tamanho = tamanho;

        for (int i = 0; i < tamanho; i++) {
            individuos.add(new Individuo());
        }
    }

    //seleção e calculo de aptidao
    //reproducao
    public void sobreviver(){
        List<Individuo> sobreviventes = new ArrayList<Individuo>();
        for (Individuo individuo : individuos) {

            double aptidao = individuo.calcularAptidao().doubleValue() / 5;
            double random = Math.random();

            if(aptidao >= random)
                sobreviventes.add(individuo);
        }

        individuos.clear();
        individuos.addAll(sobreviventes);
    }

    public void reproducao(){
        int numeroFilhos = tamanho - individuos.size();
        int numeroPais = individuos.size();
        List<Individuo> filhos = new ArrayList<Individuo>();
        for (int i = 0; i < numeroFilhos; i++) {

            Individuo individuoPai = individuos.get(Math.round(numeroPais - 1));
            Individuo individuoMae = individuos.get(Math.round(numeroPais - 1));

            Integer genePai = individuoPai.getCromossomo().get(Math.round(individuoPai.getCromossomo().size() - 1));
            Integer geneMae = individuoMae.getCromossomo().get(Math.round(individuoMae.getCromossomo().size() - 1));

            List<Integer> cromossomoFilho = new ArrayList<Integer>();
            cromossomoFilho.add(genePai);
            cromossomoFilho.add(geneMae);
            cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            Collections.shuffle(cromossomoFilho);
            filhos.add(new Individuo(cromossomoFilho));
        }

        individuos.addAll(filhos);
    }

    public void mutacao(){
        for (int i = 0; i < Math.round(tamanho * 0.1); i++) {
            Individuo individuo = individuos.get(Math.round(individuos.size() - 1));
            List<Integer> cromossomo = individuo.getCromossomo();
            cromossomo.add(Math.round(3), Math.random() >= 0.5 ? 1 : 0);
        }
    }

    public void resultado(){
        for (Individuo individuo : individuos) {
            System.out.println(individuo.calcularAptidao());
        }
    }
}
