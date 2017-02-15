package Ex_1_3_44;

import java.util.Iterator;

/**
 * Created by vl on 13.02.17.
 */
public class StackArrayForChar implements Iterable {

    private Character[] a = new Character[1]; // stack items
    private int N = 0; // number of items

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int arraySize() {

        // Размер массива "a" - для тестов
        return a.length;
    }

    public void push(char c) {

        // Добавление элемента в стек
        // Если массив заполнен, увеличить его вдвое
        if (N == a.length) {
            resize(2 * a.length);
        }

        // Добавить элемент в стек
        a[N++] = c;
    }

    public char pop() {

        // Удаление и возврат последнего добавленного элемента
        // Значение удаляемого и возвращаемого элемента
        char itemToReturn;

        // Если стек пуст, бросить исключение
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }

        // Значение удаляемого элемента для возврата
        itemToReturn = a[size() - 1];

        a[size() - 1] = null;

        N--;

        // Если размер стека стал меньше четверти массива,
        // укоротить массив вдвое
        if ( N <= a.length / 4 ) {
            resize(a.length / 2);
        }

        return itemToReturn;
    }

    public Character[] getStackAsArray() {

        // Возврат всех элементов стека в виде массива
        Character[] arrayToReturn = new Character[size()];

        System.arraycopy(a, 0, arrayToReturn, 0, size());

        return arrayToReturn;
    }

    public Character[] getInvertedStackAsArray() {

        // Возврат всех элементов стека в обратном
        // порядке в виде массива
        Character[] arrayToReturn = new Character[size()];

        for (int i = 0; i < arrayToReturn.length; i++) {
            arrayToReturn[i] = a[size() - 1 - i];
        }

        return arrayToReturn;
    }

    private void resize(int max) {

        // Move stack to a new array of size max.
        Character[] temp = new Character[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<java.lang.Character> iterator() {
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<java.lang.Character> {
        private int currentIndex = 0;
        private Character current = a[currentIndex];
        private Character realCurrent = current;

        public boolean hasNext() {
            return realCurrent != null;
        }

        public boolean hasPrevious() {
            return realCurrent != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Character next() {
            current = a[currentIndex++];
            if (currentIndex < a.length) {
                realCurrent = a[currentIndex];
            } else {
                realCurrent = null;
            }

            return current;
        }

        public Character previous() {
            current = a[currentIndex--];
            if (currentIndex >= 0) {
                realCurrent = a[currentIndex];
            } else {
                realCurrent = null;
            }

            return current;
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        StackArrayForChar stackToCompareWith = (StackArrayForChar) x;

        if(this.size() != stackToCompareWith.size()) return false;

        Iterator iterator2 = stackToCompareWith.iterator();

        for(Iterator iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        StackArrayForChar stack1 = new StackArrayForChar();

        stack1.push('c');
        stack1.push('b');
        stack1.pop();
        stack1.push('a');
        stack1.push('z');
        stack1.push('z');
        stack1.push('z');
        stack1.pop();
        stack1.pop();
        stack1.pop();

        Iterator iterator = stack1.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("stack el-s count = " + stack1.size());
        System.out.println("array size = " + stack1.arraySize());

        Character[] a = stack1.getStackAsArray();
        for (char arg : a) {
            System.out.println(arg);
        }

        Character[] b = stack1.getInvertedStackAsArray();
        for (char arg : b) {
            System.out.println(arg);
        }
    }
}
