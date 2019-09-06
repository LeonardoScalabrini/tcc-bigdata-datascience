package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GA {

    public void execute(Integer populationSize, Integer iterations) throws ChromosomeNotFoundException {

        Random random = new Random();
        RandomComponent randomComponent = new RandomComponent(random);
        List<Chromosome> population = new ArrayList<Chromosome>();
        for (int i = 0; i < populationSize; i++) {
            //population.add(new Chromosome())
        }

        for (int i = 1; i < iterations; i++) {

            List<Fitness> fitnesses = new ArrayList<Fitness>();
            ExecutorService executor = Executors.newFixedThreadPool(populationSize);
            for (Chromosome chromosome : population) {
                Fitness fitness = new Fitness(chromosome);
                fitnesses.add(fitness);
                executor.execute(fitness);
            }
            executor.shutdown();
            while(!executor.isTerminated());

            List<Chromosome> newPopulation = new ArrayList<Chromosome>();
            ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals(fitnesses);
            newPopulation.addAll(elitismTwoIndividuals.elect());
            RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection(fitnesses, random);
            ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
            for (int r = newPopulation.size(); r < populationSize; r+=2) {
                Chromosome c1 = rouletteWheelSelection.selection();
                Chromosome c2 = rouletteWheelSelection.selection();
                arithmeticCrossover.
            }
        }


    }
    //seleção e calculo de aptidao
    //reproducao
    public void sobreviver(){
        //List<Chromosome> sobreviventes = new ArrayList<Chromosome>();
        //for (Chromosome chromosome : chromosomes) {

            //double aptidao = chromosome.getFitness().doubleValue() / 5;
            //double random = Math.random();

            //if(aptidao >= random)
                //sobreviventes.add(chromosome);
        //}

        //chromosomes.clear();
        //chromosomes.addAll(sobreviventes);
    }

    public void reproducao(){
        //int numeroFilhos = tamanho - chromosomes.size();
        //int numeroPais = chromosomes.size();
        //List<Chromosome> filhos = new ArrayList<Chromosome>();
        //for (int i = 0; i < numeroFilhos; i++) {

            //Chromosome chromosomePai = chromosomes.get(Math.round(numeroPais - 1));
            //Chromosome chromosomeMae = chromosomes.get(Math.round(numeroPais - 1));

            //Integer genePai = chromosomePai.getValues().get(Math.round(chromosomePai.getValues().size() - 1));
            //Integer geneMae = chromosomeMae.getValues().get(Math.round(chromosomeMae.getValues().size() - 1));

            //List<Integer> cromossomoFilho = new ArrayList<Integer>();
            //cromossomoFilho.add(genePai);
            //cromossomoFilho.add(geneMae);
            //cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            //cromossomoFilho.add(Math.random() >= 0.5 ? 1 : 0);
            //Collections.shuffle(cromossomoFilho);
            //filhos.add(new Chromosome(cromossomoFilho));
        //}

        //chromosomes.addAll(filhos);
    }

    public void mutacao(){
        //for (int i = 0; i < Math.round(tamanho * 0.1); i++) {
            //Chromosome chromosome = chromosomes.get(Math.round(chromosomes.size() - 1));
            //List<Integer> cromossomo = chromosome.getValues();
            //cromossomo.add(Math.round(3), Math.random() >= 0.5 ? 1 : 0);
        //}
    }

    public void resultado(){
        //for (Chromosome chromosome : chromosomes) {
            //System.out.println(chromosome.getFitness());
        //}
    }
}
