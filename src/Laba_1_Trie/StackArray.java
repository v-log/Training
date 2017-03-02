package Laba_1_Trie;

import Ex_1_3_38.GeneralizedQueueAsArray;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Created by vl on 24.02.17.
 */
public class StackArray<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1]; // stack items
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

    public Item peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return a[size() - 1];
        }
    }

    public void push(Item x) {

        // Добавление элемента в стек
        // Если массив заполнен, увеличить его вдвое
        if (N == a.length) {
            resize(2 * a.length);
        }

        // Добавить элемент стек
        a[N++] = x;
    }

    public Item pop() {

        // Удаление и возврат последнего добавленного элемента
        // Значение удаляемого и возвращаемого элемента
        Item itemToReturn;

        // Если стек пуст, бросить исключение
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
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

    private void resize(int max) {

        // Move stack to a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {
        return new StackArray<Item>.StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private Item current = a[currentIndex];
        private Item realCurrent = current;

        public boolean hasNext() {
            return realCurrent != null;
        }

        public Item next() {
            current = a[currentIndex++];
            if (currentIndex < a.length) {
                realCurrent = a[currentIndex];
            } else {
                realCurrent = null;
            }

            return current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        StackArray<Item> stackToCompareWith = (StackArray<Item>) x;

        if(this.size() != stackToCompareWith.size()) return false;

        Iterator<Item> iterator2 = stackToCompareWith.iterator();

        for(Iterator<Item> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }
}
