package Ex_1_3_4;

import org.junit.Test;

import static Ex_1_3_4.Parentheses.parenthesesBalanced;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by vl on 15.09.16.
 */
public class ParenthesesTest {
    @Test
    public void parenthesesBalancedTest() throws Exception {
        String input1 = "{([])}";
        boolean res1 = parenthesesBalanced(input1);
        assertTrue(res1);

        String input2 = "([)]";
        boolean res2 = parenthesesBalanced(input2);
        assertFalse(res2);

        String input3 = "asd{";
        boolean res3 = parenthesesBalanced(input3);
        assertFalse(res3);

        String input4 = "asd";
        boolean res4 = parenthesesBalanced(input4);
        assertTrue(res4);

        String input5 = "asd}";
        boolean res5 = parenthesesBalanced(input5);
        assertFalse(res5);
    }

}