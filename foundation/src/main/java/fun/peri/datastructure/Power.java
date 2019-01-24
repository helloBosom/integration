package fun.peri.datastructure;

/**
 * calculate exponent
 */
public class Power {

    public static void main(String[] args) {
        double result = powerCount(2.0, -3);
        System.out.println(result);
    }

    public static double powerCount(double base, int exponent) {
        double result;
        boolean flag = false;
        if (base == 0.0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            flag = true;
            exponent = Math.abs(exponent);
        }
        result = powerCount(base, exponent >> 1);
        result = result * result;
        if ((exponent & 1) == 1) {
            result = result * base;
        }
        return flag ? 1.0 / result : result;
    }

}
