package Algorithms_Chapter_1.Ex_1_3_10;

import edu.princeton.cs.algs4.ResizingArrayStack;

/**
 * Created by vl on 18.09.16.
 */
/* 1.3.10 Write a filter INFIXtoPostfix that converts an arithmetic expression from
infix to postfix. */

public class INFIXtoPostfix {

    public static void main(String[] args) {
        try {
            String inStr[] = new String[13];
            inStr[0] = "A * B + C * D";
            inStr[1] = "A + B * C + D";
            inStr[2] = "A + B + C + D";
            inStr[3] = "(A + B) * (C + D)";
            inStr[4] = "(A + B + K) * (C + D)";
            inStr[5] = "(A ^ B + K) * (C + D)";
            inStr[6] = "(A + B * K) * (C + D)";
            inStr[7] = "(A * B + C) + D * E";
            inStr[8] = "(A + B * C) + D * E";
            inStr[9] = "A * B ^ C";
            inStr[10] = "(A + B * C + D) * (E + K * F * ( H + I ) )";
            inStr[11] = "A + B * C + D * (E + F ^ G * H + I * J ^ K * L * (M ^ N * (P ^ Q + R)))";
            inStr[12] = "(A)";

            for (int i = 0; i < inStr.length; i++) {
                System.out.println(inStr[i] + "  =  " + convINFIXtoPostfix(inStr[i]));
            }

            convINFIXtoPostfix("   ");
        }
        catch (IllegalArgumentException e1) {
            System.out.println(e1.getMessage());
            System.exit(1);
        }
        catch (NullPointerException e2) {
            System.out.println(e2.getMessage());
            System.exit(1);
        }
    }



    static class Operator {
        private final char opSymb;
        private final int prec;
        private static final String opsStr = "()+-*/^";

        Operator(Character opSymbol) throws IllegalArgumentException {

            // Проверка допустимости введенного оператора
            if (opsStr.indexOf(opSymbol) < 0) {
                throw new IllegalArgumentException("Illegal operation \"" + opSymbol + "\"");
            }

            // Назначение приоритета оператору из введенной строки:
            // () = 0;  +- = 1;  */ = 2;  ^ = 3;
            int precedence = -1;
            if ( opSymbol == '(' || opSymbol == ')' ) precedence = 0;
            if ( opSymbol == '+' || opSymbol == '-' ) precedence = 1;
            if ( opSymbol == '*' || opSymbol == '/' ) precedence = 2;
            if ( opSymbol == '^' ) precedence = 3;

            opSymb = opSymbol;
            prec = precedence;
        }

        char opSymb()
        { return this.opSymb; }

        int prec()
        { return this.prec; }
    }

    static String convINFIXtoPostfix(String infix) throws IllegalArgumentException,
            NullPointerException {

        // Результирующая строка для конвертера
        StringBuilder output = new StringBuilder();

        if(infix == null) throw new NullPointerException("Expression can't be null");

        if(infix.isEmpty()) throw new IllegalArgumentException("No expression to process");

        // Очищаем рез.строку
        output.setLength(0);

        // Стэк для операторов
        ResizingArrayStack<Operator> ops = new ResizingArrayStack<>();

        char[] infixChars = infix.toCharArray();

        // Флаг наличия операнда перед оператором
        boolean operandIsLast = false;
        // Флаг наличия оператора перед операндом
        boolean operatorIsLast = false;
        // Счетчик открытых скобок
        int parOpenCount = 0;
        // Флаг наличия операнда после открытой скобки
        boolean operandAfterPar = false;


        for (char chArg : infixChars) {

            // Если символ из введенной строки - операнд, добавляем его к выходной строке
            if (Character.toString(chArg).matches("\\p{L}")) {

                // Проверка на наличие оператора перед текущим операндом
                if ( !operatorIsLast && output.length() != 0 ) {
                    throw new IllegalArgumentException("Missing operation before \"" + chArg + "\"");
                }
                // Фиксирование наличия операнда после открытой скобки необходимо для проверки
                // правильности введенного выражения
                if ( !ops.isEmpty() && ops.peek().opSymb() == '(' ) operandAfterPar = true;

                opOut(output, chArg);
                operandIsLast = true;
                operatorIsLast = false;
                continue;
            }

            // Если символ - пробел, идем дальше по строке
            if (chArg == ' ') continue;

            // Создаем объект с оператором из введенной строки с соответствующим приоритетом
            Operator ch = new Operator(chArg);

            // Если символ - левая скобка, добавляем его в стек и идем дальше по строке
            if ( ch.opSymb() == '(' ) {
                ops.push(ch);
                parOpenCount++;
                continue;
            }

            // Если символ - правая скобка, выводим из стэка в рез.строку операторы,
            // пока не дойдем до первой левой скобки
            if ( ch.opSymb() == ')' ) {

                // Проверка на наличие открывающей скобки
                if (parOpenCount < 1) throw new IllegalArgumentException("Missing opening parenthesis");

                while (!ops.isEmpty() && ops.peek().opSymb() != '(') {
                    opOut(output, ops.pop().opSymb());
                }
                ops.pop();
                parOpenCount--;
                continue;
            }

            // Если символ - оператор:
            if ( ch.opSymb() != '(' && ch.opSymb() != ')' ) {

                // Проверка на наличие операнда перед текущим оператором
                if (!operandIsLast) {
                    throw new IllegalArgumentException("Missing operand before \"" + ch.opSymb() + "\"");
                }
                // Проверка на наличие после открытой скобки операнда перед оператором
                if (parOpenCount > 0 && !ops.isEmpty() && ch.opSymb() == '(' && !operandAfterPar) {
                    throw new IllegalArgumentException("Missing operand before \"" + ch.opSymb() + "\"");
                }

                operandAfterPar = false;
                operatorIsLast = true;
                operandIsLast = false;

                // Выводим из стэка в рез.строку операторы, пока оператор из введенной строки
                // не будет иметь больший приоритет, чем оператор в стэке;
                while (!ops.isEmpty() && ops.peek().prec() >= ch.prec()) {
                    opOut(output, ops.pop().opSymb());
                }
                ops.push(ch);
            }
        }

        // Проверка на то, что последним в выражении, не считая скобок, был операнд, а не оператор
        if (!operandIsLast) throw new IllegalArgumentException("Missing last operand");
        // Проверка баланса скобок
        if (parOpenCount != 0) throw new IllegalArgumentException("Missing closing parentheses");

        // Выводим в рез.строку оставшиеся в стэке операторы
        while (!ops.isEmpty()) {
            opOut(output, ops.pop().opSymb());
        }

        return output.toString();
    }

    // Добавляет символ symb и пробел к выходной строке outp
    static void opOut(StringBuilder outp, char symb) {
        outp.append(symb);
        outp.append(" ");
    }
}