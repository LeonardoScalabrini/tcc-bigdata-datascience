package ga;

import jaccard.ClusterManager;
import jaccard.JaccardFitnessExecutor;
import jgibblda.LDAWithOutCmdOption;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GAMain {

    public static void main(String... args){

        String dir = "";
        String file = "";
        ClusterManager clusterManager = new ClusterManager();
        Map<String, Set<Integer>> mapIssue = clusterManager.cluster("guava");
        StartPopulationFactory startPopulationFactory = new AleatoryPopulationFactory();
        FitnessExecutor fitnessExecutor = new JaccardFitnessExecutor(new LDAWithOutCmdOption(dir, file), mapIssue);
        FitnessParallelFactory fitnessParallelFactory = new FitnessParallelFactory();

        ElitismTwoIndividuals elitismTwoIndividuals = new ElitismTwoIndividuals();
        RouletteWheelSelection rouletteWheelSelection = new RouletteWheelSelection();
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        UniformMutation uniformMutation = new UniformMutation();
        Random random = new Random();
        PopulationFactory evolutionPopulationFactory = new ElitismCrossoverMutationPopulationFactory(elitismTwoIndividuals,
                rouletteWheelSelection,
                arithmeticCrossover,
                uniformMutation,
                random);

        ConditionStopIteration conditionStopIteration = new ConditionTenStopIteration();

        GA ga = new GA(startPopulationFactory, fitnessExecutor, fitnessParallelFactory, evolutionPopulationFactory, conditionStopIteration);

        Integer populationSize = 100;
        Integer iterations = 500;
        Double crossoverProbability = 0.6;
        Double mutationProbability = 0.01;
        ga.execute(populationSize, iterations, crossoverProbability, mutationProbability);
    }
}
