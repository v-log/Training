package Algorithms_Chapter_1.Ex_1_3_39;

import java.util.Iterator;

/**
 * Created by vl on 10.01.17.
 */

/*
Exercise 1.3.39 Ring buffer.
A ring buffer, or circular queue, is a FIFO data structure
of a fixed size N. It is useful for transferring data between asynchronous
processes or for storing log files. When the buffer is empty, the consumer
waits until data is deposited; when the buffer is full, the producer waits
to deposit data. Develop an API for a RingBuffer and an implementation that
uses an array representation (with circular wrap-around).
*/

public class RingBuffer<Item> implements Iterable<Item> {

    // Индекс считываемого элемента буфера
    private int dequeueFrom;
    // Индекс вставляемого элемента буфера
    private int enqueueAt;
    // Массив элементов очереди буфера
    private Item[] items;
    // Флаг заоплненности буфера
    private boolean isFull;

    @SuppressWarnings("unchecked")
    RingBuffer(int size) {

        // Бросить исключение, если указан отрицательный
        // или нулевой размер буфера
        if (size <= 0) {
            throw new IllegalArgumentException("Размер " +
                    "буфера должен быть больше нуля");
        }

        items = (Item[]) new Object[size];
        enqueueAt = 0;
        dequeueFrom = 0;
        isFull = false;
    }

    public boolean isEmpty() {
        return dequeueFrom == enqueueAt && !isFull();
    }

    public int size() {
        int returnSize = enqueueAt >= dequeueFrom ?
                enqueueAt - dequeueFrom : enqueueAt + items.length - dequeueFrom;
        return isFull() ? items.length : returnSize;
    }

    public int getDequeueFromIndex() {
        // Для тестов
        return dequeueFrom;
    }

    public int getEnqueueAtIndex() {
        // Для тестов
        return enqueueAt;
    }

    public Item[] getItems() {
        // Для тестов
        return items;
    }

    private int circIncrease(int arg) {
        return (arg + 1) % items.length;
    }

    private boolean isFull() {
        return isFull;
    }

    public void enqueue(Item itemToEnqueue) throws ArrayIndexOutOfBoundsException,
            IllegalArgumentException {

        // Добавление элемента в буфер

        // Если буфер заполнен, бросить исключение
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Буфер заполнен");
        }

        // Добавить новый элемент
        items[enqueueAt] = itemToEnqueue;

        enqueueAt = circIncrease(enqueueAt);

        if (enqueueAt == dequeueFrom) {
            isFull = true;
        }
    }

    public Item dequeue() throws ArrayIndexOutOfBoundsException {

        // Удаление и возврат первого элемента буфера

        Item itemToReturn;

        // Если буфер пуст, бросить исключение
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("Буфер пуст");

        // Удалить и вернуть первый элемент очереди буфера
        itemToReturn = items[dequeueFrom];

        items[dequeueFrom] = null;

        dequeueFrom = circIncrease(dequeueFrom);

        isFull = false;

        return itemToReturn;
    }

    public void printArray() {

        // Вывести на экран содержимое буфера в порядке очереди

        System.out.println("------------");
        System.out.println("Buffer items:");

        for (Iterator<Item> iterator = iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }

        System.out.println("------------");
    }

    public Iterator<Item> iterator() throws UnsupportedOperationException {
        return new RingBufferIterator();
    }

    private class RingBufferIterator implements Iterator<Item> {
        private int currentIndex = dequeueFrom;
        private Item nextItem = items[currentIndex];
        private Item currentItem;

        public boolean hasNext() {
            return nextItem != null;
        }

        public Item next() {

            currentItem = nextItem;

            if (currentIndex == circDearease(enqueueAt)) {
                nextItem = null;
            } else {
                currentIndex = circIncrease(currentIndex);
                nextItem = items[currentIndex];
            }

            return currentItem;
        }

        private int circDearease(int arg) {
            return arg - 1 == -1 ? items.length - 1 : arg;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object x) {

        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;

        @SuppressWarnings("unchecked")
        RingBuffer<Item> bufferToCompareWith = (RingBuffer<Item>) x;

        if (this.size() != bufferToCompareWith.size()) return false;

        Iterator<Item> iterator2 = bufferToCompareWith.iterator();

        for (Iterator<Item> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        RingBuffer<Integer> a = new RingBuffer<>(5);

        for (int i = 0; i < 5; i++) {
            System.out.println("++++++enqueue " + (i));
            a.enqueue(i);
        }

        System.out.println("------dequeue 0");
        a.dequeue();

        System.out.println("++++++enqueue 5");
        a.enqueue(5);

        System.out.println("------dequeue 1");
        a.dequeue();

        System.out.println("++++++enqueue 6");
        a.enqueue(6);

        System.out.println("------dequeue all (5)");
        for (int i = 0; i < 5; i++) {
            a.dequeue();
        }

        a.printArray();

        System.out.println("Iterator output:");
        for(Iterator<Integer> iterator1 = a.iterator(); iterator1.hasNext();) {
            System.out.println(iterator1.next());
        }
    }
}