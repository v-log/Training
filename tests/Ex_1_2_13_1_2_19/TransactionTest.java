package Ex_1_2_13_1_2_19;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 12.09.16.
 */
public class TransactionTest {
    @Test
    public void TransactionTest() throws Exception {
        // Тест на перехват неверного количества аргументов
        try {
            Transaction tran1 = new Transaction("Surn 11/16/2013 102.123 explicitArg");
            fail("Should throw ArithmeticException");
        }
        catch (ArithmeticException e1) { }

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
    }

    @Test
    public void whoTest() throws Exception {

        // Тест на возврат верной фамилии транзакции
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        String surn1 = tran1.who();
        assertEquals("Surname", surn1);

        Transaction tran2 = new Transaction("Sur-name 12/10/2004 102.123");
        String surn2 = tran2.who();
        assertEquals("Sur-name", surn2);
    }

    @Test
    public void whenTest() throws Exception {

        // Тест на возврат верной даты транзакции
        Transaction tran1 = new Transaction("Surname 11/16/2013 102.123");
        Date date1 = tran1.when();
        Date date2 = new Date("11/16/2013");
        assertEquals(date2, date1);

        Transaction tran2 = new Transaction("Surname 03/27/1992 102.123");
        Date date3 = tran2.when();
        Date date4 = new Date("03/27/1992");
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
        Transaction tran3 = new Transaction("SurnameS 11/16/2013 102.123");
        Transaction tran4 = new Transaction("Surname 12/16/2013 102.123");
        Transaction tran5 = new Transaction("Surname 11/16/2013 902.123");
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
        Date date1 = new Date("8/14/2012");
        boolean res8 = tran1.equals(date1);
        assertFalse("Should not be equal", res8);

        // Тест на симметричность
        boolean res9 = tran6.equals(tran1);
        assertTrue("Should be symmetric", res6 == res9);
    }
}