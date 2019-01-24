package fun.peri.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * judge Narcissistic number
 */
public class NarcissisticNumber {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<Long>();
        for (long i = 0; i < 200; i++) {
            if (Judge(i)) {
                list.add(i);
                System.out.print(i + " ");
            }
        }
    }

    public static boolean Judge(long number) {
        long getNumber;
        long sum = 0;
        long data = number;
        while (number > 0) {
            getNumber = number % 10;
            sum = sum + getNumber * getNumber * getNumber;
            number = number / 10;
        }
        return data == sum;
    }
}
