package fun.peri.design.create.singleton;

/**
 * 懒汉-线程安全0
 * 加锁
 * 会造成线程阻塞
 */
public class LazySafeSingleton {

    private static LazySafeSingleton uniqueInstance;

    private LazySafeSingleton() {

    }

    public static LazySafeSingleton getUniqueInstance() {
        if (null == uniqueInstance) {
            uniqueInstance = new LazySafeSingleton();
        }
        return uniqueInstance;
    }

}
