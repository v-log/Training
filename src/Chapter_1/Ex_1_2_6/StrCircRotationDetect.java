package Chapter_1.Ex_1_2_6;

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
        String s = "ACTGACG";
        String t = "TGACGAC";
        boolean result = strCircRotationDetect(s, t);
        System.out.println(result);
    }

    // Проверка строк на смещение
    public static boolean strCircRotationDetect(String str1, String str2) {
        String str2Double = str2.concat(str2);
        return str1.length() == str2.length()  &&
                str2Double.indexOf(str1) < str2.length()  &&  str2Double.contains(str1);
    }
}