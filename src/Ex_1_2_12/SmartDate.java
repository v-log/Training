package Ex_1_2_12;

/**
 * Created by vl on 29.07.16.
 */

/*
1.2.11 Develop an implementation SmartDate of our Date API that raises an exception
if the date is not legal.

1.2.12 Add a method dayOfTheWeek() to SmartDate that returns a String value
Monday , Tuesday , Wednesday , Thursday , Friday , Saturday , or Sunday , giving the
appropriate day of the week for the date. You may assume that the date is in the 21st
century.
*/

public class SmartDate {

    public static void main(String[] args) {
        try {
            int m = Integer.parseInt(args[0]);
            int d = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            SmartDate smartDate = new SmartDate(m, d, y);
            System.out.println(smartDate.toString());
            System.out.println(smartDate.dayOfTheWeek());
        }
        catch (DateNotLegalException e1) {
            System.out.print(e1.getMessage());
            System.out.println(e1.getDate());
            System.exit(1);
        }
    }

    private int month;
    private int day;
    private int year;
    private int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public SmartDate(int m, int d, int y) throws DateNotLegalException {
        if (isDateLegal(m, d, y)) {
            month = m; day = d; year = y;
        }
        else throw new DateNotLegalException("Date is not legal. ", m, d, y);
    }

    public int month()
    { return month; }

    public int day()
    { return day; }

    public int year()
    { return year; }

    public String toString()
    { return month() + "/" + day() + "/" + year(); }

    // Проверка правильности (допустимости) введенной даты
    public boolean isDateLegal (int m, int d, int y) {
        int daysInMo;
        // Проверка по месяцу сперва, чтобы избежать IndexOutOfBoundsException
        if ( m >= 1 && m <= 12 ) daysInMo = monthDays[m-1];
        else return false;

        if (m == 2) {
            if ( y%400 == 0 || ( y%100 != 0 && y%4 == 0 ) ) daysInMo++;
        }
        return d >= 1 && d <= daysInMo;
    }

    // Вывод дня недели для введенной даты (условие - дата должна быть в 21-м веке)
    public String dayOfTheWeek () throws DateNotLegalException {
        if (year >= 2000 && year <2100) {

            int currYearIn21Cent = year - 2000;
            int monthDaysCount = 0;

            int yearDays = currYearIn21Cent * 365;
            int additionalYearDays = currYearIn21Cent / 4;
            if (currYearIn21Cent%4 != 0) additionalYearDays++;

            for (int i = 0; i < month - 1; i++) {
                int daysInMo = monthDays[i];
                if ( i == 1 && ( year%400 == 0 || ( year%100 != 0 && year%4 == 0 ) ) ) daysInMo++;
                monthDaysCount += daysInMo;
            }
            // для отображения деталей процесса
//            System.out.println((yearDays + additionalYearDays + monthDaysCount + this.day + 4));
//            System.out.println(yearDays + " " + additionalYearDays + " " + monthDaysCount + " " + this.day);
//            System.out.println((yearDays + additionalYearDays + monthDaysCount + this.day + 4) % 7);
            return daysOfTheWeek[ ((yearDays + additionalYearDays + monthDaysCount + this.day + 4) % 7) ];
        }
        else throw new DateNotLegalException("To get the weekday date must be in 21st century. ", this.month, this.day, this.year);
    }
}

// Исключение с уведомлением о вводе недопустимой даты
class DateNotLegalException extends Exception {

    private int month;
    private int day;
    private int year;
    public String getDate() {
        return month + "/" + day + "/" + year;
    }
    public DateNotLegalException (String message, int m, int d, int y) {
        super(message);
        month = m;
        day = d;
        year = y;
    }
}