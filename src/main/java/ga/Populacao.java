package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Populacao {

    private final int tamanho;
    private List<Chromosome> chromosomes = new ArrayList<Chromosome>();


    public Populacao(int tamanho) {
        this.tamanho = tamanho;

        for (int i = 0; i < tamanho; i++) {
            //chromosomes.add(new Chromosome());
        }
    }

    //seleção e calculo de aptidao
    //reproducao
    public void sobreviver(){
        List<Chromosome> sobreviventes = new ArrayList<Chromosome>();
        for (Chromosome chromosome : chromosomes) {

            double aptidao = chromosome.getFitness().doubleValue() / 5;
            double random = Math.random();

            if(aptidao >= random)
                sobreviventes.add(chromosome);
        }

        chromosomes.clear();
        chromosomes.addAll(sobreviventes);
    }

    public void reproducao(){
        int numeroFilhos = tamanho - chromosomes.size();
        int numeroPais = chromosomes.size();
        List<Chromosome> filhos = new ArrayList<Chromosome>();
        for (int i = 0; i < numeroFilhos; i++) {

            Chromosome chromosomePai = chromosomes.get(Math.round(numeroPais - 1));
            Chromosome chromosomeMae = chromosomes.get(Math.round(numeroPais - 1));

            //Integer genePai = chromosomePai.getValues().get(Math.round(chromosomePai.getValues().size() - 1));
            //Integer geneMae = chromosomeMae.getValues().get(Math.round(chromosomeMae.getValues().size() - 1));

            List<Integer> cromossomoFilho = new ArrayList<Integer>();
            //cromossomoFilho.add(genePai);
            //cromossomoFilho.add(geneMae);
            cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            Collections.shuffle(cromossomoFilho);
            //filhos.add(new Chromosome(cromossomoFilho));
        }

        chromosomes.addAll(filhos);
    }

    public void mutacao(){
        for (int i = 0; i < Math.round(tamanho * 0.1); i++) {
            Chromosome chromosome = chromosomes.get(Math.round(chromosomes.size() - 1));
            //List<Integer> cromossomo = chromosome.getValues();
            //cromossomo.add(Math.round(3), Math.random() >= 0.5 ? 1 : 0);
        }
    }

    public void resultado(){
        for (Chromosome chromosome : chromosomes) {
            System.out.println(chromosome.getFitness());
        }
    }
}
