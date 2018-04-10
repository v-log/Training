package Chapter_1.Ex_1_3_38;

import java.util.Iterator;

/**
 * Created by vl on 31.10.16.
 */


/* 1.3.38 Delete k-th element. Implement a class that supports the following API:
public class GeneralizedQueue<Item> {
    GeneralizedQueue();        // create an empty queue
    boolean isEmpty();         // is the queue empty?
    void insert(Item x);       // add an item
    Item delete(int k);        // delete and return the k-th least recently inserted item
}

First, develop an implementation that uses an array implementation, and then develop
one that uses a linked-list implementation. Note: the algorithms and data structures
that we introduce in Chapter 3 make it possible to develop an implementation that can
guarantee that both insert() and delete() take time proportional to the logarithm of
the number of items in the queue — see Exercise 3.5.27. */


public class GeneralizedQueueAsArray<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1]; // queue items
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

    public void insert(Item x) {

        // Добавление элемента в конец очереди
        // Если массив заполнен, увеличить его вдвое
        if (N == a.length) {
            resize(2 * a.length);
        }

        // Добавить элемент в конец очереди
        a[N++] = x;
    }

    public Item delete(int k) {

        // Удаление и возврат k-го последнего добавленного элемента
        // Значение удаляемого и возвращаемого элемента
        Item itemToReturn;
        // Индекс удаляемого элемента
        int itemToRemoveIndex;

        // Если очередь пуста, бросить исключение
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        // Если k выходит за рамки индексов массива, бросить исключение
        if ( k <= 0 || k > size() ) {
            throw new IllegalArgumentException("\"k\" must be: " +
                    " 0 < k <= " + size());
        }

        // Индекс удаляемого элемента
        itemToRemoveIndex = size() - k;

        // Значение удаляемого элемента для возврата
        itemToReturn = a[itemToRemoveIndex];

        // Если удаляется элемент не в конце очереди,
        // сдвинуть все элементы ближе к концу очереди
        // на одну позицию ближе к началу очереди
        if ( itemToRemoveIndex < size() - 1 ) {
            for (int i = itemToRemoveIndex; i < itemToRemoveIndex + k - 1; i++) {
                a[i] = a[i + 1];
                a[i + 1] = null;
            }
        } else {
            // Иначе, если удаляется последний элемент в очереди,
            // обнулить его
            a[itemToRemoveIndex] = null;
        }

        N--;

        // Если размер очереди стал меньше четверти массива,
        // укоротить массив вдвое
        if ( N <= a.length / 4 ) {
            resize(a.length / 2);
        }

        return itemToReturn;
    }

    public Item dequeue() {

        // Удаление и возврат первого элемента в очереди
        return delete(size());
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
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private Item current = a[currentIndex];
        private Item realCurrent = current;

        public boolean hasNext() {
            return realCurrent != null;
        }

        public boolean hasPrevious() {
            return realCurrent != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
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

        public Item previous() {
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

        GeneralizedQueueAsArray<Item> queueToCompareWith = (GeneralizedQueueAsArray<Item>) x;

        if(this.size() != queueToCompareWith.size()) return false;

        Iterator<Item> iterator2 = queueToCompareWith.iterator();

        for(Iterator<Item> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }
}