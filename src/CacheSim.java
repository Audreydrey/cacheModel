import java.util.*;

public abstract class CacheSim {
    List<Integer> cache = new ArrayList<>();
    private double sampleSum = 0;
    private double sampleSqSum = 0;
    private double meanArrTime;
    private int hitCount;
    private int missCount;
    private double time;
    private int n;

    CacheSim(int cacheSize, double meanArrTime) {
        //fill the cache with 1 to m
        for(int i = 1; i <= cacheSize; i++) {
            this.cache.add(i);
        }
        this.meanArrTime = meanArrTime;

    }

    private void nextArr() {
        double interval = -Math.log(Math.random()) / meanArrTime;
        //System.out.println(interval);
        time += interval;
        double u = Math.random() * meanArrTime;
        int k = 1;
        while(u > 0) {
            u -= (double) 1 / k;
            k++;
        }
        if(cache.contains(k)) {
            hitCount++;
        } else {
            missCount++;
            replace(k);
        }
    }

    protected void run() {
        time = 0;
        hitCount = 0;
        missCount = 0;
        while(time < RunCacheSim.runLength ) {
            nextArr();
        }
        double obs = (double) missCount / (hitCount + missCount);
        //System.out.println(obs);
        sampleSum += obs;
        sampleSqSum += Math.pow(obs, 2);
    }

    void displayResult(int n, double alpha) {
        this.n = n;
        double mean = sampleMean();
        double width = ciHalfWidth(alpha);
        System.out.println("Sample Mean: " + mean);
        System.out.println(" CI : " + (mean - width) + " - " + (mean + width));

    }

    public double sampleMean() {
        return sampleSum / n;
    }

    public double sampleVariance() {
        return (sampleSqSum - (Math.pow(sampleSum, 2) / n)) / (n - 1);
    }

    public double ciHalfWidth(double alpha) {
        double s = Math.sqrt(sampleVariance());
        double z = StudentstTable.getPercentile(n - 1, alpha);
        return z * s / Math.sqrt(n);
    }
    abstract void replace(int k);
}
