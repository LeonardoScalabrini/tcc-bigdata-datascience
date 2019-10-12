package jaccard;

import ga.Chromosome;
import ga.Fitness;
import ga.FitnessExecutor;
import jgibblda.LDAWithOutCmdOption;

import java.util.Map;
import java.util.Set;

public class JaccardFitnessExecutor implements FitnessExecutor {

    private CalculateAverageJaccard calculateAverageJaccard = new CalculateAverageJaccard();
    private LDAClusterManager ldaClusterManager = new LDAClusterManager();
    private final LDAWithOutCmdOption ldaWithOutCmdOption;
    private final Map<String, Set<Integer>> mapIssue;

    public JaccardFitnessExecutor(LDAWithOutCmdOption ldaWithOutCmdOption, Map<String, Set<Integer>> mapIssue) {
        this.ldaWithOutCmdOption = ldaWithOutCmdOption;
        this.mapIssue = mapIssue;
    }

    @Override
    public Fitness execute(Chromosome c) {
        //[k, n, α, β].
        int k = c.getValues().get(0).value.intValue();
        int n = c.getValues().get(1).value.intValue();
        Double alpha = c.getValues().get(2).value;
        Double beta = c.getValues().get(3).value;
        String fileName = ldaWithOutCmdOption.run(k, n, alpha, beta);
        Map<Integer, Set<Integer>> map = ldaClusterManager.cluster(fileName);
        Double value = calculateAverageJaccard.calcule(mapIssue, map);
        return new Fitness(c, value);
    }
}
