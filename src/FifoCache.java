public class FifoCache extends CacheSim {
    FifoCache(int cacheSize, double meanArrTime) {
        super(cacheSize, meanArrTime);
    }

    @Override
    void replace(int k) {
        cache.remove(0);
        cache.add(k);
    }

}
