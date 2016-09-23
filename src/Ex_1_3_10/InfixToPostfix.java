package Ex_1_3_10;

import edu.princeton.cs.algs4.ResizingArrayStack;

/**
 * Created by vl on 18.09.16.
 */
/* 1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from
infix to postfix. */

public class InfixToPostfix {

    public static void main(String[] args) {
        try {
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
            String inStr13 = "(A)";

            System.out.println(inStr1 + "  =  " + convINFIXtoPostfix(inStr1));
            System.out.println(inStr2 + "  =  " + convINFIXtoPostfix(inStr2));
            System.out.println(inStr3 + "  =  " + convINFIXtoPostfix(inStr3));
            System.out.println(inStr4 + "  =  " + convINFIXtoPostfix(inStr4));
            System.out.println(inStr5 + "  =  " + convINFIXtoPostfix(inStr5));
            System.out.println(inStr6 + "  =  " + convINFIXtoPostfix(inStr6));
            System.out.println(inStr7 + "  =  " + convINFIXtoPostfix(inStr7));
            System.out.println(inStr8 + "  =  " + convINFIXtoPostfix(inStr8));
            System.out.println(inStr9 + "  =  " + convINFIXtoPostfix(inStr9));
            System.out.println(inStr10 + "  =  " + convINFIXtoPostfix(inStr10));
            System.out.println(inStr11 + "  =  " + convINFIXtoPostfix(inStr11));
            System.out.println(inStr12 + "  =  " + convINFIXtoPostfix(inStr12));
            System.out.println(inStr13 + "  =  " + convINFIXtoPostfix(inStr13));

            convINFIXtoPostfix("   ");
        }
        catch (IllegalArgumentException e1) {
            System.out.println(e1.getMessage());
        }
    }

    private static final String opsStr = "()+-*/^";

    static class Operator {
        private final char opSymb;
        private final int prec;

        Operator(Character opSymbol) throws IllegalArgumentException {

        // Проверка допустимости введенного оператора
            if (opsStr.indexOf(opSymbol) < 0) {
                throw new IllegalArgumentException("Illegal operation \"" + opSymbol + "\"");
            }

        // Назначение приоритета оператору из введенной строки:
        // () = 0;  +- = 1;  */ = 2;  ^ = 3;
            int precedence = opsStr.indexOf(opSymbol) / 2;
            opSymb = opSymbol;
            prec = precedence;
        }

        char opSymb()
        { return this.opSymb; }

        int prec()
        { return this.prec; }
    }

// Результирующая строка для конвертера
    private static StringBuilder output = new StringBuilder();


    static String convINFIXtoPostfix(String infix) throws IllegalArgumentException,
            NullPointerException {

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
                if ( !ops.isEmpty() && opsStr.indexOf(ops.peek().opSymb()) == 0 ) operandAfterPar = true;

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
            if ( opsStr.indexOf(ch.opSymb()) == 0) {
                ops.push(ch);
                parOpenCount++;
                continue;
            }


        // Если символ - правая скобка, выводим из стэка в рез.строку операторы,
        // пока не дойдем до первой левой скобки
            if ( opsStr.indexOf(ch.opSymb()) == 1 ) {

            // Проверка на наличие открывающей скобки
                if (parOpenCount < 1) throw new IllegalArgumentException("Missing opening parenthesis");

                while (!ops.isEmpty() && opsStr.indexOf(ops.peek().opSymb()) != 0) {
                    opOut(output, ops.pop().opSymb());
                }
                ops.pop();
                parOpenCount--;
                continue;
            }


        // Если символ - оператор:
            if ( opsStr.indexOf(ch.opSymb()) > 1 ) {

            // Проверка на наличие операнда перед текущим оператором
                if (!operandIsLast) {
                    throw new IllegalArgumentException("Missing operand before \"" + chArg + "\"");
                }
            // Проверка на наличие после открытой скобки операнда перед оператором
                if (parOpenCount > 0 && !ops.isEmpty() && opsStr.indexOf(ops.peek().opSymb()) == 0
                        && !operandAfterPar) {
                    throw new IllegalArgumentException("Missing operand before \"" + chArg + "\"");
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

    static void opOut(StringBuilder outp, char oper) {
        outp.append(oper);
        outp.append(" ");
    }
}