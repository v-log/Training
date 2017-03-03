package Chapter_1.Ex_1_2_12;

/**
 * Created by vl on 18.09.16.
 */

// Исключение с уведомлением о вводе недопустимой даты

public class DateNotLegalException extends Exception {

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