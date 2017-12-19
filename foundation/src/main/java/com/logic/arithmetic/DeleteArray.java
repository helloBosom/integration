package com.logic.arithmetic;

import com.logic.arithmetic.Util.GenerateArray;

import java.util.Random;

/**
 * @author logic
 * random delete array
 */
public class DeleteArray {
    public static void main(String[] args) {
        Random random = new Random();
        double[] array = GenerateArray.generateDoubleArray(10);
        deleteArray(array, random.nextInt(array.length));
    }

    static void deleteArray(double[] array, int i) {
        System.out.println("random delete index: " + i + " of number");
        System.out.println("random delete number is" + array[i]);
        if (array.length == 1) {
            array = new double[0];
        } else {
            for (; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }
        }
        System.out.println("random delete result : ");
        for (int j = 0; j < array.length; j++) {
            System.out.println(array[j]);
        }
    }
}
