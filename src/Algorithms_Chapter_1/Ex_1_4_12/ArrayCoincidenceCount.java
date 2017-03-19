package Algorithms_Chapter_1.Ex_1_4_12;

/**
 * Created by vl on 20.03.17.
 */
public class ArrayCoincidenceCount {

    public static void coincidenceCount(int[] a, int[] b) {

        int steps = 0;
        int k = 0;

        for (int i = 0; i < a.length; i++) {

            for (int j = k; j < b.length; j++) {

                steps++;

                if (b[j] > a[i]) {
                    k = j;
                    break;
                }

                if (a[i] == b[j]) {
                    k = j + 1;
                    System.out.printf(a[i] + " ");
                    break;
                }
            }
        }

        System.out.println('\n' + "Steps = " + steps);
    }


    public static void main(String[] args) {

        int[] a = new int[100];
        int[] b = new int[100];

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        for (int i = 0; i < b.length; i++) {
            b[i] = i * 2;
        }

        coincidenceCount(a, b);
    }
}