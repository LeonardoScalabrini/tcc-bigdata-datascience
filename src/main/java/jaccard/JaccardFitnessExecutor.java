package jaccard;

import ga.Chromosome;
import ga.Fitness;
import ga.FitnessExecutor;
import jgibblda.LDAWithOutCmdOption;

import java.util.Map;
import java.util.Set;

public class JaccardFitnessExecutor implements FitnessExecutor {

    private final String dir;
    private final String file;
    private final LDAWithOutCmdOption ldaWithOutCmdOption;
    private final LDAClusterManager ldaClusterManager = new LDAClusterManager();
    private final Map<String, Set<Integer>> mapIssue;

    public JaccardFitnessExecutor(String dir, String file, Map<String, Set<Integer>> mapIssue) {
        this.dir = dir;
        this.file = file;
        this.ldaWithOutCmdOption = new LDAWithOutCmdOption(dir, file);
        this.mapIssue = mapIssue;
    }

    @Override
    public Fitness execute(Chromosome c) {
        //[k, n, α, β].
        int k = c.getValues().get(0).value.intValue();
        int n = c.getValues().get(1).value.intValue();
        Double alpha = c.getValues().get(2).value;
        Double beta = c.getValues().get(3).value;

        Double value = Double.MIN_VALUE;
        String fileName = "";
        try {
            fileName = ldaWithOutCmdOption.run(k, n, alpha, beta);
            Map<Integer, Set<Integer>> map = ldaClusterManager.cluster(dir, fileName);
            value = new CalculateAverageJaccard().calcule(mapIssue, map);
        }catch (Exception e){
            System.out.println("K: " + k);
            System.out.println("N: " + n);
            System.out.println("ALPHA: " + alpha);
            System.out.println("BETA: " + beta);
            System.out.println("FILE_NAME: " + fileName);
            throw e;
        }
        return new Fitness(c, value);
    }
}
