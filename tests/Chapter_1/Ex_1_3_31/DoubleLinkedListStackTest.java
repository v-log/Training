package Chapter_1.Ex_1_3_31;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vl on 24.10.16.
 */
public class DoubleLinkedListStackTest {
    private boolean invariantTestOne(DoubleLinkedListStack listToTest) throws Exception{

        // Тест 1
        // Первый элемент списка
        DoubleLinkedListStack.DoubleNode first = listToTest.getFirst();
        // Последний элемент списка
        DoubleLinkedListStack.DoubleNode last = listToTest.getLast();
        int size = listToTest.size();

        // Если первый и последний элемент ссылаются
        // на один объект, то
        if (first == last) {
            if (first == null) {
                // либо они оба null, и N == 0,
                if (size == 0) {
                    return true;
                }
            } else {
                // либо оба не null, и N == 1.
                if (size == 1) {
                    return true;
                }
            }
        } else {
            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }

        return false;
    }

    private boolean invariantTestTwo(DoubleLinkedListStack listToTest) throws Exception {

        // Тест 2
        // Первый элемент списка
        DoubleLinkedListStack.DoubleNode first = listToTest.getFirst();
        // Последний элемент списка
        DoubleLinkedListStack.DoubleNode last = listToTest.getLast();
        int size = listToTest.size();
        // Начальное значение временного элемента для прохода по списку
        DoubleLinkedListStack.DoubleNode temp = first;
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

            if (nodeCountDirect == size && nodeCountReverse == size) return true;
        } else {
            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }

        return false;
    }

    private boolean invariantTestThree(DoubleLinkedListStack listToTest) throws Exception {

        // Тест 3
        // Первый элемент списка
        DoubleLinkedListStack.DoubleNode first = listToTest.getFirst();
        // Последний элемент списка
        DoubleLinkedListStack.DoubleNode last = listToTest.getLast();

        // Если список непустой, то у первого элемента не должно быть
        // предыдущего элемента, а у последнего - последующего
        if ( !listToTest.isEmpty() ) {
            if (first.getPrevious() == null && last.getNext() == null) {
                return true;
            }
        } else {
            // Иначе начальных условий для теста нет, и нет смысла возвращать false
            return true;
        }

        return false;
    }

    private boolean invariantTestFour(DoubleLinkedListStack listToTest) throws Exception {

        // Тест 4
        // Первый элемент списка
        DoubleLinkedListStack.DoubleNode first = listToTest.getFirst();
        // Последний элемент списка
        DoubleLinkedListStack.DoubleNode last = listToTest.getLast();
        // Начальное значение элемента для прохода по списку
        DoubleLinkedListStack.DoubleNode current = first;

        // Если список пустой, начальных условий для теста нет,
        // и нет смысла возвращать false
        if(first == null && last == null) return true;

        // Проход по прямым ссылкам через весь список
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

    private boolean invariantTest(DoubleLinkedListStack listToTest) throws Exception {

        // Тест инвариантов для списка
        return invariantTestOne(listToTest) && invariantTestTwo(listToTest)
                && invariantTestThree(listToTest) && invariantTestFour(listToTest);
    }

    @Test
    public void insertAtBeginningTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное добавление элемента в начало списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение вставляемого Node
        DoubleLinkedListStack<String> listWithNodeToInsert0 = new DoubleLinkedListStack<>();
        listWithNodeToInsert0.push("inserted");
        Iterator<DoubleLinkedListStack<String>.DoubleNode> iterator0 = listWithNodeToInsert0.iterator();
        DoubleLinkedListStack.DoubleNode nodeToInsert0 = iterator0.next();

        // Вставка Node
        originalList0.insertAtBeginning(nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "three", "two", "one", "inserted", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на успешное добавление в пустой список
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        // Вставка Node
        emptyList0.insertAtBeginning(nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected2 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"inserted", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(emptyList0.equals(listResultExpected2) && invariantTest(emptyList0));
    }

    @Test
    public void insertAtEndTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное добавление элемента в конец списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение вставляемого Node
        DoubleLinkedListStack<String> listWithNodeToInsert0 = new DoubleLinkedListStack<>();
        listWithNodeToInsert0.push("inserted");
        Iterator<DoubleLinkedListStack<String>.DoubleNode> iterator0 = listWithNodeToInsert0.iterator();
        DoubleLinkedListStack.DoubleNode nodeToInsert0 = iterator0.next();

        // Вставка Node
        originalList0.insertAtEnd(nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"inserted", "five", "four", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на успешное добавление в пустой список
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        // Вставка Node
        emptyList0.insertAtEnd(nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"inserted", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(emptyList0.equals(listResultExpected1) && invariantTest(emptyList0));
    }

    @Test
    public void removeFromBeginningTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(2);

        // Тест на успешное удаление элемента из начала списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Удаление первого элемента списка
        originalList0.removeFromBeginning();

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "three", "two", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на перехват исключения при удалении из пустого списка
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        try {
            emptyList0.removeFromBeginning();
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {}


        // Тест на успешное удаление всех элементов списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        // Удаление всех элементов списка
        int size = originalList1.size();
        for (int i = 0; i < size; i++) {
            originalList1.removeFromBeginning();
        }

        assertTrue(originalList1.isEmpty() && invariantTest(originalList1));
    }

    @Test
    public void removeFromEndTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(2);

        // Тест на успешное удаление элемента из начала списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Удаление последнего элемента списка
        originalList0.removeFromEnd();

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"four", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на перехват исключения при удалении из пустого списка
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        try {
            emptyList0.removeFromEnd();
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {}


        // Тест на успешное удаление всех элементов списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        int size = originalList1.size();
        for (int i = 0; i < size; i++) {
            originalList1.removeFromEnd();
        }

        assertTrue(originalList1.isEmpty() && invariantTest(originalList1));
    }

    @Test
    public void removeTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(4);

        // Тест на успешное удаление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение удаляемого Node
        DoubleLinkedListStack.DoubleNode nodeToRemove0 = originalList0.getFirst();
        for (int i = 0; i < 2; i++) {
            nodeToRemove0 = nodeToRemove0.getNext();
        }

        // Удаление элемента в середине списка
        originalList0.remove(nodeToRemove0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на успешное удаление элемента в конце списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        // Получение удаляемого Node
        DoubleLinkedListStack.DoubleNode nodeToRemove1 = originalList1.getFirst();
        for (int i = 0; i < 4; i++) {
            nodeToRemove1 = nodeToRemove1.getNext();
        }

        // Удаление элемента в конце списка
        originalList1.remove(nodeToRemove1);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"four", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1) && invariantTest(originalList1));


        // Тест на успешное удаление элемента в начале списка
        DoubleLinkedListStack<String> originalList2 = originalLists.getList(2);

        // Удаляемый Node
        DoubleLinkedListStack.DoubleNode nodeToRemove2 = originalList2.getFirst();

        // Удаление элемента в начале списка
        originalList2.remove(nodeToRemove2);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected2 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"five", "four", "three", "two", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected2) && invariantTest(originalList2));


        // Тест на успешное удаление всех элементов списка
        DoubleLinkedListStack<String> originalList3 = originalLists.getList(3);

        // Удаление всех элементов в списке
        for(DoubleLinkedListStack.DoubleNode arg : originalList3) {
            originalList3.remove(arg);
        }

        assertTrue(originalList3.isEmpty() && invariantTest(originalList3));
    }

    @Test
    public void removeAfterTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(2);

        // Тест на успешное удаление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение удаляемого Node
        DoubleLinkedListStack.DoubleNode nodeToRemoveAfter0 = originalList0.getFirst();
        for (int i = 0; i < 2; i++) {
            nodeToRemoveAfter0 = nodeToRemoveAfter0.getNext();
        }

        // Удаление элемента в середине списка
        originalList0.removeAfter(nodeToRemoveAfter0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на перехват исключения при удалении
        // после последнего элемента в списке
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        // Последний элемент списка
        DoubleLinkedListStack.DoubleNode nodeToRemoveAfter1 = originalList1.getLast();

        try {
            originalList1.removeAfter(nodeToRemoveAfter1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void insertAfterTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(2);

        // Тест на успешное добавление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение Node, после которого вставляется элемент
        DoubleLinkedListStack.DoubleNode nodeToInsertAfter0 = originalList0.getFirst();
        for (int i = 0; i < 2; i++) {
            nodeToInsertAfter0 = nodeToInsertAfter0.getNext();
        }

        // Получение вставляемого Node
        DoubleLinkedListStack<String> listWithNodeToInsert0 = new DoubleLinkedListStack<>();
        listWithNodeToInsert0.push("inserted");
        Iterator<DoubleLinkedListStack<String>.DoubleNode> iterator0 = listWithNodeToInsert0.iterator();
        DoubleLinkedListStack.DoubleNode nodeToInsert0 = iterator0.next();

        // Вставка элемента в середину списка
        originalList0.insertAfter(nodeToInsertAfter0, nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "inserted", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на успешное добавление элемента после последнего элемента списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        // Получение Node, после которого вставляется элемент
        DoubleLinkedListStack.DoubleNode nodeToInsertAfter1 = originalList1.getFirst();
        for (int i = 0; i < 4; i++) {
            nodeToInsertAfter1 = nodeToInsertAfter1.getNext();
        }

        // Вставка элемента после последнего элемента списка
        originalList1.insertAfter(nodeToInsertAfter1, nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"inserted", "five", "four", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1) && invariantTest(originalList1));
    }

    @Test
    public void insertBeforeTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(4);

        // Тест на успешное добавление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        // Получение Node, перед которым вставляется элемент
        DoubleLinkedListStack.DoubleNode nodeToInsertBefore0 = originalList0.getFirst();
        for (int i = 0; i < 2; i++) {
            nodeToInsertBefore0 = nodeToInsertBefore0.getNext();
        }

        // Получение вставляемого Node
        DoubleLinkedListStack<String> listWithNodeToInsert0 = new DoubleLinkedListStack<>();
        listWithNodeToInsert0.push("inserted");
        Iterator<DoubleLinkedListStack<String>.DoubleNode> iterator0 = listWithNodeToInsert0.iterator();
        DoubleLinkedListStack.DoubleNode nodeToInsert0 = iterator0.next();

        // Вставка элемента в середину списка
        originalList0.insertBefore(nodeToInsertBefore0, nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "three", "inserted", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0) && invariantTest(originalList0));


        // Тест на успешное добавление элемента перед первым элементом списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        // Node, перед которым вставляется элемент
        DoubleLinkedListStack.DoubleNode nodeToInsertBefore1 = originalList1.getFirst();

        // Вставка элемента перед первым элементом списка
        originalList1.insertBefore(nodeToInsertBefore1, nodeToInsert0);

        // Формирование проверочного списка
        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"five", "four", "three", "two", "one", "inserted", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1) && invariantTest(originalList1));
    }
}