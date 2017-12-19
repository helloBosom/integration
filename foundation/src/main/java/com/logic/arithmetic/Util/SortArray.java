package com.logic.arithmetic.Util;

/**
 * sort array
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
        System.out.println("array quick sort result:");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        return array;
    }
}
