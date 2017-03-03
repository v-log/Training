package Chapter_1.Ex_1_3_4;

import org.junit.Test;

import static Chapter_1.Ex_1_3_4.Parentheses.parenthesesBalanced;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vl on 15.09.16.
 */
public class ParenthesesTest {
    @Test
    public void parenthesesBalancedTest() throws Exception {
        // Тест на успешную проверку баланса скобок
        String input1 = "{([])}";
        boolean res1 = parenthesesBalanced(input1);
        assertTrue(res1);

        String input8 = "[()]{}{[()()]()}";
        boolean res8 = parenthesesBalanced(input8);
        assertTrue(res8);

        String input4 = "asd";
        boolean res4 = parenthesesBalanced(input4);
        assertTrue(res4);

        String input6 = "";
        boolean res6 = parenthesesBalanced(input6);
        assertTrue(res6);

        String input7 = "><";
        boolean res7 = parenthesesBalanced(input7);
        assertTrue(res7);

        // Тест на НЕуспешную проверку баланса скобок
        String input2 = "([)]";
        boolean res2 = parenthesesBalanced(input2);
        assertFalse(res2);

        String input3 = "asd{";
        boolean res3 = parenthesesBalanced(input3);
        assertFalse(res3);

        String input5 = "asd}";
        boolean res5 = parenthesesBalanced(input5);
        assertFalse(res5);

        String input9 = "{asd";
        boolean res9 = parenthesesBalanced(input9);
        assertFalse(res9);

        String input10 = "}{";
        boolean res10 = parenthesesBalanced(input10);
        assertFalse(res10);
    }
}