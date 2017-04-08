package Algorithms_Chapter_1.Ex_1_4_20;

import java.util.Comparator;

/**
 * Created by vl on 20.03.17.
 */
public class BitonicSearch {
    public static boolean bitonicSearch(int[] bitonicArray, int key)
            throws IllegalArgumentException {

        if (bitonicArray.length < 3) {
            throw new IllegalArgumentException("Bitonic array must have at least 3 elements");
        }

        int maxIndex = bitonicMax(bitonicArray);

        return (key == bitonicArray[maxIndex])
                || rank( bitonicArray, 0, maxIndex - 1, key, (a, b) -> a.compareTo(b) )
                || rank( bitonicArray, maxIndex + 1, bitonicArray.length - 1,
                    key, (a, b) -> b.compareTo(a) );
    }

    private static int bitonicMax(int[] bitonicArray) {
        int max = 0;
        int lo = 0;
        int hi = bitonicArray.length - 1;

        while (lo <= hi) {
            max = (lo + hi) / 2;

            if (hi != max && bitonicArray[max + 1] > bitonicArray[max]) {
                lo = max + 1;
            } else if (lo != max && bitonicArray[max] < bitonicArray[max - 1]) {
                hi = max - 1;
            } else break;
        }

        return max;
    }

    private static boolean rank(int[] array, int beginIndex, int endIndex,
                               int key, Comparator<Integer> comp) {
        int lo = beginIndex;
        int hi = endIndex;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (comp.compare(key, array[mid]) < 0) {
                hi = mid - 1;
            } else if (comp.compare(key, array[mid]) > 0) {
                lo = mid + 1;
            } else return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] bitonicArray0 = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 9, 8, 0, -1, -3, -5, };

        System.out.println(bitonicSearch(bitonicArray0, 4));
    }
}