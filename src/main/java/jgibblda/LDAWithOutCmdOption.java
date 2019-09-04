package jgibblda;

public class LDAWithOutCmdOption {
    public static void main(String args[]){
        LDACmdOption ldaCmdOption = new LDACmdOption();
        ldaCmdOption.est = true;
        ldaCmdOption.K = 3;
        ldaCmdOption.alpha = 0.01;
        ldaCmdOption.beta = 0.01;
        ldaCmdOption.niters = 100;
        ldaCmdOption.dir = "C:\\Users\\Leonardo\\tcc-bigdata-datascience\\JGibbLDA-v.1.0";
        ldaCmdOption.dfile = "src/test/resources/teste.txt";
        Estimator estimator = new Estimator();
        estimator.init(ldaCmdOption);
        estimator.estimate();
    }
}
