package Ex_1_3_39;

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
        // должны быть null, индексы first и last равны "-1"
        if (bufferToTest.size() == 0) {
            Object[] itemsArray = bufferToTest.getItems();

            for (Object arg : itemsArray) {
                if (arg != null) {
                    return false;
                }
            }

            if (bufferToTest.getFirstIndex() != -1 || bufferToTest.getLastIndex() != -1) {
                return false;
            }

            return true;

        } else {

            // Если буфер не пустой, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestTwo(RingBuffer bufferToTest) throws Exception {

        // Тест 2
        // Если в буфере есть элементы, все элементы между
        // first и last - ненулевые, все остальные - нулевые
        if (bufferToTest.size() > 0) {

            int first = bufferToTest.getFirstIndex();
            int last = bufferToTest.getLastIndex();
            Object[] itemsArray = bufferToTest.getItems();

            for (int i = 0; i < bufferToTest.size(); i++) {
                if (itemsArray[(first + i) % itemsArray.length] == null) {
                    return false;
                }
            }

            for (int i = 1; i <= itemsArray.length - bufferToTest.size(); i++) {
                if (itemsArray[(last + i) % itemsArray.length] != null) {
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

        // Буферы для тестов
        RingBufferForTests originalBuffers = new RingBufferForTests(2);


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

        for (int i = 0; i < buffer2.size() * 2; i++) {
            buffer2.dequeue();
            buffer2.enqueue("extra " + i);
        }

        boolean flag2 = true;
        int i = 10;

        for (Iterator<String> iterator2 = buffer2.iterator(); iterator2.hasNext();) {

            String arg = iterator2.next();

            if (!arg.equals("extra " + i)) {
                flag2 = false;
            }

            i++;
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

        RingBufferForTests originalBuffers = new RingBufferForTests(1);


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
    public void returnBufferTest() throws Exception {

        RingBufferForTests originalBuffers = new RingBufferForTests(1);


        // Тест на возврат верного массива очереди буфера

    }
}