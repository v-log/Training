package Ex_1_2_6;

/**
 * Created by vl on 15.07.16.
 */

/*
1.2.6 A string s is a circular rotation of a string t if it matches when the characters
are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of
TGACGAC , and vice versa. Detecting this condition is important in the study of genomic
sequences. Write a program that checks whether two given strings s and t are circular
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

    public static boolean strCircRotationDetect(String s1, String s2) {

        return (s2.concat(s2).contains(s1));
    }
}
