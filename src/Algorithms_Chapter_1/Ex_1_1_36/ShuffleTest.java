package Algorithms_Chapter_1.Ex_1_1_36;

import edu.princeton.cs.algs4.StdRandom;
import java.io.IOException;

/**
 * Created by vl on 25.06.16.
 */

/*
1.1.36 Empirical shuffle check. Run computational experiments to check that our
shuffling code on page 32 works as advertised. Write a program ShuffleTest that takes
command-line arguments M and N, does N shuffles of an array of size M that is initial-
ized with a[i] = i before each shuffle, and prints an M-by-M table such that row i
gives the number of times i wound up in position j for all j . All entries in the array
should be close to N/M.
 */

public class ShuffleTest {

    public static void main(String[] args) throws IOException {
        try {
            if (args.length == 2) {
                int m = Integer.parseInt(args[0]);
                int n = Integer.parseInt(args[1]);
                shuffleTest(m, n);
            }
            else {
                System.out.println("Необходимо ввести два целых числа через пробел");
                System.exit(1);
            }
        }
        catch (NumberFormatException e1) {
            System.out.println("Необходимо ввести целые числа");
            System.exit(1);
        }

    }

    public static void shuffleTest (int m, int n) {

        int[][] resArray = new int[m][m];
        //заполнение resArray нулями
        for (int i = 0; i < resArray.length; i++) {
            for (int j = 0; j < resArray[i].length; j++) {
                resArray[i][j] = 0;
            }
        }
        // перемешка массива a, n раз
        for (int i = 0; i < n; i++) {
            int[] a = new int[m];
            //инициализация массива (a[i]=i)
            for (int j = 0; j < a.length; j++) {
                a[j] = j;
            }
            shuffle(a);

            // для проверки - вывод перемешанных массивов
//            for (int j = 0; j < a.length; j++) {
//                System.out.print(a[j] + " ");
//            }

            for (int j = 0; j < a.length; j++) {
                resArray[a[j]][j]++;
            }

            // для проверки - разделение между выведенными перемешанными массивами и рез.масс.
//            System.out.println("");

        }
        System.out.println("");
        // занесение данных в результирующий массив
        for (int i = 0; i < resArray.length; i++) {
            for (int j = 0; j < resArray[i].length; j++) {
                System.out.print(resArray[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void shuffle(int[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        { // Exchange a[i] with random element in a[i..N-1]
            int r = i + StdRandom.uniform(N-i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}
