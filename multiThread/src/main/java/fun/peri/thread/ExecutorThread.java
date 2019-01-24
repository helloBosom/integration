package fun.peri.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorThread {

    int limit;

    public ExecutorThread(int limit) {
        this.limit = limit;
    }

    ExecutorService limitThreadPool = Executors.newFixedThreadPool(limit);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService singleThread = Executors.newSingleThreadExecutor();

}
