package Ex_1_2_6;

/**
 * Created by vl on 15.07.16.
 */

/*
1.2.6 A string str1 is a circular rotation of a string str2 if it matches when the characters
are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of
TGACGAC , and vice versa. Detecting this condition is important in the study of genomic
sequences. Write a program that checks whether two given strings str1 and str2 are circular
shifts of one another. Hint : The solution is a one-liner with indexOf() , length() , and
string concatenation.
 */

public class StrCircRotationDetect {
    public static void main(String[] args) {
        try {
            String s = "ACTGACG";
            String t = "TGACGAC";
            boolean result = strCircRotationDetect(s, t);
            System.out.println(result);
        }
        catch (StrLenNotEqualException e1) {
            System.out.println(e1.getMessage());
            System.out.println(e1.getStrings());
            System.exit(1);
        }
        // Исключение: если хотя бы одна строка null
        catch (NullPointerException e2) {
            System.out.println("Strings cannot be null");
            System.exit(1);
        }
    }

    // Проверка строк на смещение
    public static boolean strCircRotationDetect(String str1, String str2) throws StrLenNotEqualException {
        String str2Double = str2.concat(str2);
        if (str1.length() != str2.length()) {
            throw new StrLenNotEqualException("Strings must have same length", str1, str2);
        }
        else return str2Double.indexOf(str1) < str2.length()  &&  str2Double.contains(str1);
    }
}

// Исключение с уведомлением о разной длине строк
class StrLenNotEqualException extends Exception {
    private String str1;
    private String str2;
    public String getStrings () {
        return "String 1: " + str1 + "\nString 2: " + str2;
    }
    public StrLenNotEqualException (String message, String str1, String str2) {
        super(message);
        this.str1 = str1;
        this.str2 = str2;
    }
}