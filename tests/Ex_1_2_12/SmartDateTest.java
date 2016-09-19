package Ex_1_2_12;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vl on 04.09.16.
 */
public class SmartDateTest {
    @Test
    public void monthTest() throws Exception {
        SmartDate date1 = new SmartDate(9, 4, 2016);
        SmartDate date2 = new SmartDate(12, 25, 2004);
        SmartDate date3 = new SmartDate(4, 17, 2009);
        int month1 = date1.month();
        int month2 = date2.month();
        int month3 = date3.month();
        assertEquals(9, month1);
        assertEquals(12, month2);
        assertEquals(4, month3);
    }

    @Test
    public void dayTest() throws Exception {
        SmartDate date1 = new SmartDate(9, 4, 2016);
        SmartDate date2 = new SmartDate(12, 25, 2004);
        SmartDate date3 = new SmartDate(4, 17, 2009);
        int day1 = date1.day();
        int day2 = date2.day();
        int day3 = date3.day();
        assertEquals(4, day1);
        assertEquals(25, day2);
        assertEquals(17, day3);
    }

    @Test
    public void yearTest() throws Exception {
        SmartDate date1 = new SmartDate(9, 4, 2016);
        SmartDate date2 = new SmartDate(12, 25, 2004);
        SmartDate date3 = new SmartDate(4, 17, 2009);
        int year1 = date1.year();
        int year2 = date2.year();
        int year3 = date3.year();
        assertEquals(2016, year1);
        assertEquals(2004, year2);
        assertEquals(2009, year3);
    }

    @Test
    public void dayOfTheWeekTest() throws Exception {
        SmartDate date1 = new SmartDate(1, 1, 2000);
        SmartDate date11 = new SmartDate(1, 1, 2016);
        SmartDate date2 = new SmartDate(9, 4, 2016);
        SmartDate date21 = new SmartDate(7, 16, 2069);
        SmartDate date3 = new SmartDate(12, 31, 2099);
        String weekDay1 = date1.dayOfTheWeek();
        String weekDay11 = date11.dayOfTheWeek();
        String weekDay2 = date2.dayOfTheWeek();
        String weekDay21 = date21.dayOfTheWeek();
        String weekDay3 = date3.dayOfTheWeek();
        assertEquals("Saturday", weekDay1);
        assertEquals("Friday", weekDay11);
        assertEquals("Sunday", weekDay2);
        assertEquals("Tuesday", weekDay21);
        assertEquals("Thursday", weekDay3);
    }

    @Test
    public void SmartDateTest1() throws Exception {
        try{
            SmartDate date1 = new SmartDate(2, 30, 2000);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date2 = new SmartDate(2, 29, 2001);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date3 = new SmartDate(2, 30, 2004);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date4 = new SmartDate(2, 29, 2100);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date4 = new SmartDate(13, 22, 2003);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date4 = new SmartDate(-1, 22, 2003);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        try{
            SmartDate date4 = new SmartDate(4, 35, 2003);
            fail("Should throw DateNotLegalException");
        }
        catch (DateNotLegalException e1) { }

        // Тест на успешное создание объекта
        try{
            SmartDate date4 = new SmartDate("4/23/2003");
        }
        catch (DateNotLegalException e1)
        { fail("Should create SmartDate"); }

        // Тест на исключение при создании даты с избыточными аргументами
        try{
            SmartDate date4 = new SmartDate("4/23/2003/123");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
    }

    @Test
    public void hashCodeTest() throws Exception {
        // Тест на совпадение хеш-кодов у эквивалентных дат
        SmartDate date1 = new SmartDate(4, 23, 2003);
        SmartDate date2 = new SmartDate("4/23/2003");
        int h1 = date1.hashCode();
        int h2 = date2.hashCode();
        boolean res1 = h1 == h2;

        if ( date1.equals(date2) ) assertTrue(res1);
        else fail("To test hashCode must be equal");

        // Тест на несовпадение хеш-кодов у неэквивалентных дат
        SmartDate date3 = new SmartDate("6/15/2014");
        int h3 = date3.hashCode();
        boolean res2 = date1.equals(date3);
        if (h1 != h3) assertFalse(res2);
        else fail("To test hashCode must not be equal");
    }
}