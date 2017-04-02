package Algorithms_Chapter_1.Ex_1_4_20;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 03.04.17.
 */
public class BitonicSearchTest {
    @Test
    public void bitonicSearchTest() throws Exception {

        // Битонический массивы
        int[] bitonicArray0 = {1, 2, 3, 11, 12, 9, 5, 0, };
        int[] bitonicArray1 = {1, 2, 3, 4, 5 };
        int[] bitonicArray2 = {1, 2, 0, -1 };


        // Тест на нахождение элементов массива
        boolean flag0 = true;

        for (int el : bitonicArray0) {
            if (!BitonicSearch.bitonicSearch(bitonicArray0, el)) flag0 = false;
        }

        assertTrue(flag0);


        boolean flag1 = true;

        for (int el : bitonicArray1) {
            if (!BitonicSearch.bitonicSearch(bitonicArray1, el)) flag1 = false;
        }

        assertTrue(flag1);


        boolean flag2 = true;

        for (int el : bitonicArray2) {
            if (!BitonicSearch.bitonicSearch(bitonicArray2, el)) flag2 = false;
        }

        assertTrue(flag2);
    }
}