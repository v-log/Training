package Ex_1_2_3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import static edu.princeton.cs.algs4.StdDraw.rectangle;
import static edu.princeton.cs.algs4.StdDraw.setPenColor;

/**
 * Created by vl on 06.07.16.
 */

/*
1.2.3 Write an Interval2D client that takes command-line arguments N , min , and max
and generates N random 2D intervals whose width and height are uniformly distributed
between min and max in the unit square. Draw them on StdDraw and print the number
of pairs of intervals that intersect and the number of intervals that are contained in one
another.
 */

public class Interval2D {
    public static void main(String[] args) {
        try {
            if (args.length == 3) {
                int N = Integer.parseInt(args[0]);
                double min = Double.parseDouble(args[1]);
                double max = Double.parseDouble(args[2]);
                Interval2DMethod(N, min, max);
            }
            else {
                System.out.println("Необходимо ввести через пробел одно целое и два вещественных числа");
                System.exit(1);
            }
        }
        catch (NumberFormatException e1) {
            System.out.println("Необходимо ввести через пробел одно целое и два вещественных числа");
            System.exit(1);
        }

    }

    private static class Rectangle {
        final double x;
        final double y;
        final double halfWidth;
        final double halfHeight;

        Rectangle (double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.halfWidth = width;
            this.halfHeight = height;
        }
    }

    public static void Interval2DMethod (int N, double min, double max) {

        // Инициализация плоскости в 1.5 раза больше самого большого прямоугольника (для удобства восприятия)
        StdDraw.setXscale(0, max - min + (max - min)/2);
        StdDraw.setYscale(0, max - min + (max - min)/2);
        StdDraw.setPenRadius(.003);

        Rectangle[] rectArray = new Rectangle[N];

        int rectIntersectPairsCount = 0;
        int rectInclusionPairsCount = 0;


        for (int i = 0; i < N; i++) {
            // Создание случайных прямоугольников случайного цвета
            setPenColor(StdRandom.uniform(0, 255), StdRandom.uniform(0, 255), StdRandom.uniform(0, 255));
            double halfWidth = StdRandom.uniform(0, max - min) / 2;
            double halfHeight = StdRandom.uniform(0, max - min) / 2;
            double x = StdRandom.uniform(min + halfWidth, max - halfWidth);
            double y = StdRandom.uniform(min + halfHeight, max - halfHeight);

            rectArray[i] = new Rectangle(x, y, halfWidth, halfHeight);
            rectangle(x, y, halfWidth, halfHeight);

            // Сравнение текущего прямоугольника с предыдущими
            for (int j = 0; j <= i; j++) {
                // Исключение сравнения прямоугольника с самим собой
                if (i == j) continue;

                // Определение середин сторон прямоугольника
                double il = rectArray[i].x - rectArray[i].halfWidth;
                double ir = rectArray[i].x + rectArray[i].halfWidth;
                double jl = rectArray[j].x - rectArray[j].halfWidth;
                double jr = rectArray[j].x + rectArray[j].halfWidth;
                double it = rectArray[i].y + rectArray[i].halfHeight;
                double ib = rectArray[i].y - rectArray[i].halfHeight;
                double jt = rectArray[j].y + rectArray[j].halfHeight;
                double jb = rectArray[j].y - rectArray[j].halfHeight;

                // wI - widthIntersect;  hI - heightIntersect;  wInc - widthInclude;  hInc - heightInclude;
                boolean wI1 = Intersect(il, ir, jl, jr);
                boolean hI1 = Intersect(ib, it, jb, jt);
                boolean wI2 = Intersect(jl, jr, il, ir);
                boolean hI2 = Intersect(jb, jt, ib, it);

                boolean wInc1 = Include(il, ir, jl, jr);
                boolean hInc1 = Include(ib, it, jb, jt);
                boolean wInc2 = Include(jl, jr, il, ir);
                boolean hInc2 = Include(jb, jt, ib, it);

                // Проверка на включение одного прямоугольника другим, и наоборот
                if ( (wInc1 && hInc1) || (wInc2 && hInc2) ) rectInclusionPairsCount++;
                // Проверка на пересечение площадей прямоугольника - совпадение сторон считается пересечением
                else if ( (wI1 && hI1) || (wI2 && hI2) ||
                        (wI1 && hInc1) || (wI2 && hInc1) ||
                        (wI1 && hInc2) || (wI2 && hInc2) ||
                        (hI1 && wInc1) || (hI2 && wInc1) ||
                        (hI1 && wInc2) || (hI2 && wInc2))  rectIntersectPairsCount++;
            }

        }
        System.out.println("Rectangles intersections: " + rectIntersectPairsCount);
        System.out.println("Rectangles inclusions: " + rectInclusionPairsCount);
    }

    // Определение пересечения интервалов
    private static boolean Intersect (double in1St, double in1End, double in2St, double in2End) {
        return (  ( (in1St <= in2End) && (in1St >= in2St) ) )
                ||( (in1End >= in2St) && (in1End <= in2End) );
    }

    // Определение включения интервалов
    private static boolean Include (double in1St, double in1End, double in2St, double in2End) {
        return ( (in1St < in2End) && (in1St > in2St) && (in1End < in2End) && (in1End > in2St) );
    }
}












