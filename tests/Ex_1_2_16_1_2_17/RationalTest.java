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

        // Тест на сокращение знаменателя и числителя на общий множитель
        assertEquals(new Rational(4, 3), new Rational(8, 6));

        assertEquals(new Rational(21, 3), new Rational(7, 1));

        // Тест на перевод минуса из знаменателя в числитель
        assertEquals(new Rational(3, -2), new Rational(-3, 2));

        assertEquals(new Rational(-3, -2), new Rational(3, 2));

        // Тест на успешное создание рац. числа из строкового аргумента
        assertEquals(new Rational(13, 7), new Rational("13/7"));
        assertEquals(new Rational(13, 1), new Rational("13"));
        assertEquals(new Rational(13, 1), new Rational("26/2"));

        try{
            Rational rat1 = new Rational("1/0");
            fail("Should throw ArithmeticException");
        }
        catch(ArithmeticException e1) { }
    }

    @Test
    public void numeratorTest() throws Exception {
        assertEquals(123,  new Rational(123, 1).numerator());
        assertEquals(117,  new Rational(117, 1).numerator());
        assertEquals(1029141085,  new Rational(1029141085, 1).numerator());
    }

    @Test
    public void denominatorTest() throws Exception {
        assertEquals(123,  new Rational(1, 123).denominator());
        assertEquals(117,  new Rational(1, 117).denominator());
        assertEquals(1029141085,  new Rational(1, 1029141085).denominator());
    }

    @Test
    public void plusTest() throws Exception {

        // Тест на сложение двух рац. чисел с общим знаменателем
        Rational rat1 = new Rational(14, 9).plus(new Rational(17, 9));
        assertEquals(new Rational(31, 9), rat1);

        // Тест на сложение двух рац. чисел с разными знаменателями
        Rational rat2 = new Rational(13, 4).plus(new Rational(11, 3));
        assertEquals(new Rational(83, 12), rat2);

        // Тест на сложение двух рац. чисел с разными знаменателями,
        // имеющими общий множитель
        Rational rat3 = new Rational(13, 6).plus(new Rational(15, 4));
        assertEquals(new Rational(71, 12), rat3);

        // Тест на сокращение в результате сложения числителя и знаменателя на общий множитель
        Rational rat4 = new Rational(13, 4).plus(new Rational(11, 4));
        assertEquals(new Rational(6, 1), rat4);

        // Тест на сложение двух рац. чисел с разными знаками
        Rational rat5 = new Rational(-7, 3).plus(new Rational(11, 3));
        assertEquals(new Rational(4, 3), rat5);

        Rational rat6 = new Rational(11, 3).plus(new Rational(-7, 3));
        assertEquals(new Rational(4, 3), rat6);

        // Тест на перехват переполнения при сложении
        try {
            Rational ratMin = new Rational(Long.MIN_VALUE, 1);
            Rational res1 = ratMin.plus( new Rational(-1, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational ratMax = new Rational(Long.MAX_VALUE, 1);
            Rational res1 = ratMax.plus( new Rational(1, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void minusTest() throws Exception {

        // Тест на вычитание двух рац. чисел с общим знаменателем
        Rational rat1 = new Rational(17, 9).minus(new Rational(16, 9));
        assertEquals(new Rational(1, 9), rat1);

        // Тест на вычитание двух рац. чисел с разными знаменателями
        Rational rat2 = new Rational(21, 4).minus(new Rational(11, 3));
        assertEquals(new Rational(19, 12), rat2);

        // Тест на вычитание двух рац. чисел с разными знаменателями,
        // имеющими общий множитель
        Rational rat3 = new Rational(13, 6).minus(new Rational(5, 4));
        assertEquals(new Rational(11, 12), rat3);

        // Тест на сокращение в результате вычитания числителя и знаменателя на общий множитель
        Rational rat4 = new Rational(19, 4).minus(new Rational(11, 4));
        assertEquals(new Rational(2, 1), rat4);

        // Тест на вычитание двух рац. чисел с разными знаками
        Rational rat5 = new Rational(-7, 3).minus(new Rational(7, 3));
        assertEquals(new Rational(-14, 3), rat5);

        Rational rat6 = new Rational(7, 3).minus(new Rational(-7, 3));
        assertEquals(new Rational(14, 3), rat6);

        // Тест на перехват переполнения при вычитании
        try {
            Rational ratMin = new Rational(Long.MIN_VALUE, 1);
            Rational res1 = ratMin.minus( new Rational(1, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational ratMax = new Rational(Long.MAX_VALUE, 1);
            Rational res1 = ratMax.minus( new Rational(-1, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void timesTest() throws Exception {

        // Тест умножения с результатом, числитель и знаменатель которого имеют общий множитель
        Rational rat1 = new Rational(9, 2).times(new Rational(8, 3));
        assertEquals(new Rational(12, 1), rat1);

        // Тест умножения с результатом, числитель и знаменатель не имеют общего множителя
        Rational rat2 = new Rational(9, 7).times(new Rational(4, 5));
        assertEquals(new Rational(36, 35), rat2);

        // Тест на перехват переполнения при перемножении двух рац.чисел
        try {
            Rational ratMinHalf = new Rational(Long.MIN_VALUE/2 - 1, 1);
            Rational res11 = ratMinHalf.times( new Rational(2, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational ratMaxHalf = new Rational(Long.MAX_VALUE/2 + 1, 1);
            Rational res11 = ratMaxHalf.times( new Rational(2, 1) );
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void dividesTest() throws Exception {

        // Тест деления с результатом, числитель и знаменатель которого имеют общий множитель
        Rational rat1 = new Rational(9, 5).divides(new Rational(8, 5));
        assertEquals(new Rational(9, 8), rat1);

        // Тест деления с результатом, числитель и знаменатель не имеют общего множителя
        Rational rat2 = new Rational(9, 5).divides(new Rational(8, 7));
        assertEquals(new Rational(63, 40), rat2);

        // Тест на перехват переполнения при делении двух рац.чисел
        try {
            Rational ratMinHalf = new Rational(Long.MIN_VALUE/2 - 1, 1);
            Rational res11 = ratMinHalf.divides(new Rational(1, 2));
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        try {
            Rational ratMaxHalf = new Rational(Long.MAX_VALUE/2 + 1, 1);
            Rational res11 = ratMaxHalf.divides(new Rational(1, 2));
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }
    }

    @Test
    public void equalsTest() throws Exception {

        // Тест на равность ссылок на один и тот же объект
        Rational rat1 = new Rational(17, 2);
        Rational rat2 = rat1;
        boolean res1 = rat1.equals(rat2);
        assertTrue("Should be equal", res1);

        // Тест на неравность различных рациональных чисел
        boolean res3 = new Rational(17, 2).equals(new Rational(19, 2));
        assertFalse("Should not be equal", res3);

        // Тест на равность двух одинаковых рациональных чисел
        Rational rat6 = new Rational(17,2);
        assertTrue("Should be equal", rat1.equals(rat6));
        assertTrue("Should be equal", rat6.equals(rat1));

        // Тест на неравность рационального числа и нулевого объекта
        assertFalse("Should not be equal", rat1.equals(null));

        // Тест на неравность объектов двух разных классов
        assertFalse("Should not be equal", rat1.equals(new Date(11, 14, 2003)));
    }

    @Test
    public void toStringTest() throws Exception{
        assertEquals("13241/16253", new Rational(13241, 16253).toString());
        assertEquals("-13241/16253", new Rational(-13241, 16253).toString());
        assertEquals("13241", new Rational(13241, 1).toString());
        assertEquals("0", new Rational(0, 11241).toString());
        assertEquals("0", new Rational("0").toString());
    }
}