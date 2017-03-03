package Chapter_1.Ex_1_2_3;

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
                    rectArray[i] = Rectangle.randomRect(min, max);
                    // Отрисовка созданных прямоугольников на плоскости
                    rectangle(rectArray[i].x, rectArray[i].y, rectArray[i].halfWidth, rectArray[i].halfHeight);

                    // Сравнение текущего прямоугольника с предыдущими
                    for (int j = 0; j < i; j++) {
                        if ( rectArray[i].rectInclude(rectArray[j]) ) rectInclusionPairsCount++;
                        else if ( rectArray[j].rectInclude(rectArray[i]) ) rectInclusionPairsCount++;
                        else if ( rectArray[i].rectIntersect(rectArray[j]) ) rectIntersectPairsCount++;
                    }
                }
                System.out.println("Rectangles intersections: " + rectIntersectPairsCount);
                System.out.println("Rectangles inclusions: " + rectInclusionPairsCount);
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

        Rectangle (double x, double y, double halfWidth, double halfHeight) {
            this.x = x;
            this.y = y;
            this.halfWidth = halfWidth;
            this.halfHeight = halfHeight;
        }

        public static Rectangle randomRect (double min, double max) {
            double halfWidth = StdRandom.uniform(0, max - min) / 2;
            double halfHeight = StdRandom.uniform(0, max - min) / 2;
            double x = StdRandom.uniform(min + halfWidth, max - halfWidth);
            double y = StdRandom.uniform(min + halfHeight, max - halfHeight);
            return new Rectangle (x, y, halfWidth, halfHeight);
        }

        // Проверка на включение прямоугольником this прямоугольника that
        public boolean rectInclude (Rectangle that) {

            // Определение середин сторон прямоугольника
            double il = this.x - this.halfWidth;
            double ir = this.x + this.halfWidth;
            double it = this.y + this.halfHeight;
            double ib = this.y - this.halfHeight;

            double jl = that.x - that.halfWidth;
            double jr = that.x + that.halfWidth;
            double jt = that.y + that.halfHeight;
            double jb = that.y - that.halfHeight;

            // wInc - width include;  hInc - height include;
            boolean wInc = intervalInclude(il, ir, jl, jr);
            boolean hInc = intervalInclude(ib, it, jb, jt);

            return wInc && hInc;
        }

        // Проверка пересечения прямоугольников this и that
        public boolean rectIntersect (Rectangle that) {

            // Определение середин сторон прямоугольника
            double il = this.x - this.halfWidth;
            double ir = this.x + this.halfWidth;
            double it = this.y + this.halfHeight;
            double ib = this.y - this.halfHeight;

            double jl = that.x - that.halfWidth;
            double jr = that.x + that.halfWidth;
            double jt = that.y + that.halfHeight;
            double jb = that.y - that.halfHeight;

            // wI - widthIntersect;  hI - heightIntersect;
            boolean wI1 = intervalIntersect(il, ir, jl, jr);
            boolean hI1 = intervalIntersect(ib, it, jb, jt);

            // wInc - widthInclude;  hInc - heightInclude;
            boolean wInc1 = intervalInclude(il, ir, jl, jr);
            boolean hInc1 = intervalInclude(ib, it, jb, jt);
            boolean wInc2 = intervalInclude(jl, jr, il, ir);
            boolean hInc2 = intervalInclude(jb, jt, ib, it);

            return    (wI1 && hI1) ||
                    (wI1 && hInc1) ||
                    (wI1 && hInc2) ||
                    (hI1 && wInc1) ||
                    (hI1 && wInc2);
        }

        // Определение включения интервалов
        private static boolean intervalInclude (double in1St, double in1End, double in2St, double in2End) {
            return  (in1St < in2End) && (in1St > in2St) &&
                    (in1End < in2End) && (in1End > in2St);
        }

        // Определение пересечения интервалов
        private static boolean intervalIntersect (double in1St, double in1End, double in2St, double in2End) {
            return  ( (in1St <= in2End) && (in1St >= in2St) ) ||
                    ( (in1End >= in2St) && (in1End <= in2End) );
        }
    }
}