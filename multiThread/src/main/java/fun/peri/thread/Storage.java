package fun.peri.thread;

public class Storage {

    private int index = 0;
    Apple[] apples = new Apple[5];

    public synchronized void push(Apple apple) {
        while (index == apples.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apples[index] = apple;
        index++;
        System.out.println(Thread.currentThread().getName() + "生产了" + apple.getAppleId() + "号苹果");
        this.notify();
    }

    public synchronized Apple pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        index--;
        Apple apple = apples[index];
        System.out.println(Thread.currentThread().getName() + "存储了" + apple.getAppleId() + "号苹果");
        this.notify();
        return apple;
    }
}
