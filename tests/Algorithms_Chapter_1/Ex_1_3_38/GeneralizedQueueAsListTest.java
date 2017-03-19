package Algorithms_Chapter_1.Ex_1_3_38;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by vl on 11.01.17.
 */
public class GeneralizedQueueAsListTest {

    private boolean invariantTestOne(GeneralizedQueueAsList queueToTest) throws Exception {

        // Тест 1

        // Первый элемент очереди
        GeneralizedQueueAsList.DoubleNode first = queueToTest.getFirst();

        // Последний элемент очереди
        GeneralizedQueueAsList.DoubleNode last = queueToTest.getLast();

        // Размер очереди
        int size = queueToTest.size();

        // Если первый и последний элемент ссылаются
        // на один объект, то
        if (first == last) {

            if (first == null) {

                // либо они оба null, и N == 0,
                return size == 0;
            } else {
                // либо оба не null, и N == 1.
                return size == 1;
            }
        } else {

            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestTwo(GeneralizedQueueAsList queueToTest) throws Exception {

        // Тест 2

        // Первый элемент очереди
        GeneralizedQueueAsList.DoubleNode first = queueToTest.getFirst();

        // Последний элемент очереди
        GeneralizedQueueAsList.DoubleNode last = queueToTest.getLast();

        // Размер очереди
        int size = queueToTest.size();

        // Начальное значение временного элемента для прохода по очереди
        GeneralizedQueueAsList.DoubleNode temp = first;

        // Счетчик количества элементов при прямом проходе
        int nodeCountDirect = 0;

        // Счетчик количества элементов при обратном проходе
        int nodeCountReverse = 0;

        // Если первый и последний элемент ссылаются
        // на разные объекты, то, пройдясь от первого
        // к последнему по прямым ссылкам, насчитаем N элементов;
        // пройдясь от последнего к первому по обратным ссылкам,
        // получим то же число.
        if (first != last) {

            while (temp != null) {
                nodeCountDirect++;
                temp = temp.getNext();
            }

            temp = last;

            while (temp != null) {
                nodeCountReverse++;
                temp = temp.getPrevious();
            }

            return nodeCountDirect == size && nodeCountReverse == size;
        } else {

            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestThree(GeneralizedQueueAsList listToTest) throws Exception {

        // Тест 3

        // Первый элемент очереди
        GeneralizedQueueAsList.DoubleNode first = listToTest.getFirst();

        // Последний элемент очереди
        GeneralizedQueueAsList.DoubleNode last = listToTest.getLast();

        // Если очередь непустая, то у первого элемента не должно быть
        // предыдущего элемента, а у последнего - последующего
        if ( !listToTest.isEmpty() ) {

            return first.getPrevious() == null && last.getNext() == null;
        } else {

            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestFour(GeneralizedQueueAsList listToTest) throws Exception {

        // Тест 4

        // Первый элемент очереди
        GeneralizedQueueAsList.DoubleNode first = listToTest.getFirst();

        // Последний элемент очереди
        GeneralizedQueueAsList.DoubleNode last = listToTest.getLast();

        // Начальное значение элемента для прохода по очереди
        GeneralizedQueueAsList.DoubleNode current = first;

        // Если очередь пустая, начальных условий для теста нет,
        // и нет смысла возвращать false
        if (first == null && last == null) return true;

        // Проход по прямым ссылкам через всю очередь
        while (current.getNext() != null) {

            // Если текущий элемент - не первый и не последний,
            // то его ссылка его последующего элемента на предыдущий,
            // должна быть ссылкой на сам текущий элемент;
            // аналогично с предыдущим элементом.
            if (current != first && current != last) {
                if (current.getNext().getPrevious() != current
                        || current.getPrevious().getNext() != current) {
                    return false;
                }
            }

            current = current.getNext();
        }

        return true;
    }

    private boolean invariantTest(GeneralizedQueueAsList queueToTest) throws Exception {

        // Тест инвариантов для очереди
        return invariantTestOne(queueToTest) && invariantTestTwo(queueToTest)
                && invariantTestThree(queueToTest) && invariantTestFour(queueToTest);
    }

    @Test
    public void insertTest() throws Exception {
        QueueAsListAsArrayForTests originalQueues = new QueueAsListAsArrayForTests(1);

        // Тест на целостность очереди после добавления элемента в пустую очередь
        GeneralizedQueueAsList<String> originalQueue0 = new GeneralizedQueueAsList<>();

        originalQueue0.insert("inserted");
        assertTrue(invariantTest(originalQueue0));


        // Тест на целостность очереди после добавления элемента в очередь с одним элементом
        GeneralizedQueueAsList<String> originalQueue1 = new GeneralizedQueueAsList<>();

        originalQueue1.insert("one");
        originalQueue1.insert("inserted");

        assertTrue(invariantTest(originalQueue1));


        // Тест на успешное добавление элемента в очередь с несколькими элементами
        GeneralizedQueueAsList<String> originalQueue2 = originalQueues.getQueue(0);

        originalQueue2.insert("inserted");

        // Формирование проверочной очереди
        GeneralizedQueueAsList<String> queueResultExpected2 = new GeneralizedQueueAsList<>();
        String[] listArgs2 = {"one", "two", "three", "four", "five", "inserted", };
        for (String i : listArgs2) {
            queueResultExpected2.insert(i);
        }

        assertTrue(originalQueue2.equals(queueResultExpected2) && invariantTest(originalQueue2));
    }

    @Test
    public void deleteTest() throws Exception {
        QueueAsListAsArrayForTests originalQueues = new QueueAsListAsArrayForTests(5);

        // Тест на успешное удаление элемента в середине списка
        GeneralizedQueueAsList<String> originalQueue0 = originalQueues.getQueue(0);

        originalQueue0.delete(3);

        // Формирование проверочной очереди
        GeneralizedQueueAsList<String> queueResultExpected0 = new GeneralizedQueueAsList<>();
        String[] queueArgs0 = {"one", "two", "four", "five", };
        for (String i : queueArgs0) {
            queueResultExpected0.insert(i);
        }

        assertTrue(originalQueue0.equals(queueResultExpected0) && invariantTest(originalQueue0));


        // Тест на успешное удаление элемента в начале очереди
        GeneralizedQueueAsList<String> originalQueue1 = originalQueues.getQueue(1);

        originalQueue1.delete(originalQueue1.size());

        // Формирование проверочной очереди
        GeneralizedQueueAsList<String> queueResultExpected1 = new GeneralizedQueueAsList<>();
        String[] queueArgs1 = {"two", "three", "four", "five", };
        for (String i : queueArgs1) {
            queueResultExpected1.insert(i);
        }

        assertTrue(originalQueue1.equals(queueResultExpected1) && invariantTest(originalQueue1));


        // Тест на успешное удаление элемента в конце очереди
        GeneralizedQueueAsList<String> originalQueue2 = originalQueues.getQueue(2);

        originalQueue2.delete(1);

        // Формирование проверочной очереди
        GeneralizedQueueAsList<String> queueResultExpected2 = new GeneralizedQueueAsList<>();
        String[] queueArgs2 = {"one", "two", "three", "four", };
        for (String i : queueArgs2) {
            queueResultExpected2.insert(i);
        }

        assertTrue(originalQueue2.equals(queueResultExpected2) && invariantTest(originalQueue2));


        // Тест на успешное удаление всех элементов очереди
        GeneralizedQueueAsList<String> originalQueue3 = originalQueues.getQueue(3);

        // Размер оригинальной очереди
        int s = originalQueue3.size();

        // Удаление всех элементов в списке
        for (int i = 1; i <= s; i++) {
            originalQueue3.delete(1);
        }

        // Создание проверочной пустой очереди
        GeneralizedQueueAsList<String> queueResultExpected3 = new GeneralizedQueueAsList<>();

        assertTrue(originalQueue3.equals(queueResultExpected3) && invariantTest(originalQueue3));


        // Тест на перехват исключения при удалении из пустого списка
        GeneralizedQueueAsList<String> originalQueue4 = new GeneralizedQueueAsList<>();

        try {
            originalQueue4.delete(1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при вводе номера
        // последнего добавленного элемента вне пределов очереди
        GeneralizedQueueAsList<String> originalQueue5 = originalQueues.getQueue(4);

        try {
            originalQueue5.delete(0);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        try {
            originalQueue5.delete(6);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

    }

    @Test
    public void dequeueTest() throws Exception {
        QueueAsListAsArrayForTests originalQueues = new QueueAsListAsArrayForTests(1);

        // Тест на успешное удаление элемента в начале очереди
        GeneralizedQueueAsList<String> originalQueue0 = originalQueues.getQueue(0);

        originalQueue0.dequeue();

        // Формирование проверочной очереди
        GeneralizedQueueAsList<String> queueResultExpected0 = new GeneralizedQueueAsList<>();
        String[] queueArgs0 = {"two", "three", "four", "five", };
        for (String i : queueArgs0) {
            queueResultExpected0.insert(i);
        }

        assertTrue(originalQueue0.equals(queueResultExpected0) && invariantTest(originalQueue0));
    }
}