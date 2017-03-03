package Chapter_1.Ex_1_1_30;

import java.util.Date;

/**
 * Created by vl on 25.06.16.
 */

/*
1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array
a[][] such that a[i][j] is true if i and j are relatively prime (have no common fac-
tors), and false otherwise.
 */
public class IndexPrimeArray {

    public static void main(String[] args) {

        // Начало процесса
        Date startTime = new Date();

        boolean[][] array = getIndexPrimeArray(6);

        // Время формирования массива
        Date getIndexPrimeArrayTime = new Date();

        for (int i = 0; i < array.length; i++) {
            System.out.println();
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
        }
        // Время окончания процесса
        Date endTime = new Date();

        long getIndexPrimeArrayTimeRes = getIndexPrimeArrayTime.getTime() - startTime.getTime();
        long systemOutTime = endTime.getTime() - getIndexPrimeArrayTime.getTime();

        System.out.println("");
        System.out.println("");
        System.out.println("Время формирования массива: " + getIndexPrimeArrayTimeRes + " ms");
        System.out.println("Время вывода массива: " + systemOutTime + " ms");

    }

    public static boolean[][] getIndexPrimeArray (int arSize)
    {   // создание boolean массива
        boolean[][] resArray = new boolean[arSize][arSize];
        for (int i = 0; i < arSize; i++) {
            resArray[i][i] = false;
            for (int j = 0; j < i; j++) {
                resArray[i][j] = resArray [j][i]= (gcd(i, j) <= 1);
            }
        }
        return resArray;
    }

    public static int gcd(int p, int q) {
        int r;
        while (q != 0) {
            r = p % q;
            p = q;
            q = r;
        }
        return p;
    }

}
