package fun.peri.datastructure;

/**
 * feibonacci number
 *
 * @author logic
 */
public class Feibonacci {
    public static long feibo(int n) {
        int fibonaccValue = 0;
        if (n < 0)
            return -1;
        else if (n == 0 || n == 1)
            return n;
        else {
            int beforeOne = 1;
            int beforeTwo = 1;
            for (int i = 0; i < n; i++) {
                fibonaccValue = beforeOne + beforeTwo;
                beforeOne = beforeTwo;
                beforeTwo = fibonaccValue;
            }
        }
        return fibonaccValue;
    }
}
