package Ex_1_3_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by vl on 14.09.16.
 */

/*1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
and uses a stack to determine whether its parentheses are properly balanced. For
example, your program should print true for [()]{}{[()()]()} and false for [(]) .*/

public class Parentheses {
    public static void main(String[] args) {
        String input = StdIn.readString();
        System.out.println(parenthesesBalanced(input));
    }

    private static final String PAR_OPEN = "[({";
    private static final String PAR_CLOSE = "])}";

    public static boolean parenthesesBalanced(String input) {
        char[] inputChars = input.toCharArray();
        Stack<Character> collection = new Stack<>();
        for (char ch : inputChars) {

            if (PAR_OPEN.indexOf(ch) >= 0)
            {   collection.push(ch);   }

            if (PAR_CLOSE.indexOf(ch) >= 0) {
                if (collection.isEmpty()) return false;
                char lastPar = collection.pop();
                int lastParInd = PAR_CLOSE.indexOf(ch);
                int lastParOpenInd = PAR_OPEN.indexOf(lastPar);
                if (lastParInd != lastParOpenInd) return false;
            }
        }
        return collection.isEmpty();
    }
}