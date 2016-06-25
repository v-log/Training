package Ex_1_1_22;

/**
 * Created by vl on 25.06.16.
 */

/*
1.1.22 Write a version of BinarySearch that uses the recursive rank() given on page
25 and traces the method calls. Each time the recursive method is called, print the argument
values lo and hi, indented by the depth of the recursion. Hint: Add an argument
to the recursive method that keeps track of the depth.
 */

public class Rank {
//    public static void main(String[] args) {
//        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
//        System.out.println("rank = " + rank(4, array));
//    }

    public static int rank(int key, int[] a)
    {   return rank(key, a, 0, a.length - 1, 0);   }

    public static int rank(int key, int[] a, int lo, int hi, int depth)
    {   // Index of key in a[], if present, is not smaller than lo
        // and not larger than hi.
        depth++;
        System.out.println(depth + ".  lo: " + lo + "  hi: " + hi);
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, lo, mid - 1, depth);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, depth);
        else                   return mid;
    }
}
