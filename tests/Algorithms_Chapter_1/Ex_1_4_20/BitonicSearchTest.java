package Algorithms_Chapter_1.Ex_1_4_20;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 03.04.17.
 */
public class BitonicSearchTest {
    @Test
    public void bitonicSearchTest() throws Exception {

        int[] bitonicArray0 = {1, 2, 3, 11, 12, 9, 5, 0, -2, -5};
        int[] bitonicArray1 = {1, 2, 3, 4, 5 };
        int[] bitonicArray2 = {1, 3, 2, };


        // Тест на наличие всех элементов в массиве
        assertTrue(checkForAllElements(bitonicArray0));

        assertTrue(checkForAllElements(bitonicArray1));

        assertTrue(checkForAllElements(bitonicArray2));


        // Тест на отсутствие элементов в массиве
        assertFalse(BitonicSearch.bitonicSearch(bitonicArray0, -1));

        assertFalse(BitonicSearch.bitonicSearch(bitonicArray0, 13));
    }

    private boolean checkForAllElements(int[] array) {
        boolean flag = true;

        for (int el : array) {
            if (!BitonicSearch.bitonicSearch(array, el)) flag = false;
        }

        return flag;
    }
}