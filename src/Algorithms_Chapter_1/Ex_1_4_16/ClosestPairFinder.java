package Algorithms_Chapter_1.Ex_1_4_16;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by vl on 20.03.17.
 */
public class ClosestPairFinder {
    public static double[] closestPair(double[] a) {
        double[] result = new double[2];

        Arrays.sort(a); // O(n log(n))

        double difference = Double.MAX_VALUE;

        int index = 0;

        for (int i = 0; i < a.length - 1; i++) {
            double temp = Math.abs(a[i + 1] - a[i]);
            if (temp < difference) {
                difference = temp;
                index = i;
            }
        }

        result[0] = a[index];
        result[1] = a[index + 1];

        return result;
    }

    public static void main(String[] args) {

        double[] a = new double[100];

        int MAX = 1000000;

        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }

        double[] closestPair = closestPair(a);

        System.out.println("Closest pair:");
        for (int i = 0; i < closestPair.length; i++) {
            System.out.println(closestPair[i]);
        }
    }
}