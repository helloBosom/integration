package fun.peri.design.create.singleton;

/**
 * 饿汉-线程安全
 * 丢失延迟实例化优势
 */
public class EagerSingleton {

    private EagerSingleton() {

    }

    private static EagerSingleton uniqueInstance = new EagerSingleton();

    public static EagerSingleton getUniqueInstance() {
        if (null == uniqueInstance) {
            uniqueInstance = new EagerSingleton();
        }
        return uniqueInstance;
    }

}
