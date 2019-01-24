package fun.peri.tickets;

import java.util.Scanner;

/**
 * factorial
 */
public class Factorial extends Thread {
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(factorial);
    }
}
