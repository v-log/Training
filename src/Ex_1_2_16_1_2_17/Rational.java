package Ex_1_2_16_1_2_17;

/**
 * Created by vl on 05.09.16.
 */

/*1.2.16 Rational numbers. Implement an immutable data type Rational for rational
numbers that supports addition, subtraction, multiplication, and division.
public class Rational

Rational(int numerator. int denominator)
Rational plus(Rational b) sum of this number and b
Rational minus(Rational b) difference of this number and b
Rational times(Rational b) product of this number and b
Rational divides(Rational b) quotient of this number and b
boolean equals(Rational that) is this number equal to that ?
String toString() string representation

You do not have to worry about testing for overflow (see Exercise 1.2.17), but use as
instance variables two long values that represent the numerator and denominator to
limit the possibility of overflow. Use Euclid’s algorithm (see page 4) to ensure that the
numerator and denominator never have any common factors. Include a test client that
exercises all of your methods.

1.2.17 Robust implementation of rational numbers. Use assertions to develop an
implementation of Rational (see Exercise 1.2.16) that is immune to overflow.
*/

public class Rational {
    public static void main(String[] args) {
        String excMessage = "Введите числитель и знаменатель через / для каждого числа" +
                " в пределах интервала -2^63...2^63-1";
        try {
            if (args.length == 2) {
                String a1 = args[0];
                String b1 = args[1];

                Rational a = new Rational(a1);
                Rational b = new Rational(b1);

                System.out.println(a.plus(b).toString());
                System.out.println(a.minus(b).toString());
                System.out.println(a.times(b).toString());
                System.out.println(a.divides(b).toString());
                System.out.println(a.equals(b));
            }
            else {
                System.out.println(excMessage);
                System.exit(1);
            }
        }
        catch (ArithmeticException e1) {
            System.out.println(e1.getMessage());
            System.exit(1);
        }
        catch (NumberFormatException e2) {
            System.out.println(excMessage);
        }
        catch (ArrayIndexOutOfBoundsException e3) {
            System.out.println(excMessage);
            System.exit(1);
        }
    }

    private final long numer;
    private final long denom;

    Rational(long num, long den) throws ArithmeticException {
        long gcdTemp = gcd( num, den );

        if (den == 0) throw new ArithmeticException();

        if ( gcdTemp > 1 ) {
            num = num / gcdTemp;
            den = den / gcdTemp;
        }
        if (den < 0) {
            numer = -num;
            denom = -den;
        } else {
            numer = num;
            denom = den;
        }
    }

    Rational(String rat) {
        String[] ratArgs;
        long num;
        long den;
        if ( rat.indexOf('/') == -1 ) {
            num = Long.parseLong(rat);
            den = 1;
        }
        else {
            ratArgs = rat.split("/");
            num = Long.parseLong(ratArgs[0]);
            den = Long.parseLong(ratArgs[1]);
            if (den == 0) throw new ArithmeticException();
        }

        long gcdTemp = gcd(num, den);

        if ( gcdTemp > 1 ) {
            num = num / gcdTemp;
            den = den / gcdTemp;
        }
        if (den < 0) {
            numer = -num;
            denom = -den;
        } else {
            numer = num;
            denom = den;
        }
    }

    public long numerator()
    { return this.numer; }

    public long denominator()
    { return this.denom; }

    // Сумма this и b
    public Rational plus(Rational b) {
        long sumNumer;
        long sumDenom;
        Rational sum;
        long aN = this.numer;
        long aD = this.denom;
        long bN = b.numer;
        long bD = b.denom;

        if (aD == bD) {
            sumNumer = sPlus(aN, bN);
            return new Rational(sumNumer, aD);
        }
        else{
            // Если знаменатели различны, перемножаем крест-накрест числители и знаменатели
            long t1 = sTimes(aN, bD);
            long t2 = sTimes(bN, aD);
            sumNumer = sPlus(t1, t2);
            long gcdDenom = gcd(aD, bD);

            if ( gcdDenom > 1) {
                // Если у знаменателей есть НОД, находим общий знаменатель:
                // перемножаем знаменатели и делим произведение на НОД
                sumDenom = sTimes(aD, bD) / gcdDenom;
                // Находим числители чисел
                long t3 = sTimes(aN, sumDenom) / aD;
                long t4 = sTimes(bN, sumDenom) / bD;
                // Числитель
                sumNumer = sPlus(t3, t4);
            }
            else sumDenom = sTimes(aD, bD);

            return new Rational(sumNumer, sumDenom);
        }
    }

    // Вычитание из this b
    public Rational minus(Rational b) {
        Rational bTemp = new Rational(-b.numer, b.denom);
        return this.plus(bTemp);
    }

    // Умножение this на b
    public Rational times(Rational b) {
        long timesNumer;
        long timesDenom;
        Rational times;

        timesNumer = sTimes(this.numer, b.numer);
        timesDenom = sTimes(this.denom, b.denom);

        times = new Rational(timesNumer, timesDenom);

        return times;
    }

    // Деление this на that
    public Rational divides(Rational b) {
        Rational bTemp = new Rational(b.denom, b.numer);
        return this.times(bTemp);
    }

    // Проверка на равенство this и that
    public boolean equals(Object x) {

        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;

        Rational that = (Rational) x;

        return this.numer == that.numer && this.denom == that.denom;
    }

    // Safe plus
    private static long sPlus(final long a, final long b) throws ArithmeticException {
        long c = a + b;
        if (((a & b & ~c) | (~a & ~b & c)) < 0) {
            throw new ArithmeticException("long overflow sPlus(" + a + ", " + b + ")");
        }
        else return c;
    }

    // Safe times
    private static long sTimes(final long a, final long b) throws ArithmeticException {
        if ( a == 0 || b == 0 ) return 0;
        long c = a * b;
        long c2 = c / b;
        if (a != c2) {
            throw new ArithmeticException("long overflow sTimes(" + a + ", " + b + ")");
        }
        else return c;
    }

    // Строковое представление числа.
    // Если числитель - ноль, число - ноль.
    // Если знаменатель - один, число - числитель.
    public String toString () {
        if (numerator() == 0) return "0";
        if (denominator() == 1) return numerator() + "";
        return numerator() + "/" + denominator();
    }

    // Определение наибольшего общего делителя (НОД)
    private static long gcd(long p1, long q1) {
        long r;
        long p = Math.abs(p1);
        long q = Math.abs(q1);
        while (q != 0) {
            r = p % q;
            p = q;
            q = r;
        }
        return p;
    }
}