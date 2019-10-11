package jgibblda;

public class LDAWithOutCmdOption {
    public static void main(String args[]){
        LDACmdOption ldaCmdOption = new LDACmdOption();
        ldaCmdOption.est = true;
        ldaCmdOption.K = 3;
        ldaCmdOption.alpha = 0.01;
        ldaCmdOption.beta = 0.01;
        ldaCmdOption.niters = 100;
        ldaCmdOption.dir = "C:\\Users\\Leonardo\\tcc-bigdata-datascience\\src\\main\\resources\\";
        ldaCmdOption.dfile = "guava.txt";
        ldaCmdOption.savestep = 100;
        ldaCmdOption.modelName = "teste";
        Estimator estimator = new Estimator();
        estimator.init(ldaCmdOption);
        estimator.estimate();
    }
}
