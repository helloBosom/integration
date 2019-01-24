package fun.peri.tickets;

import java.util.Scanner;

/**
 * cumulation
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        int Sum = 0;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i <= n; i++) {
            Sum += i;
        }
        System.out.println(Sum);
    }
}
