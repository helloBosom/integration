package fun.peri.design.create.singleton;

/**
 * 双重锁-线程安全
 * volatile禁止指令重排，保证内存可见性
 * 双重锁避免并发时多次实例化
 */
public class DoubleBlockSingleton {

    private volatile static DoubleBlockSingleton uniqueInstance;

    private DoubleBlockSingleton() {

    }

    public static DoubleBlockSingleton getUniqueInstance() {
        if (null == uniqueInstance) {
            synchronized (DoubleBlockSingleton.class) {
                if (null == uniqueInstance) {
                    uniqueInstance = new DoubleBlockSingleton();
                }
            }
        }
        return uniqueInstance;
    }

}
