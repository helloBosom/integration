package com.logic.dataStructure;

import java.util.Scanner;

public class Question2 {

    public static void main(String[] args) {
        int[] arr = getArray();
        int[] count = count(arr);
        System.out.println("the average number of array:" + count[0]);
        System.out.println("the count number of array which greater than average:" + count[1]);
        System.out.println("the count number of array which less than average:" + count[2]);
    }

    public static int[] getArray() {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int[] count(int[] array) {
        int sum = 0;
        int average;
        int up = 0;
        int down = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        average = sum / array.length;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > average) {
                up++;
            } else if (array[i] < average) {
                down++;
            }
        }
        int[] count = {average, up, down};
        return count;
    }

}
