package Ex_1_3_39;

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

    // Индекс первого элемента очереди
    private int first;
    // Индекс последнего элемента очереди
    private int last;
    // Количество элементов в очереди
    private int N;
    // Массив элементов очереди-буфера
    private Item[] items;

    @SuppressWarnings("unchecked")
    RingBuffer(int size) {

        // Бросить исключение, если указан отрицательный
        // размер массива очереди
        if (size <= 0) {
            throw new IllegalArgumentException("Размер " +
                    "буфера должен быть больше нуля");
        }

        items = (Item[]) new Object[size];
        N = 0;
        first = -1;
        last = -1;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int getFirstIndex() {
        // Для тестов
        return first;
    }

    public int getLastIndex() {
        // Для тестов
        return last;
    }

    public Item[] getItems() {
        // Для тестов
        return items;
    }

    public void enqueue(Item itemToEnqueue) throws ArrayIndexOutOfBoundsException {

        // Добавление элемента в очередь буфера

        // Если буфер заполнен, бросить исключение
        if (N == items.length) {
            throw new ArrayIndexOutOfBoundsException("Буфер заполнен");
        }

        // Если буфер пуст, вставить первый элемент в начало
        // массива и установить индексы first и last на новый элемент
        if (first == -1) {

            items[0] = itemToEnqueue;
            first = 0;
            last = 0;
        } else {

            // Иначе, если буфер не пуст, вставить элемент после последнего
            // и установить индекс last на новый элемент
            last = circIncrease(last);
            items[last] = itemToEnqueue;
        }

        N++;
    }

    public Item dequeue() throws ArrayIndexOutOfBoundsException {

        // Удаление и возврат первого элемента очереди буфера

        Item itemToReturn;

        // Если очередь пуста, бросить исключение
        if (N == 0) throw new ArrayIndexOutOfBoundsException("Буфер пуст");

        // Если в очереди один элемент, вернуть его и установить в first и last
        // значения как при инициализации буфера
        if (N == 1) {
            itemToReturn = items[first];
            items[first] = null;
            first = -1;
            last = -1;
        } else {
            // Иначе, если в очереди больше одного элемента,
            // вернуть значение с индексом first, сделать его
            // null массиве очереди, и сместить индекс first
            // на одну позицию вправо
            itemToReturn = items[first];
            items[first] = null;
            first = circIncrease(first);
        }

        N--;

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

    public Object[] returnBuffer() {

        // Вернуть массив объектов очереди в порядке очереди

        // Возвращаемый массив
        @SuppressWarnings("unchecked")
        Object[] arrayToReturn = new Object[N];

        // Заполнение возвращаемого массива в порядке очереди
        int i = 0;

        for (Iterator<Item> iterator = iterator(); iterator.hasNext(); ) {
            Item arg = iterator.next();
            arrayToReturn[i] = arg;
            i++;
        }

        return arrayToReturn;
    }

    public Iterator<Item> iterator() throws UnsupportedOperationException {
        return new RingBufferIterator();
    }

    private class RingBufferIterator implements Iterator<Item> {
        private int currentIndex = first;
        private Item nextItem = currentIndex == -1 ? null : items[currentIndex];
        private Item currentItem;

        public boolean hasNext() {
            return nextItem != null;
        }

        public Item next() {

            currentItem = nextItem;

            if (currentIndex == last) {
                nextItem = null;
            } else {
                currentIndex = circIncrease(currentIndex);
                nextItem = items[currentIndex];
            }

            return currentItem;
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

    private int circIncrease(int arg) {
        return (arg + 1) % items.length;
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

        System.out.println("returnBuffer(): ");
        Object[] b = a.returnBuffer();
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }
}