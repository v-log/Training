package Algorithms_Chapter_1.Ex_1_4_20;

/**
 * Created by vl on 20.03.17.
 */
public class BitonicSearch {
    public static boolean bitonicSearch(int[] bitonicArray, int key) {

        int maxIndex = bitonicMax(bitonicArray);

        if (key == bitonicArray[maxIndex]) {
            return true;
        }

        if (rankDirect(bitonicArray, maxIndex, key)) {
            return true;
        }

        if (rankIndirect(bitonicArray, maxIndex, key)) {
            return true;
        }

        return false;
    }

    private static int bitonicMax(int[] bitonicArray) {
        int max = 0;
        int lo = 0;
        int hi = bitonicArray.length - 1;

        while (lo <= hi) {
            max = lo + (hi - lo) / 2;

            if (hi != max && bitonicArray[max + 1] - bitonicArray[max] > 0) {
                lo = max + 1;
            } else if (lo != max && bitonicArray[max] - bitonicArray[max - 1] < 0) {
                hi = max - 1;
            } else break;
        }

        return max;
    }

    private static boolean rankDirect(int[] bitonicArray, int max, int key) {
        int lo = 0;
        int hi = max - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key < bitonicArray[mid]) {
                hi = mid - 1;
            } else if (key > bitonicArray[mid]) {
                lo = mid + 1;
            } else return true;
        }

        return false;
    }

    private static boolean rankIndirect(int[] bitonicArray, int max, int key) {
        int lo = max + 1;
        int hi = bitonicArray.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key > bitonicArray[mid]) {
                hi = mid - 1;
            } else if (key < bitonicArray[mid]) {
                lo = mid + 1;
            } else return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] bitonicArray0 = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 9, 8, 0, -1, -3, -5, };

        System.out.println(bitonicSearch(bitonicArray0, -6));
    }
}