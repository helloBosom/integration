package tickets;

/**
 * book ticket thread
 * 使用Runnable接口可以为多个线程提供共享的数据。
 */
public class BookingThread implements Runnable {
    private int count = 100;
    Object lock = new Object();

    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + " buy a ticket,remaining tickets:" + count);
                    count--;
                }
            }
        }
    }
}
