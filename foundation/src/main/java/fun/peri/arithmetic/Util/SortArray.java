package fun.peri.arithmetic.Util;

/**
 * sort array
 *
 * @author logic
 */
public class SortArray {

    public static double[] quickSortArray(double[] array) {
        if (array.length < 2) {
            return array;
        } else {
            double temp;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
        outputArray(array);
        return array;
    }

    public static double[] selectSort(double[] array) {
        if (array.length < 2) {
            return array;
        } else {
            for (int i = 0; i < array.length - 1; ) {
                int minindex = 0;
                for (int j = 0; i < array.length; ) {
                    if (array[j] > array[minindex]) {
                        minindex = j;
                    }
                }
            }
        }
        outputArray(array);
        return array;
    }

    private static void outputArray(double[] array) {
        System.out.println("array quick sort result:");
        for (double anArray : array) {
            System.out.println(anArray);
        }
    }
}