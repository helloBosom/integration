package fun.peri.arithmetic;

import fun.peri.arithmetic.util.GenerateArray;
import fun.peri.arithmetic.util.SortArray;

import java.util.Random;

/**
 * @author logic
 * merge array
 */
public class MergeArray {
    public static void main(String[] args) {
        Random random = new Random();
        double[] array = GenerateArray.generateDoubleArray(random.nextInt(10) + 1);
        array = SortArray.quickSortArray(array);
        double[] number = GenerateArray.generateDoubleArray(random.nextInt(10) + 1);
        number = SortArray.quickSortArray(number);
        double[] result = mergeArray(array, number);
        System.out.println("the length of merged array is:" + result.length);
        System.out.println("array merge result:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    static double[] mergeArray(double[] array, double[] number) {
        double[] result = null;
        /**
         *the case union
         */
        if (array[array.length - 1] <= number[0]) {
            result = new double[array.length + number.length];
            System.arraycopy(array, 0, result, 0, array.length);
            System.arraycopy(number, 0, result, array.length, number.length);
        } else if (array[0] >= number[number.length - 1]) {
            result = new double[array.length + number.length];
            System.arraycopy(number, 0, result, 0, number.length);
            System.arraycopy(array, 0, result, number.length, array.length);
        } else if (array[array.length - 1] <= number[number.length - 1]) {
            /**
             * the case of subset
             */
            if (array[0] >= number[0]) {
                result = mergeUnion(number, array);
            }
            /**
             * the case of intersection
             */
            else {
                mergeIntersection(array, number);
            }
        } else if (array[array.length - 1] >= number[number.length - 1]) {
            /**
             * the case of subset
             */
            if (array[0] <= number[0]) {
                result = mergeUnion(array, number);
            }
            /**
             * the case of intersection
             */
            else {
                result = mergeIntersection(number, array);
            }
        }
        return result;
    }

    /**
     * merge union
     *
     * @param number children
     * @param array  pattern
     * @return
     */
    static double[] mergeUnion(double[] number, double[] array) {
        double[] result = null;
        double[] temp = number;
        for (int i = 0; i < array.length; i++) {
            result = insert(temp, array[i]);
            temp = result;
        }
        return result;
    }

    /**
     * @param array
     * @param number max number large
     * @return
     */
    static double[] mergeIntersection(double[] array, double[] number) {
        double[] result = new double[array.length + number.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= number[0]) {
                index1 = i;
            }
        }
        for (int i = 0; i < number.length; i++) {
            if (array[array.length - 1] >= number[i]) {
                index2 = i;
            }
        }
        System.arraycopy(array, 0, result, 0, index1);
        System.arraycopy(number, index2, result, index1, number.length - index2);
        for (; index1 < array.length; index1++) {
            result = insert(result, array[index1]);

        }
        for (int j = 0; j < index2; j++) {
            result = insert(result, number[j]);
        }
        return result;
    }

    static double[] insert(double[] array, double number) {
        int index = -1;
        int i;
        for (i = 0; i < array.length; i++) {
            if (number <= array[i]) {
                index = i;
                break;
            }
        }
        double[] result = new double[array.length + 1];
        if (index == -1) {
            System.arraycopy(array, 0, result, 0, array.length);
            result[array.length] = number;
        } else {
            System.arraycopy(array, 0, result, 0, index);
            result[index] = number;
            System.arraycopy(array, index, result, index + 1, array.length - index);
        }
        return result;
    }

}
