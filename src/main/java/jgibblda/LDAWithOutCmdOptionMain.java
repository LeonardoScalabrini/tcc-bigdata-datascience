package jgibblda;

import jaccard.LDAClusterManager;

public class LDAWithOutCmdOptionMain {

    public static void main(String... args){
        /*
        K: 100
        N: 500
        ALPHA: 0.4012964975254947
        BETA: 0.005337783161829646
        FILE_NAME: 6990d9c7-80c4-44d9-b41c-69a3947042cf
        * */
        String dir = "C:\\Users\\Leonardo\\tcc-guava";
        String file = "guava.txt";
        LDAWithOutCmdOption ldaWithOutCmdOption = new LDAWithOutCmdOption(dir, file);
        String fileName = ldaWithOutCmdOption.run(100, 500, 0.401, 0.005);
        new LDAClusterManager().cluster(dir, fileName);
        System.out.println(fileName);
    }
}
