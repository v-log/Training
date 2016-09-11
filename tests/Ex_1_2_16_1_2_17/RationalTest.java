package Ex_1_2_16_1_2_17;

import edu.princeton.cs.algs4.Date;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 11.09.16.
 */
public class RationalTest {
    @Test
    public void RationalTest() throws Exception {

        // Тест на перехват нуля в знаменателе
        try{
            Rational rat1 = new Rational(1, 0);
            fail("Should throw ArithmeticException");
        }
        catch(ArithmeticException e1) { }

        // Тест на перехват переполнения long (в числителе, в знаменателе)
        try {
            String a = "9223372036854775808";
            Rational rat1 = new Rational(Long.parseLong(a), 1);
            fail("Should throw NumberFormatException");
        }
        catch (NumberFormatException e1) { }

        try {
            String a = "-9223372036854775809";
            Rational rat1 = new Rational(Long.parseLong(a), 1);
            fail("Should throw NumberFormatException");
        }
        catch (NumberFormatException e1) { }

        try {
            String a = "9223372036854775808";
            Rational rat1 = new Rational(1, Long.parseLong(a));
            fail("Should throw NumberFormatException");
        }
        catch (NumberFormatException e1) { }

        try {
            String a = "-9223372036854775809";
            Rational rat1 = new Rational(1, Long.parseLong(a));
            fail("Should throw NumberFormatException");
        }
        catch (NumberFormatException e1) { }

        // Тест на сокращение знаменателя и числителя на общий множитель
        Rational rat5 = new Rational(8, 6);
        Rational rat6 = new Rational(4, 3);
        assertEquals(rat6, rat5);

        Rational rat7 = new Rational(21, 3);
        Rational rat8 = new Rational(7, 1);
        assertEquals(rat8, rat7);

        // Тест на перевод минуса из знаменателя в числитель
        Rational rat1 = new Rational(3, -2);
        Rational rat2 = new Rational(-3, 2);
        assertEquals(rat2, rat1);

        Rational rat3 = new Rational(-3, -2);
        Rational rat4 = new Rational(3, 2);
        assertEquals(rat4, rat3);
    }

    @Test
    public void numeratorTest() throws Exception {
        Rational rat1 = new Rational(123, 1);
        Rational rat2 = new Rational(117, 1);
        Rational rat3 = new Rational(1029141085, 1);
        long a1 = rat1.numerator();
        long a2 = rat2.numerator();
        long a3 = rat3.numerator();
        assertEquals(123, a1);
        assertEquals(117, a2);
        assertEquals(1029141085, a3);
    }

    @Test
    public void denominatorTest() throws Exception {
        Rational rat1 = new Rational(1, 123);
        Rational rat2 = new Rational(1, 117);
        Rational rat3 = new Rational(1, 1029141085);
        long a1 = rat1.denominator();
        long a2 = rat2.denominator();
        long a3 = rat3.denominator();
        assertEquals(123, a1);
        assertEquals(117, a2);
        assertEquals(1029141085, a3);
    }

    @Test
    public void plusTest() throws Exception {

        // Тест на сложение двух рац. чисел с общим знаменателем
        Rational rat5 = new Rational(14, 9);
        Rational rat6 = new Rational(17, 9);
        Rational res5 = rat5.plus(rat6);
        Rational res6 = new Rational(31, 9);
        assertEquals(res6, res5);

        // Тест на сложение двух рациональных чисел с разными знаменателями
        Rational rat1 = new Rational(13, 4);
        Rational rat2 = new Rational(11, 3);
        Rational res1 = rat1.plus(rat2);
        Rational res2 = new Rational(83, 12);
        assertEquals(res2, res1);

        // Тест на сложение двух рациональных чисел с разными знаменателями,
        // имеющими общий множитель
        Rational rat3 = new Rational(13, 6);
        Rational rat4 = new Rational(15, 4);
        Rational res3 = rat3.plus(rat4);
        Rational res4 = new Rational(71, 12);
        assertEquals(res4, res3);

        // Тест на сокращение в результате сложения числителя и знаменателя на общий множитель
        Rational rat7 = new Rational(13, 4);
        Rational rat8 = new Rational(11, 4);
        Rational res7 = rat7.plus(rat8);
        Rational res8 = new Rational(6, 1);
        assertEquals(res8, res7);

        // Тест на сложение двух рациональных чисел с разными знаками
        Rational rat13 = new Rational(-7, 3);
        Rational rat14 = new Rational(11, 3);
        Rational res13 = rat13.plus(rat14);
        Rational res14 = new Rational(4, 3);
        assertEquals(res14, res13);

        Rational rat15 = new Rational(11, 3);
        Rational rat16 = new Rational(-7, 3);
        Rational res15 = rat15.plus(rat16);
        Rational res16 = new Rational(4, 3);
        assertEquals(res16, res15);

        // Тест на перехват переполнения при сложении
        try {
            Rational rat9 = new Rational(9223372036854775807L, 1);
            Rational rat10 = new Rational(1, 1);
            Rational res9 = rat9.plus(rat10);
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational rat11 = new Rational(-9223372036854775808L, 1);
            Rational rat12 = new Rational(-1, 1);
            Rational res11 = rat11.plus(rat12);
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void minusTest() throws Exception {

        // Тест на вычитание двух рац. чисел с общим знаменателем
        Rational rat5 = new Rational(17, 9);
        Rational rat6 = new Rational(16, 9);
        Rational res5 = rat5.minus(rat6);
        Rational res6 = new Rational(1, 9);
        assertEquals(res6, res5);

        // Тест на вычитание двух рациональных чисел с разными знаменателями
        Rational rat1 = new Rational(21, 4);
        Rational rat2 = new Rational(11, 3);
        Rational res1 = rat1.minus(rat2);
        Rational res2 = new Rational(19, 12);
        assertEquals(res2, res1);

        // Тест на вычитание двух рациональных чисел с разными знаменателями,
        // имеющими общий множитель
        Rational rat3 = new Rational(13, 6);
        Rational rat4 = new Rational(5, 4);
        Rational res3 = rat3.minus(rat4);
        Rational res4 = new Rational(11, 12);
        assertEquals(res4, res3);

        // Тест на сокращение в результате вычитания числителя и знаменателя на общий множитель
        Rational rat7 = new Rational(19, 4);
        Rational rat8 = new Rational(11, 4);
        Rational res7 = rat7.minus(rat8);
        Rational res8 = new Rational(2, 1);
        assertEquals(res8, res7);

        // Тест на вычитание двух рациональных чисел с разными знаками
        Rational rat13 = new Rational(-7, 3);
        Rational rat14 = new Rational(7, 3);
        Rational res13 = rat13.minus(rat14);
        Rational res14 = new Rational(-14, 3);
        assertEquals(res14, res13);

        Rational rat15 = new Rational(7, 3);
        Rational rat16 = new Rational(-7, 3);
        Rational res15 = rat15.minus(rat16);
        Rational res16 = new Rational(14, 3);
        assertEquals(res16, res15);

        // Тест на перехват переполнения при вычитании
        try {
            Rational rat9 = new Rational(-9223372036854775808L, 1);
            Rational rat10 = new Rational(1, 1);
            Rational res9 = rat9.minus(rat10);
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational rat11 = new Rational(9223372036854775807L, 1);
            Rational rat12 = new Rational(-1, 1);
            Rational res11 = rat11.minus(rat12);
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void timesTest() throws Exception {

        // Тест умножения с результатом, числитель и знаменатель которого имеют общий множитель
        Rational rat1 = new Rational(9, 2);
        Rational rat2 = new Rational(8, 3);
        Rational res1 = rat1.times(rat2);
        Rational res2 = new Rational(12, 1);
        assertEquals(res2, res1);

        // Тест умножения с результатом, числитель и знаменатель не имеют общего множителя
        Rational rat3 = new Rational(9, 7);
        Rational rat4 = new Rational(4, 5);
        Rational res3 = rat3.times(rat4);
        Rational res4 = new Rational(36, 35);
    }

    @Test
    public void dividesTest() throws Exception {

        // Тест деления с результатом, числитель и знаменатель которого имеют общий множитель
        Rational rat1 = new Rational(9, 5);
        Rational rat2 = new Rational(8, 5);
        Rational res1 = rat1.divides(rat2);
        Rational res2 = new Rational(9, 8);
        assertEquals(res2, res1);

        // Тест деления с результатом, числитель и знаменатель не имеют общего множителя
        Rational rat3 = new Rational(9, 5);
        Rational rat4 = new Rational(8, 7);
        Rational res3 = rat3.divides(rat4);
        Rational res4 = new Rational(63, 40);
        assertEquals(res4, res3);
    }

    @Test
    public void equalsTest() throws Exception {

        // Тест на равность ссылок на один и тот же объект
        Rational rat1 = new Rational(17, 2);
        Rational rat2 = rat1;
        boolean res1 = rat1.equals(rat2);
        assertTrue("Should be equal", res1);

        // Тест на неравность различных рациональных чисел
        Rational rat3 = new Rational(19, 2);
        boolean res3 = rat1.equals(rat3);
        assertFalse("Should not be equal", res3);

        // Тест на равность двух одинаковых рациональных чисел
        Rational rat6 = new Rational(17,2);
        boolean res6 = rat1.equals(rat6);
        assertTrue("Should be equal", res6);
        boolean res7 = rat6.equals(rat1);
        assertTrue("Should be equal", res7);

        // Тест на неравность рационального числа и нулевого объекта
        Rational rat4 = null;
        boolean res4 = rat1.equals(rat4);
        assertFalse("Should not be equal", res4);

        // Тест на неравность объектов двух разных классов
        Date date1 = new Date(11, 14, 2003);
        boolean res5 = rat1.equals(date1);
        assertFalse("Should not be equal", res5);
    }
}