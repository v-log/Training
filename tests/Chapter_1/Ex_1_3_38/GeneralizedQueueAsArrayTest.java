package Chapter_1.Ex_1_3_38;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vl on 27.11.16.
 */
public class GeneralizedQueueAsArrayTest {
    private boolean invariantTest(GeneralizedQueueAsArray queueToTest) throws Exception {
        return invariantTestOne(queueToTest) && invariantTestTwo(queueToTest)
                && invariantTestThree(queueToTest);
    }

    private boolean invariantTestOne(GeneralizedQueueAsArray queueToTest) throws Exception {

        // Тест 1
        // Если очередь пустая (size() == 0), длина массива "а"
        // должна быть равна 1, и 0-й элемент равен null
        if (queueToTest.size() == 0) {
            Iterator iterator = queueToTest.iterator();

            if (queueToTest.arraySize() == 1 && iterator.next() == null) {
                return true;
            } else {
                // Иначе тест провален
                return false;
            }
        } else {
            // Если очередь непустая, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestTwo(GeneralizedQueueAsArray queueToTest) throws Exception {

        // Тест 2
        // Размер очереди
        int queueSize = queueToTest.size();
        // Размер массива
        int arraySize = queueToTest.arraySize();
        // Если очередь непустая (size() > 0)
        if (queueSize > 0) {
            // Длина массива "а" больше либо равна 1,
            // и размер очереди находится в пределах
            // от 1/4 до 1 размера массива "a",
            // и элементы от нулевого до (size() - 1) не равны null,
            if ( arraySize >= 1 && queueSize > arraySize / 4
                    && queueSize <= arraySize ) {
                for (Object arg : queueToTest) {
                    if (arg == null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean invariantTestThree(GeneralizedQueueAsArray queueToTest) throws Exception {

        // Тест 3
        // Счетчик ненулевых элементов в очереди при прямом проходе
        int elementCountDirect = 0;

        Iterator<GeneralizedQueueAsArray<String>> iterator = queueToTest.iterator();

        while(iterator.hasNext()) {
            iterator.next();
            elementCountDirect++;
        }

        if (elementCountDirect != queueToTest.size()) {
            return false;
        }

        return true;
    }


    @Test
    public void insertTest() throws Exception {

        // Очереди для тестов
        QueueAsArrayForTests originalQueues = new QueueAsArrayForTests(1);


        // Тест на успешное добавление элементов в пустую очередь
        GeneralizedQueueAsArray<String> emptyQueue0 = new GeneralizedQueueAsArray<>();

        // Аргументы для добавления в очередь
        String[] queueArgs0 = {"one", "two", "three", "four", "five"};

        // Добавление аргументов в пустую очередь
        for (int i = 0; i < queueArgs0.length; i++) {
            emptyQueue0.insert(queueArgs0[i]);
        }

        // Проверочная очередь
        GeneralizedQueueAsArray<String> queueResultExpected0 = originalQueues.getQueue(0);

        assertTrue(queueResultExpected0.equals(emptyQueue0) && invariantTest(emptyQueue0));
    }

    @Test
    public void deleteTest() throws Exception {

        // Очереди для тестов
        QueueAsArrayForTests originalQueues = new QueueAsArrayForTests(5);


        //Тест на успешное удаление элемента из начала очереди
        GeneralizedQueueAsArray<String> originalQueue0 = originalQueues.getQueue(0);

        // Удаление элемента из начала очереди
        originalQueue0.delete(originalQueue0.size());

        // Формирование проверочной очереди
        GeneralizedQueueAsArray<String> queueResultExpected0 = new GeneralizedQueueAsArray<>();
        String[] queueArgs0 = {"two", "three", "four", "five"};
        for (String i : queueArgs0) {
            queueResultExpected0.insert(i);
        }

        assertTrue(queueResultExpected0.equals(originalQueue0) && invariantTest(originalQueue0));


        // Тест на успешное удаление элемента из конца очереди
        GeneralizedQueueAsArray<String> originalQueue1 = originalQueues.getQueue(1);

        // Удаление элемента из конца очереди
        originalQueue1.delete(1);

        // Формирование проверочной очереди
        GeneralizedQueueAsArray<String> queueResultExpected1 = new GeneralizedQueueAsArray<>();
        String[] queueArgs1 = {"one", "two", "three", "four", };
        for (String i : queueArgs1) {
            queueResultExpected1.insert(i);
        }

        assertTrue(queueResultExpected1.equals(originalQueue1) && invariantTest(originalQueue1));


        // Тест на успешное удаление элемента из середины очереди
        GeneralizedQueueAsArray<String> originalQueue2 = originalQueues.getQueue(2);

        // Удаление элемента из конца очереди
        originalQueue2.delete(3);

        // Формирование проверочной очереди
        GeneralizedQueueAsArray<String> queueResultExpected2 = new GeneralizedQueueAsArray<>();
        String[] queueArgs2 = {"one", "two", "four", "five", };
        for (String i : queueArgs2) {
            queueResultExpected2.insert(i);
        }

        assertTrue(queueResultExpected2.equals(originalQueue2) && invariantTest(originalQueue2));


        // Тест на перехват исключения при удалении из пустого списка
        GeneralizedQueueAsArray<String> emptyQueue0 = new GeneralizedQueueAsArray<>();

        try {
            emptyQueue0.delete(1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при удалении нулевого элемента
        GeneralizedQueueAsArray<String> originalQueue3 = originalQueues.getQueue(3);

        try {
            originalQueue3.delete(0);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при удалении элемента
        // с номером больше размера очереди
        GeneralizedQueueAsArray<String> originalQueue4 = originalQueues.getQueue(4);

        try {
            originalQueue4.delete(originalQueue4.size() + 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }
}