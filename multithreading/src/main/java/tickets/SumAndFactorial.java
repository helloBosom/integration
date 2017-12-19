package tickets;

/**
 * cumulation test
 */
public class SumAndFactorial {
    public static void main(String[] args) {
        System.out.println("enter you number for cumulation:");
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("enter you number for factorial:");
        Factorial thread1 = new Factorial();
        thread1.start();
    }
}
