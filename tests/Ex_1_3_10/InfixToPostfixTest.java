package Ex_1_3_10;

import org.junit.Test;

import static Ex_1_3_10.InfixToPostfix.convertInfixToPostfix;
import static org.junit.Assert.assertEquals;

/**
 * Created by vl on 20.09.16.
 */
public class InfixToPostfixTest {
    @Test
    public void convertInfixToPostfixTest() throws Exception {
        String inStr1 = "A * B + C * D";
        String inStr2 = "A + B * C + D";
        String inStr3 = "A + B + C + D";
        String inStr4 = "(A + B) * (C + D)";
        String inStr5 = "(A + B + K) * (C + D)";
        String inStr6 = "(A ^ B + K) * (C + D)";
        String inStr7 = "(A + B * K) * (C + D)";
        String inStr8 = "(A * B + C) + D * E";
        String inStr9 = "(A + B * C) + D * E";
        String inStr10 = "A * B ^ C";
        String inStr11 = "(A + B * C + D) * (E + K * F * ( H + I ) )";
        String inStr12 = "A + B * C + D * (E + F ^ G * H + I * J ^ K * L * (M ^ N * (P ^ Q + R)))";

        String inStr20 = "A + B * C";
        String outStr20 = convertInfixToPostfix(inStr20);
        assertEquals("A B C * + ", outStr20);

        String inStr21 = "A * B + C";
        String outStr21 = convertInfixToPostfix(inStr21);
        assertEquals("A B * C + ", outStr21);

        String inStr22 = "A + B ^ C";
        String outStr22 = convertInfixToPostfix(inStr22);
        assertEquals("A B C ^ + ", outStr22);

        String inStr23 = "A ^ B + C";
        String outStr23 = convertInfixToPostfix(inStr23);
        assertEquals("A B ^ C + ", outStr23);

        String inStr24 = "A * B ^ C";
        String outStr24 = convertInfixToPostfix(inStr24);
        assertEquals("A B C ^ * ", outStr24);

        String inStr25 = "A ^ B * C";
        String outStr25 = convertInfixToPostfix(inStr25);
        assertEquals("A B ^ C * ", outStr25);

        String inStr26 = "(A + B + C) * D ";
        String outStr26 = convertInfixToPostfix(inStr26);
        assertEquals("A B + C + D * ", outStr26);

        String inStr27 = "(A + B * C) * D + E ";
        String outStr27 = convertInfixToPostfix(inStr27);
        assertEquals("A B C * + D * E + ", outStr27);

        String inStr28 = "(A + B * C) + D * E ";
        String outStr28 = convertInfixToPostfix(inStr28);
        assertEquals("A B C * + D E * + ", outStr28);

        String inStr29 = "(A + B + C) + D * (E + F) ";
        String outStr29 = convertInfixToPostfix(inStr29);
        assertEquals("A B + C + D E F + * + ", outStr29);

        String inStr30 = "A + B * C + D * (E * F ^ G + H + I * J ^ K * L * (M ^ N * (P ^ Q + R)))";
        String outStr30 = convertInfixToPostfix(inStr30);
        assertEquals("A B C * + D E F G ^ * H + I J K ^ * L * M N ^ P Q ^ R + * * + * + ", outStr30);


        String outStr1 = convertInfixToPostfix(inStr1);
        String outStr2 = convertInfixToPostfix(inStr2);
        String outStr3 = convertInfixToPostfix(inStr3);
        String outStr4 = convertInfixToPostfix(inStr4);
        String outStr5 = convertInfixToPostfix(inStr5);
        String outStr6 = convertInfixToPostfix(inStr6);
        String outStr7 = convertInfixToPostfix(inStr7);
        String outStr8 = convertInfixToPostfix(inStr8);
        String outStr9 = convertInfixToPostfix(inStr9);
        String outStr10 = convertInfixToPostfix(inStr10);
        String outStr11 = convertInfixToPostfix(inStr11);
        String outStr12 = convertInfixToPostfix(inStr12);

        assertEquals("A B * C D * + ", outStr1);
        assertEquals("A B C * + D + ", outStr2);
        assertEquals("A B + C + D + ", outStr3);
        assertEquals("A B + C D + * ", outStr4);
        assertEquals("A B + K + C D + * ", outStr5);
        assertEquals("A B ^ K + C D + * ", outStr6);
        assertEquals("A B K * + C D + * ", outStr7);
        assertEquals("A B * C + D E * + ", outStr8);
        assertEquals("A B C * + D E * + ", outStr9);
        assertEquals("A B C ^ * ", outStr10);
        assertEquals("A B C * + D + E K F * H I + * + * ", outStr11);
        assertEquals("A B C * + D E F G ^ H * + I J K ^ * L * M N ^ P Q ^ R + * * + * + ", outStr12);


    }

}