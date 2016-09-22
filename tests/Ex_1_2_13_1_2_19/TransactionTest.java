package Ex_1_2_13_1_2_19;

import Ex_1_2_12.SmartDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 12.09.16.
 */
public class TransactionTest {
    @Test
    public void TransactionTest1() throws Exception {
        // Тест на перехват неверного количества аргументов
        try {
            Transaction tran1 = new Transaction("Surn 11/16/2013 102.123 explicitArg");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        // Тест на перехват отрицательной суммы транзакции
        try {
            Transaction tran1 = new Transaction("Surn 11/16/2013 -102.123");
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

        // Тест на перехват неверного формата фамилии
        try {
            Transaction tran1 = new Transaction("surn 11/16/2013 102.123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Transaction tran1 = new Transaction("Surn9 11/16/2013 102.123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Transaction tran1 = new Transaction("12308 11/16/2013 102.123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Transaction tran1 = new Transaction("Surn* 11/16/2013 102.123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Transaction tran1 = new Transaction("Surn-surn 11/16/2013 102.123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        // Тест на успешное создание Транзакции
        try {
            Transaction tran1 = new Transaction("Surn 11/16/2013 102.123");
        }
        catch (IllegalArgumentException e1) {
            fail("Should create Transaction"); }

        try {
            Transaction tran1 = new Transaction("Surn-Surn 11/16/2013 102.123");
        }
        catch (IllegalArgumentException e1) {
            fail("Should create Transaction"); }

        try {
            Transaction tran1 = new Transaction("Фвра-Фдыл 11/16/2013 102.123");
        }
        catch (IllegalArgumentException e1) {
            fail("Should create Transaction"); }

        try {
            Transaction tran1 = new Transaction("Üasf-Käas 11/16/2013 102.123");
        }
        catch (IllegalArgumentException e1) {
            fail("Should create Transaction"); }
    }

    @Test
    public void whoTest() throws Exception {

        // Тест на возврат верной фамилии транзакции
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        String surn1 = tran1.who();
        assertEquals("Surname", surn1);

        Transaction tran2 = new Transaction("Sur-Name 12/10/2004 102.123");
        String surn2 = tran2.who();
        assertEquals("Sur-Name", surn2);
    }

    @Test
    public void whenTest() throws Exception {

        // Тест на возврат верной даты транзакции
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        SmartDate date1 = tran1.when();
        SmartDate date2 = new SmartDate("11/16/2013");
        assertEquals(date2, date1);

        Transaction tran2 = new Transaction("Surname 03/27/1992 102.123");
        SmartDate date3 = tran2.when();
        SmartDate date4 = new SmartDate("03/27/1992");
        assertEquals(date4, date3);
    }

    @Test
    public void amountTest() throws Exception {

        // Тест на возврат верной суммы транзакции
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        Double am1 = tran1.amount();
        Double am2 = 102.123;
        assertEquals(am2, am1);

        Transaction tran2 = new Transaction("Surname 11/16/2013 1203184.1526");
        Double am3 = tran2.amount();
        Double am4 = 1203184.1526;
        assertEquals(am4, am3);
    }

    @Test
    public void equalsTest() throws Exception {

        // Тест на равность ссылок на один и тот же объект
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        Transaction tran2 = tran1;
        boolean res1 = tran1.equals(tran2);
        assertTrue("Should be equal", res1);

        // Тест на неравность различных транзакций
        Transaction tran3 = new Transaction("Surnameaa 11/16/2013 102.123");
        Transaction tran4 = new Transaction("Surnamebb 12/16/2013 102.123");
        Transaction tran5 = new Transaction("Surnamecc 11/16/2013 902.123");
        boolean res3 = tran1.equals(tran3);
        boolean res4 = tran1.equals(tran4);
        boolean res5 = tran1.equals(tran5);
        assertFalse("Should not be equal", res3);
        assertFalse("Should not be equal", res4);
        assertFalse("Should not be equal", res5);

        // Тест на равность двух одинаковых транзакций
        Transaction tran6 = new Transaction("Surname 11/16/2013 102.123");
        boolean res6 = tran1.equals(tran6);
        assertTrue("Should be equal", res6);

        // Тест на неравность транзакции и null
        Transaction tran7 = null;
        boolean res7 = tran1.equals(tran7);
        assertFalse("Should not be equal", res7);

        // Тест на неравность объектов двух разных классов
        SmartDate date1 = new SmartDate("8/14/2012");
        boolean res8 = tran1.equals(date1);
        assertFalse("Should not be equal", res8);

        // Тест на симметричность
        boolean res9 = tran6.equals(tran1);
        assertTrue("Should be symmetric", res6 == res9);
    }

    @Test
    public void compareToTest() throws Exception {
        Transaction tran1 = new Transaction("Surn 11/16/2013 102.123");
        Transaction tran2 = new Transaction("Surnasd 11/16/2015 102.123");
        Transaction tran3 = new Transaction("Surnasdasd 11/21/2015 2.123");
        Transaction tran4 = new Transaction("Sasdasd 11/25/2003 202.123");
        int res1 = tran1.compareTo(tran2);
        int res2 = tran1.compareTo(tran3);
        int res3 = tran1.compareTo(tran4);
        assertEquals(0, res1);
        assertEquals(1, res2);
        assertEquals(-1, res3);
    }

    @Test
    public void hashCodeTest() throws Exception {
        // Тест на совпадение хеш-кодов у эквивалентных транзакций
        Transaction tran1 = new Transaction("Surn 11/16/2013 102.123");
        Transaction tran2 = new Transaction("Surn 11/16/2013 102.123");
        int h1 = tran1.hashCode();
        int h2 = tran2.hashCode();
        boolean res1 = h1 == h2;

        if ( tran1.equals(tran2) ) assertTrue(res1);
        else fail("To test hashCode must be equal");

        // Тест на несовпадение хеш-кодов у неэквивалентных транзакций
        Transaction tran3 = new Transaction("Surnasd 3/15/2011 201.321");
        int h3 = tran3.hashCode();
        boolean res2 = tran1.equals(tran3);
        if (h1 != h3) assertFalse(res2);
    }
}