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

    private final int month;
    private final int day;
    private final int year;

    // Проверка правильности (допустимости) введенной даты
    public SmartDate(int m, int d, int y) throws DateNotLegalException {

        final int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (m < 1 || m > 12) throw new DateNotLegalException("Date is not legal: month must be 1 to 12. ", m, d, y);
        else if ( m != 2 && d < 1 || d > monthDays[m - 1] ) {
            throw new DateNotLegalException("Date is not legal: month " + m + " has " + monthDays[m-1] + " days in it. ", m, d, y);
        }
        else if ( m == 2 && y%400 == 0 && ( d < 1 || d > monthDays[m - 1] + 1 ) ) {
            throw new DateNotLegalException("Date is not legal: month " + m + " has " + monthDays[m - 1] + 1 +
                    " days every 400 years. ", m, d, y);
        }
        else if ( m == 2 && y%400 !=0 && y%100 == 0 && ( d < 1 || d > monthDays[m - 1] ) ) {
            throw new DateNotLegalException("Date is not legal: month " + m + " has " + monthDays[m - 1] +
                    " days every 100 years, except for every 400 years. ", m, d, y);
        }
        else if ( m == 2 && y%4 == 0 && ( d < 1 || d > monthDays[m - 1] + 1 ) ) {
            throw new DateNotLegalException("Date is not legal: month " + m + " has " + monthDays[m - 1] + 1 +
                    " days every 4 years, except for every 100 years. ", m, d, y);
        }
        else if ( m == 2 && y%400 != 0 && y%100 != 0 && y%4 != 0 && ( d < 1 || d > monthDays[m - 1] ) ) {
            throw new DateNotLegalException("Date is not legal: month" + m + " has " + monthDays[m - 1] +
                    " days in it, except for every 400 or 4 years. ", m, d, y);
        }
        else {month = m; day = d; year = y;}
    }

    public int month()
    { return month; }

    public int day()
    { return day; }

    public int year()
    { return year; }

    public String toString()
    { return month() + "/" + day() + "/" + year(); }

    // Вывод дня недели для введенной даты (условие - дата должна быть в 21-м веке)
    public String dayOfTheWeek () throws DateNotLegalException {
        if (this.year >= 2000 && this.year <2100) {
            String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

            int currYearIn21Cent = this.year - 2000;

            int monthDaysCount = 0;
            for (int i = 0; i < this.month - 1; i++) {
                monthDaysCount += monthDays[i];
            }

            return daysOfTheWeek[(currYearIn21Cent * 365 + currYearIn21Cent / 4 + monthDaysCount + this.day + 5) % 7];
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