package Ex_1_3_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

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

    public static boolean parenthesesBalanced(String input) {
        Character[] parOpen = {'[', '(', '{'};
        Character[] parClose = {']', ')', '}'};
        char[] inputChars = input.toCharArray();
        Stack<Character> collection = new Stack<>();

        for (char ch : inputChars) {

            if (Arrays.asList(parOpen).contains(ch))
            {   collection.push(ch);   }

            if (Arrays.asList(parClose).contains(ch)) {
                if (collection.isEmpty()) return false;
                char lastPar = collection.pop();
                int lastParInd = Arrays.asList(parClose).indexOf(ch);
                int lastParOpenInd = Arrays.asList(parOpen).indexOf(lastPar);
                if (lastParInd != lastParOpenInd) return false;
            }
        }
        return collection.isEmpty();
    }
}