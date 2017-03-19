package Algorithms_Chapter_1.Ex_1_3_39;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vl on 07.02.17.
 */
public class RingBufferTest {
    private boolean invariantTest(RingBuffer bufferToTest) throws Exception {
        return invariantTestOne(bufferToTest) && invariantTestTwo(bufferToTest);
    }

    private boolean invariantTestOne(RingBuffer bufferToTest) throws Exception {

        // Тест 1
        // Если буфер пустой, все элементы массива очереди
        // должны быть null, индексы dequeueFrom и enqueueAt
        // равны нулю
        if (bufferToTest.size() == 0) {
            Object[] itemsArray = bufferToTest.getItems();

            for (Object arg : itemsArray) {
                if (arg != null) {
                    return false;
                }
            }

            return bufferToTest.getDequeueFromIndex() == 0 && bufferToTest.getEnqueueAtIndex() == 0;

        } else {

            // Если буфер не пустой, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestTwo(RingBuffer bufferToTest) throws Exception {

        // Тест 2
        // Если в буфере есть элементы, все элементы между
        // dequeueFrom (включительно) и enqueueAt (невключительно)
        // - ненулевые, все остальные - нулевые
        if (bufferToTest.size() > 0) {
            Object[] itemsArray = bufferToTest.getItems();

            int arrayLength = itemsArray.length;

            // Проход по элементам между dequeueFrom и enqueueAt
            for (int i = 0; i < bufferToTest.size(); i++) {
                if (itemsArray[(bufferToTest.getDequeueFromIndex() + i)
                        % arrayLength] == null) {
                    return false;
                }
            }

            // Проход по элементам вне dequeueFrom и enqueueAt
            for (int i = 0; i < arrayLength - bufferToTest.size(); i++) {
                if (itemsArray[(bufferToTest.getEnqueueAtIndex() + i)
                        % arrayLength] != null) {
                    return false;
                }
            }

            return true;

        } else {

            // Если буфер не пустой, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    @Test
    public void enqueueTest() throws Exception {

        // Аргументы для добавления в буфер
        String[] bufferArgs = {"one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten"};

        // Буферы для тестов
        RingBufferForTests originalBuffers = new RingBufferForTests(2, bufferArgs);


        // Тест на добавление одного элемента в пустой буфер
        RingBuffer<String> buffer0 = new RingBuffer<>(5);

        buffer0.enqueue("one");

        Iterator<String> iterator0 = buffer0.iterator();

        String checkElement = iterator0.next();

        assertTrue(checkElement.equals("one") && invariantTest(buffer0));


        // Тест на добавление нескольких элементов в буфер
        RingBuffer<String> buffer1 = new RingBuffer<>(5);

        String[] bufferArgs1 = {"one", "two", "three", };

        for (String arg : bufferArgs1) {
            buffer1.enqueue(arg);
        }

        Iterator<String> iterator1 = buffer1.iterator();

        boolean flag1 = true;

        for (String arg : bufferArgs1) {
            String bufArg = iterator1.next();

            if (!bufArg.equals(arg)) {
                flag1 = false;
            }
        }

        assertTrue(flag1 && invariantTest(buffer1));


        // Тест на добавление/удаление элементов из буфера
        // по кругу несколько раз

        // Заполненный буфер
        RingBuffer<String> buffer2 = originalBuffers.getBuffer(0);

        int size2 = buffer2.size();

        for (int i = 0; i < size2 * 2; i++) {
            buffer2.dequeue();

            buffer2.enqueue("extra " + i);
        }

        boolean flag2 = true;

        int i = 10;

        for (Iterator<String> iterator2 = buffer2.iterator(); iterator2.hasNext(); i++) {
            String arg = iterator2.next();

            if (!arg.equals("extra " + i)) {
                flag2 = false;
            }
        }

        assertTrue(flag2 && invariantTest(buffer2));


        // Тест на перехват исключения при создании
        // буфера с отрицательным размером
        try {
            RingBuffer<String> buffer3 = new RingBuffer<>(-1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при добавлении
        // элемента в заполненный буфер
        RingBuffer<String> buffer4 = originalBuffers.getBuffer(1);

        try {
            buffer4.enqueue("eleven");
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    @Test
    public void dequeueTest() throws Exception {

        // Аргументы для добавления в буфер
        String[] bufferArgs = {"one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten"};

        // Буферы для тестов
        RingBufferForTests originalBuffers = new RingBufferForTests(1, bufferArgs);


        // Тест на успешное удаление всех элементов буфера
        RingBuffer<String> buffer0 = originalBuffers.getBuffer(0);

        int bufferSize = buffer0.size();

        for (int i = 0; i < bufferSize; i++) {
            buffer0.dequeue();
        }

        Iterator<String> iterator = buffer0.iterator();

        boolean flag0 = true;

        while (iterator.hasNext()) {
            flag0 = false;
        }

        assertTrue(flag0 && buffer0.size() == 0 && invariantTest(buffer0));


        // Тест на перехват исключения при удалении элемента
        // из пустого буфера
        RingBuffer<String> buffer1 = new RingBuffer<>(5);

        try {
            buffer1.dequeue();
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    @Test
    public void iteratorTest() throws Exception {

        // Тест на возврат нуля элементов из пустого буфера
        RingBuffer<String> buffer0 = new RingBuffer<>(5);

        boolean flag0 = true;

        for (Iterator iterator = buffer0.iterator(); iterator.hasNext(); ) {
            flag0 = false;
        }

        assertTrue(flag0 && invariantTest(buffer0));


        // Тест на возврат элементов полузаполненного буфера
        RingBuffer<String> buffer1 = new RingBuffer<>(5);

        String[] bufferArgs1 = {"one", "two", "three", };

        for (String arg : bufferArgs1) {
            buffer1.enqueue(arg);
        }

        boolean flag1 = true;

        int i1 = 0;

        for (Iterator iterator = buffer1.iterator(); iterator.hasNext(); i1++) {
            flag1 = iterator.next().equals(bufferArgs1[i1]);
        }

        assertTrue(flag1 && invariantTest(buffer1));


        // Тест на возврат элементов полного буфера
        RingBuffer<String> buffer2 = new RingBuffer<>(5);

        String[] bufferArgs2 = {"one", "two", "three", "four", "five", };

        for (String arg : bufferArgs2) {
            buffer2.enqueue(arg);
        }

        boolean flag2 = true;

        int i2 = 0;

        for (Iterator iterator = buffer2.iterator(); iterator.hasNext(); i2++) {
            flag2 = iterator.next().equals(bufferArgs2[i2]);
        }

        assertTrue(flag2 && invariantTest(buffer2));
    }
}