package fun.peri.arithmetic.Util;

/**
 * interchange int number
 */
public class InterchageNumber {

    public static void changeByTemp(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("change result:" + a + "," + b);
    }

    public static void changeByMath(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("change result:" + a + "," + b);
    }

    public static void changeByExclusiveOr(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("change result:" + a + "," + b);
    }

}