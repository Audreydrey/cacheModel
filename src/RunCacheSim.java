public class RunCacheSim {
    static final double runLength = 1000;

    private double meanArrTime; //lamda
    private int cacheSize, memSize, repetition;
    double ciWidth;

    private RunCacheSim( int cacheSize, int memSize, int repetition, double ciWidth) {
        this.cacheSize = cacheSize;
        this.memSize = memSize;
        this.repetition = repetition;
        this.ciWidth = ciWidth;
        this.meanArrTime = calculateMeanArrT();
    }

    private double calculateMeanArrT() {
        double arr = 0;
        for(int i = 1; i <= memSize; i++) {
            arr += 1 / (double) i;
        }
        System.out.println(arr);
        return arr;
    }

    private void runOnce() {
        CacheSim oneRandSim = new RandCache(cacheSize, meanArrTime);
        CacheSim oneFifoSim = new FifoCache(cacheSize, meanArrTime);
        for(int i = 0; i < repetition; i++) {
            oneRandSim.run();
            oneFifoSim.run();
        }
        System.out.println("results for RAND cache :");
        oneRandSim.displayResult(repetition, ciWidth);
        System.out.println("results for FIFO cache :");
        oneFifoSim.displayResult(repetition, ciWidth);
    }


    public static void main(String[] args) {
//
//        int repetition = Integer.parseInt(args[0]);
//        int cacheSize = Integer.parseInt(args[1]);
//        int memSize = Integer.parseInt(args[2]);
//        double ciWidth = Double.parseDouble(args[3]);


        //RunCacheSim sim = new RunCacheSim(cacheSize, memSize, repetition, ciWidth);
        RunCacheSim sim = new RunCacheSim(100, 2000, 100, 95);
        sim.runOnce();
    }
}
