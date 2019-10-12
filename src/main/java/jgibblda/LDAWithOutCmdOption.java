package jgibblda;

import java.util.UUID;

public class LDAWithOutCmdOption {

    private final String dir;
    private final String file;

    public LDAWithOutCmdOption(String dir, String file) {
        this.dir = dir;
        this.file = file;
    }

    public String run(int k, int n, Double alpha, Double beta){
        String uuid = UUID.randomUUID().toString();
        LDACmdOption ldaCmdOption = new LDACmdOption();
        ldaCmdOption.est = true;
        ldaCmdOption.K = k;
        ldaCmdOption.alpha = alpha;
        ldaCmdOption.beta = beta;
        ldaCmdOption.niters = n;
        ldaCmdOption.dir = dir;
        ldaCmdOption.dfile = file;
        ldaCmdOption.savestep = n;
        ldaCmdOption.modelName = uuid;
        Estimator estimator = new Estimator();
        estimator.init(ldaCmdOption);
        estimator.estimate();
        return uuid;
    }
}
