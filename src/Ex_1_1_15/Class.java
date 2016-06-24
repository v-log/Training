package Ex_1_1_15;

import java.util.HashMap;
import java.util.Map;

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

public class Class {

//    public static void main(String[] args) {
//        int[] array = {0, 1, 2, 3, 4, 5, 6, 0, 2, 4, 6, 8, 8, 8, 8, 8};
//        int[] resAr = histogram(array, 10);
//        for (int a : resAr) System.out.println(a);
//    }

    public static int[] histogram (int[] a, int M) {

        int[] retArray = new int[M];
        Map<Integer, Integer> tempMap = new HashMap<>();

        for (int num: a) {
            if (tempMap.containsKey(num)) {
                int temp = tempMap.get(num);
                temp++;
                tempMap.put(num, temp);
            }
            else tempMap.put(num, 1);
        }

        for (int i = 0; i < M; i++) {
            if (tempMap.containsKey(i)) {
                retArray[i] = tempMap.get(i);
            }
            else retArray[i] = 0;
        }


        return retArray;
    }

}
