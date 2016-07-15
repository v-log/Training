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
        for (int i = 0; i < s1.length() - 1; i++) {
//            Вывод индекса, с которого строки совпадают:
//            System.out.println(s2.indexOf(s1));
            if (s2.indexOf(s1) == 0) {
                return true;
            }
            else {
                String sTemp = s1.substring(s1.length() - 1, s1.length()).concat(s1.substring(0, s1.length() - 1));
//                Вывод деталей операции:
//                System.out.print(" step " + (i + 1) + ": " + s1.substring(s1.length() - 1, s1.length()));
//                System.out.print(" + " + s1.substring(0, s1.length() - 1));
//                System.out.println(" = " + s2 + ";  length = " + s2.length());
                s1 = sTemp;
            }
        }
        return false;
    }

}
