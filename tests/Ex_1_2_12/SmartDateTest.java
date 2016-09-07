package Ex_1_2_12;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

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
    }
}