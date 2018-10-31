package fun.peri.arithmetic.Util;

import java.util.ArrayList;

/**
 * break number
 * @author logic
 */
public class BreakNumber {

    public static ArrayList<Integer> breakIntNumber(int number) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (number > 0) {
            int divisor = number % 10;
            list.add(divisor);
            number = number / 10;
        }
        return list;
    }
}