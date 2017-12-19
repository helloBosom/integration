package com.logic.arithmetic;

import com.logic.arithmetic.Util.GenerateArray;

import java.util.Random;

/**
 * @author logic
 * find max number ,min number and the index of those
 */
public class SearchMaxMinNumber {

    public static void main(String[] args) {
        Random random = new Random();
        double[] score = GenerateArray.generateDoubleArray(random.nextInt(10) + 1);
        search(score);
    }

    static void search(double[] score) {
        if (score.length < 2) {
            System.out.println("only one number:" + score[0]);
        } else {
            double max = score[0];
            double min = score[1];
            int index1 = 0;
            int index2 = 0;
            int j;
            for (j = 0; j < score.length; ++j) {
                if (max <= score[j]) {
                    max = score[j];
                    index1 = j;
                }
            }
            for (j = 0; j < score.length; ++j) {
                if (min >= score[j]) {
                    min = score[j];
                    index2 = j;
                }
            }
            System.out.println("max number:" + max);
            System.out.println("the index of max number:" + index1);
            System.out.println("min number:" + min);
            System.out.println("the index of min number:" + index2);
        }
    }
}
