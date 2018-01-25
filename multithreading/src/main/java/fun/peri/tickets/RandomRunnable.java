package fun.peri.tickets;

import java.util.Random;

/**
 * Realizing the random output of 26 capital letters
 */
public class RandomRunnable implements Runnable {
    public void run() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            char c = (char) ('Z' - r.nextInt(26));
            System.out.print(c + " ");
        }
    }
}
