package Ex_1_1_15;


/**
 * Created by vl on 23.06.16.
 */

/*
1.1.15 Write a static method histogram() that takes an array a[] of int values and
an integer M as arguments and returns an array of length M whose i th entry is the num-
ber of times the integer i appeared in the argument array. If the values in a[] are all
between 0 and M–1 , the sum of the values in the returned array should be equal to
a.length.
 */

public class Histogram {

    public static void main(String[] args) {
        int[] array = {0, -1, -2, 3, 4, 5, 6, 0, 2, 4, 6, 8, 8, 8, 8, 8};
        int[] resAr = histogram(array, 10);
        for (int a : resAr) System.out.println(a);
    }

    public static int[] histogram (int[] a, int M) {

        int[] retArray = new int[M];

        for (int num : a) {
            if (num >= 0 && num < M) retArray[num]++;
        }
        return retArray;
    }
}
