package fun.peri.arithmetic.util;

/**
 * factorial
 */
public class Factorial {

    public static int Factorial(int n) {
        if (n < 0) {
            System.out.println("error parameter of factorial");
        }
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * Factorial(n - 1);
        }
    }
}
