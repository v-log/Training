package Ex_1_2_13_1_2_19;

/**
 * Created by vl on 07.09.16.
 */

/*
1.2.13 Using our implementation of Date as a model (page 91), develop an
implementation of Transaction.

Transaction(String who, Date when, double amount)
Transaction(String transaction)     create a transaction (parse constructor)
String who()                    customer name
Date when()                     date
double amount()                 amount
String toString()               string representation
boolean equals(Object that)     is this the same transaction as that ?
int compareTo(Transaction that)     compare this transaction to that
int hashCode()                  hash code

1.2.19 Parsing. Develop the parse constructors for your Date and Transaction
implementations of Exercise 1.2.13 that take a single String argument to specify the
initialization values, using the formats given in the table below.

Partial solution:

public Date(String date)
{
    String[] fields = date.split("/");
    month = Integer.parseInt(fields[0]);
    day = Integer.parseInt(fields[1]);
    year = Integer.parseInt(fields[2]);
}

type                   format                          example

Date          integers separated by slashes           5/22/1939

Transaction   customer, date, and amount,       Turing 5/22/1939 11.99
              separated by whitespace

*/

public class Transaction {
    public static void main(String[] args) {
        try {
            if (args.length == 6) {
                Transaction a = new Transaction(args[0] + " " + args[1] + " " + args[2]);
                Transaction b = new Transaction(args[3] + " " + args[4] + " " + args[5]);
                System.out.println(a.who());
                System.out.println(a.when().toString());
                System.out.println(a.amount());
                System.out.println(a.equals(b));
            }
        }
        catch (ArithmeticException e1) {
            System.out.println(e1.getMessage());
            System.exit(1);
        }
        catch (NumberFormatException e2) {
            System.out.println("Введите информацию для двух транзакций: " +
                    "через пробелы Фамилию, Дату, Сумму");
            System.exit(1);
        }
        catch (IllegalArgumentException e3) {
            System.out.println(e3.getMessage());
            System.exit(1);
        }
    }

    private final String customer;
    private final Date date;
    private final double amount;

    public Transaction(String transaction) throws IllegalArgumentException,
            ArithmeticException
    {
        String[] fields = transaction.split(" ");
        if (fields.length == 3) {
            String customerTemp = fields[0];
            if (!customerTemp.matches("[A-Z]([a-z])+") &&
                    !customerTemp.matches("[A-Z]([a-z\\-])+[A-Z][a-z]+")) {
                throw new IllegalArgumentException("Фамилия должна начинаться с" +
                        "прописной буквы и содержать только буквы");
            }
            else customer = fields[0];

            date = new Date(fields[1]);

            double amountTemp = Double.parseDouble(fields[2]);
            if (amountTemp <= 0) throw new ArithmeticException("Сумма должна быть положительна");
            else amount = amountTemp;
        }
        else throw new ArithmeticException("Введите три аргумента через пробел:" +
                " Фамилия Дата Сумма");
    }

    public String toString()
    { return who() + " " + when() + " " + amount(); }

    public String who()
    { return this.customer; }

    public Date when()
    { return this.date; }

    public double amount()
    { return this.amount; }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;

        Transaction that = (Transaction) x;

        if (!this.who().equals(that.who())) return false;
        if (!this.when().equals(that.when())) return false;
        if (this.amount() != that.amount()) return false;
        return true;
    }
}