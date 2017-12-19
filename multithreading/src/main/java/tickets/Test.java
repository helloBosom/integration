package tickets;

/**
 * 在Java类中使用super来引用基类的成分.
 * this是对当前对象的引用，是运行期间当前对象本身。
 * 可以使用this明确的访问当前对象的属性或者方法，类似于"我".
 * this() 可调用本类的其他构造器,
 * 可以使用构造器的重用简化代码的实现.
 * (this()必须写在构造器第一行) 调用当前方法
 */
public class Test {
    public static void main(String[] args) {
        RandomThread thread = new RandomThread();
        thread.start();
        RandomRunnable runnable = new RandomRunnable();
        Thread t = new Thread(runnable);
        t.start();
    }
}
