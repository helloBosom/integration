package tickets;

/**
 * mimic ticket software
 */
public class MainThread {
    public static void main(String[] args) {
        BookingThread bookingThread = new BookingThread();
        Thread t1 = new Thread(bookingThread);
        Thread t2 = new Thread(bookingThread);
        Thread t3 = new Thread(bookingThread);
        Thread t4 = new Thread(bookingThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
