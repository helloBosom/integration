package fun.peri.arithmetic;

import fun.peri.arithmetic.Util.GenerateArray;

import java.util.*;

/**
 * the divisor of number
 *
 * @author logic
 */
public class Divisor {

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = GenerateArray.generateIntArray(random.nextInt(10) + 1, 1000);
        HashMap<Integer, ArrayList<Integer>> map = countDivisor(array);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            int number = entry.getKey();
            System.out.println("number:" + number);
            ArrayList<Integer> list = entry.getValue();
            System.out.println("the divisor except 1 and self of number:" + number);
            System.out.println(Arrays.toString(list.toArray()));
            System.out.println("size:" + list.size());
        }
    }

    static HashMap<Integer, ArrayList<Integer>> countDivisor(int[] number) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < number.length; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 2; j <= number[i] / 2 + 1; j++) {
                if (number[i] % j == 0) {
                    list.add(j);
                }
            }
            map.put(number[i], list);
        }
        return map;
    }
}
