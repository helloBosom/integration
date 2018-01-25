package fun.peri.tickets;

import java.util.Random;

/**
 * Realizing the random output of 26 capital letters
 */
public class RandomThread extends Thread {
    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.print(r.nextInt(26) + ",");
        }
    }
}
