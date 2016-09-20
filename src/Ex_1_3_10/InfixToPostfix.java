package Ex_1_3_10;

import edu.princeton.cs.algs4.ResizingArrayStack;

/**
 * Created by vl on 18.09.16.
 */
/* 1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from
infix to postfix. */

public class InfixToPostfix {

    public static void main(String[] args) {
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
        System.out.println(inStr1 + "  =  " + outStr1);
        System.out.println(inStr2 + "  =  " + outStr2);
        System.out.println(inStr3 + "  =  " + outStr3);
        System.out.println(inStr4 + "  =  " + outStr4);
        System.out.println(inStr5 + "  =  " + outStr5);
        System.out.println(inStr6 + "  =  " + outStr6);
        System.out.println(inStr7 + "  =  " + outStr7);
        System.out.println(inStr8 + "  =  " + outStr8);
        System.out.println(inStr9 + "  =  " + outStr9);
        System.out.println(inStr10 + "  =  " + outStr10);
        System.out.println(inStr11 + "  =  " + outStr11);
        System.out.println(inStr12 + "  =  " + outStr12);
    }

    private static final String opsPlus = "+-";
    private static final String opsMultip = "*/";
    private static final char OP_POW = '^';
    private static final char L_PAR = '(';
    private static final char R_PAR = ')';
    private static String output;

    public static String convertInfixToPostfix(String infix) {

        ResizingArrayStack<Character> ops = new ResizingArrayStack<>();
        char[] infixChars = infix.toCharArray();
        output = "";
        int hiIsOnStack = 0;
        int parFlag = 0;
        int powFlag = 0;
        for (char ch: infixChars) {

            if (Character.toString(ch).matches("\\p{L}")) {
                output = output + ch + " ";
                continue;
            }

            if(ch == L_PAR) {
                hiIsOnStack = 0;
                ops.push(ch);
                parFlag = 1;
                continue;
            }

            if(ch == R_PAR) {
                while(!ops.isEmpty()) {

                    char opOut = ops.pop();
                    if (opOut == L_PAR) break;
                    output = output + opOut + " ";

                }

                hiIsOnStack = 0;
                parFlag = 0;
                continue;
            }

            if (opsMultip.indexOf(ch) >= 0) {

//                if (ops.isEmpty()) hiIsOnStack = 1;
//                ops.push(ch);
                if (powFlag == 1) {
//                    while(!ops.isEmpty()) {
//                        if (ops.peek() == L_PAR) break;
//                        char opOut = ops.pop();
//                        output = output + opOut + " ";
//                    }
                    char opOut = ops.pop();
                    output = output + opOut + " ";
                    powFlag = 0;
//                    ops.push(ch);
//                    continue;
                }
//                if(parFlag == 1) ops.push(ch);
                if (hiIsOnStack == 1) {
                    output = output + ch + " ";
                    hiIsOnStack = 1;
                    parFlag = 0;
                    continue;
                    /*while(!ops.isEmpty()) {
                    if (ops.peek() == L_PAR) break;
                    char opOut = ops.pop();
                    output = output + opOut + " ";*/
                }

                ops.push(ch);
                hiIsOnStack = 1;
                parFlag = 0;
                powFlag = 0;
                continue;
            }

            if (opsPlus.indexOf(ch) >= 0) {
//                if ( (parFlag !=1 && hiIsOnStack != 1 || !ops.isEmpty()))
//                    while(!ops.isEmpty()) {
//                        char opOut = ops.pop();
//                        output = output + opOut + " ";
//                    }
//                ops.push(ch);
//                hiIsOnStack = 0;
//                if (powFlag == 1) {
//                    while (!ops.isEmpty()) {
//                        if (ops.peek() == L_PAR) break;
//                        char opOut = ops.pop();
//                        output = output + opOut + " ";
//                    }
//                    powFlag = 0;
////                    ops.push(ch);
////                    continue;
//                }

                if (parFlag == 1) {
                    ops.push(ch);
                    parFlag = 0;
                    continue;
                }

                if (  !ops.isEmpty() ) while(!ops.isEmpty()) {
                    if (ops.peek() == L_PAR) break;
                    char opOut = ops.pop();
                    output = output + opOut + " ";
                }
                ops.push(ch);

//                parFlag = 0;

                hiIsOnStack = 0;
                parFlag = 0;
                powFlag = 0;
                continue;
            }

            if (ch == OP_POW) {
                ops.push(ch);
                parFlag = 0;
                powFlag = 1;
                continue;
            }

            if (ch == ' ') continue;

            throw new IllegalArgumentException("Illegal argument: " + ch);
        }
        while(!ops.isEmpty()) {
            char opOut = ops.pop();
            output = output + opOut + " ";
        }
        return output;
    }
}