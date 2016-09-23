package Ex_1_3_10;

import org.junit.Test;

import static Ex_1_3_10.InfixToPostfix.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by vl on 20.09.16.
 */
public class InfixToPostfixTest {
    @Test
    public void convertINFIXToPostfixTest() throws Exception {

    // Тест на успешную конвертацию
        assertEquals("A B C * + ", convINFIXtoPostfix("A + B * C"));
        assertEquals("A B * C - ", convINFIXtoPostfix("A * B - C"));

        assertEquals("A B C ^ + ", convINFIXtoPostfix("A + B ^ C"));
        assertEquals("A B ^ C - ", convINFIXtoPostfix("A ^ B - C"));

        assertEquals("A B C ^ * ", convINFIXtoPostfix("A * B ^ C"));
        assertEquals("A B ^ C / ", convINFIXtoPostfix("A ^ B / C"));

        assertEquals("A B + C + D * ", convINFIXtoPostfix("(A + B + C) * D "));
        assertEquals("A B C * + D * E - ", convINFIXtoPostfix("(A + B * C) * D - E "));
        assertEquals("A B C * + D E / + ", convINFIXtoPostfix("(A + B * C) + D / E "));

        assertEquals("A B * C D * + ", convINFIXtoPostfix("A * B + C * D"));
        assertEquals("A B C / - D + ", convINFIXtoPostfix("A - B / C + D"));

        assertEquals("A B + C + D + ", convINFIXtoPostfix("A + B + C + D"));
        assertEquals("A B + C D + * ", convINFIXtoPostfix("(A + B) * (C + D)"));

        assertEquals("A B + K + C D + * ", convINFIXtoPostfix("(A + B + K) * (C + D)"));
        assertEquals("A B ^ K + C D + * ", convINFIXtoPostfix("(A ^ B + K) * (C + D)"));
        assertEquals("A B K * - C D + / ", convINFIXtoPostfix("(A - B * K) / (C + D)"));
        assertEquals("A B * C + D E * + ", convINFIXtoPostfix("(A * B + C) + D * E"));
        assertEquals("A B C * + D E * + ", convINFIXtoPostfix("(A + B * C) + D * E"));

        String outStr29 = convINFIXtoPostfix("(A + B + C) + D * (E + F) ");
        assertEquals("A B + C + D E F + * + ", outStr29);

        String inStr11 = "(A + B * C - D) * (E + K * F / ( H + I ) )";
        String outStr11 = convINFIXtoPostfix(inStr11);
        assertEquals("A B C * + D - E K F * H I + / + * ", outStr11);

        String inStr12 = "A + B * C + D * (E + F ^ G * H + I * J ^ K * L * (M ^ N * (P ^ Q + R)))";
        String outStr12 = convINFIXtoPostfix(inStr12);
        assertEquals("A B C * + D E F G ^ H * + I J K ^ * L * M N ^ P Q ^ R + * * + * + ", outStr12);

        String inStr30 = "A + B / C + D / (E * F ^ G + H - I * J ^ K * L * (M ^ N * (P ^ Q + R)))";
        String outStr30 = convINFIXtoPostfix(inStr30);
        assertEquals("A B C / + D E F G ^ * H + I J K ^ * L * M N ^ P Q ^ R + * * - / + ", outStr30);


    //Тест на перехват исключения при вводе недопустимой операции
        try {
            convINFIXtoPostfix("A < B");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A . B");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A + B . C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A * (B + C . D)");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    //Тест на перехват исключения при пропуске операнда
        try {
            convINFIXtoPostfix("A * - B");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A * B + / C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A * B + ");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix(" * B + C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("  ");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват исключения при пропуске оператора
        try {
            convINFIXtoPostfix("A  B");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
        try {
            convINFIXtoPostfix("A * B  C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват исключения при пропуске оператора после открывающей скобки
        try {
            convINFIXtoPostfix("A * B ( C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват исключения при пропуске операнда после открытия скобки
        try {
            convINFIXtoPostfix("A * B ( + C");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            convINFIXtoPostfix("A * B (B + ( * C)");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            convINFIXtoPostfix("()");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват исключения при незакрытой скобке
        try {
            convINFIXtoPostfix("(");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            convINFIXtoPostfix(")");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            convINFIXtoPostfix("A * B * (C + D");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            convINFIXtoPostfix("A * B * (C + D * (E + F)");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват исключения при вводе пустой строки
        try {
            convINFIXtoPostfix("");
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }


    // Тест на перехват NullPointerException
        try{
            convINFIXtoPostfix(null);
            fail("Should throw NullPointerException");
        }
        catch (NullPointerException e1) { }
    }

    @Test
    public void opOutTest() throws Exception {
        StringBuilder output = new StringBuilder();

        output.setLength(0);
        opOut(output, 'A');
        assertEquals( "A ", output.toString() );

        opOut(output, 'B');
        assertEquals( "A B ", output.toString() );

        opOut(output, ' ');
        assertEquals( "A B   ", output.toString() );

        opOut(output, '*');
        assertEquals( "A B   * ", output.toString() );
    }

    @Test
    public void OperatorTest() throws Exception {
    // Тест на успешное создание оператора
        try { Operator oper1 = new Operator('+'); }
        catch (IllegalArgumentException e1) { fail("Should create Operator"); }

        try { Operator oper1 = new Operator('('); }
        catch (IllegalArgumentException e1) { fail("Should create Operator"); }

        try { Operator oper1 = new Operator('^'); }
        catch (IllegalArgumentException e1) { fail("Should create Operator"); }

    // Тест на перехват исключения при вводе недопустимого оператора
        try {
            Operator oper1 = new Operator(';');
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Operator oper1 = new Operator('&');
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Operator oper1 = new Operator('>');
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }

        try {
            Operator oper1 = new Operator(' ');
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e1) { }
    }

    @Test
    public void opSymbTest() throws Exception {
        Operator oper1 = new Operator('+');
        assertEquals('+', oper1.opSymb());

        Operator oper2 = new Operator('^');
        assertEquals('^', oper2.opSymb());
    }

    @Test
    public void precTest() throws Exception {
        Operator oper1 = new Operator('+');
        assertEquals(1, oper1.prec());

        Operator oper2 = new Operator('^');
        assertEquals(3, oper2.prec());
    }
}