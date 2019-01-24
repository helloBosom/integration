package fun.peri.design.create.singleton;

/**
 * 懒汉式-线程不安全
 * 多个线程同时进入if (null == uniqueInstance)且uniqueInstance为null，会实例化多次uniqueInstance
 * 延迟静态变量实例化，节约资源
 */
public class LazySingleton {

    private static LazySingleton uniqueInstance;

    private LazySingleton() {

    }

    public static LazySingleton getUniqueInstance() {
        if (null == uniqueInstance) {
            uniqueInstance = new LazySingleton();
        }
        return uniqueInstance;
    }

}
