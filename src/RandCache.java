public class RandCache extends CacheSim {
    RandCache(int cacheSize, double meanArrTime) {
        super(cacheSize, meanArrTime);
    }

    @Override
    void replace(int k) {
        int victim = (int) Math.floor(Math.random() * cache.size());
        cache.set(victim, k);
    }
}
