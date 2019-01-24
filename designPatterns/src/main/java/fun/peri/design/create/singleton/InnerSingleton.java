package fun.peri.design.create.singleton;

/**
 * 静态内部类实现-线程安全
 * jvm确保uniqueInstance实例化一次
 */
public class InnerSingleton {

    private InnerSingleton() {

    }

    private static class SingletonHolder {
        private static final InnerSingleton uniqueInstance = new InnerSingleton();
    }

    public static InnerSingleton getUniqueInstance() {
        return SingletonHolder.uniqueInstance;
    }

}
