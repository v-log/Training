package Ex_1_3_31;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 24.10.16.
 */
public class DoubleLinkedListStackTest {
    @Test
    public void insertAtBeginningTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное добавление элемента в начало списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.insertAtBeginning("inserted");

        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"five", "four", "three", "two", "one", "inserted", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected1));

        // Тест на успешное добавление в пустой список
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        emptyList0.insertAtBeginning("inserted");

        DoubleLinkedListStack<String> listResultExpected2 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"inserted", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(emptyList0.equals(listResultExpected2));
    }

    @Test
    public void insertAtEndTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное добавление элемента в конец списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.insertAtEnd("inserted");

        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"inserted", "five", "four", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное добавление в пустой список
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        emptyList0.insertAtEnd("inserted");

        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"inserted", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(emptyList0.equals(listResultExpected1));
    }

    @Test
    public void removeFromBeginningTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное удаление элемента из начала списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.removeFromBeginning();

        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "three", "two", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на перехват исключения при удалении из пустого списка
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        try {
            emptyList0.removeFromBeginning();
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void removeFromEndTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(1);

        // Тест на успешное удаление элемента из начала списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.removeFromEnd();

        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"four", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на перехват исключения при удалении из пустого списка
        DoubleLinkedListStack<String> emptyList0 = new DoubleLinkedListStack<>();

        try {
            emptyList0.removeFromBeginning();
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void removeTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(6);

        // Тест на успешное удаление элемента в середине списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(0);

        originalList1.remove(originalList1, "three");

        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"five", "four", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на успешное удаление элемента в конце списка
        DoubleLinkedListStack<String> originalList2 = originalLists.getList(1);

        originalList2.remove(originalList2, "five");

        DoubleLinkedListStack<String> listResultExpected2 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"four", "three", "two", "one", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected2));

        // Тест на успешное удаление элемента в начале списка
        DoubleLinkedListStack<String> originalList3 = originalLists.getList(2);

        originalList3.remove(originalList3, "one");

        DoubleLinkedListStack<String> listResultExpected3 = new DoubleLinkedListStack<>();
        String[] listArgs3 = {"five", "four", "three", "two", };
        for (String i : listArgs3) {
            listResultExpected3.push(i);
        }

        assertTrue(originalList3.equals(listResultExpected3));

        // Тест на перехват ошибки при удалении отсутствующего в списке элемента
        DoubleLinkedListStack<String> originalList4 = originalLists.getList(3);

        try {
            originalList4.remove(originalList4, "notExistedElement");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при удалении в пустом списке
        DoubleLinkedListStack<String> originalList5 = new DoubleLinkedListStack<>();

        try {
            originalList5.removeAfter("one");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на успешное удаление всех элементов списка
        // (проверка size() на равность нулю)
        DoubleLinkedListStack<String> originalList6 = originalLists.getList(4);
        String[] listArgsToRemove1 = {"five", "four", "three", "two", "one", };

        for(String arg : listArgsToRemove1) {
            originalList6.remove(originalList6, arg);
        }

        assertEquals(0, originalList6.size());

        // Тест на успешное удаление всех элементов списка
        // (проверка на отсутствие элементов в списке)
        DoubleLinkedListStack<String> originalList7 = originalLists.getList(5);
        DoubleLinkedListStack<String> originalList8 = new DoubleLinkedListStack<>();
        String[] listArgsToRemove2 = {"five", "four", "three", "two", "one", };

        for(String arg : listArgsToRemove2) {
            originalList7.remove(originalList7, arg);
        }

        assertTrue(originalList7.equals(originalList8));
    }

    @Test
    public void insertAfterTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(4);

        // Тест на успешное добавление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.insertAfter("three", "threethree");

        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"five", "four", "threethree", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное добавление элемента после последнего элемента списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        originalList1.insertAfter("five", "fivefive");

        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"fivefive", "five", "four", "three", "two", "one", };
        for (String i : listArgs2) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на успешное добавление элемента после первого элемента списка
        DoubleLinkedListStack<String> originalList2 = originalLists.getList(2);

        originalList2.insertAfter("one", "oneone");

        DoubleLinkedListStack<String> listResultExpected3 = new DoubleLinkedListStack<>();
        String[] listArgs3 = {"five", "four", "three", "two", "oneone", "one", };
        for (String i : listArgs3) {
            listResultExpected3.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected3));

        // Тест на перехват ошибки при вставке после несуществующиего элемента
        DoubleLinkedListStack<String> originalList3 = originalLists.getList(3);

        try {
            originalList3.insertAfter("notExistedElement", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при вставке в пустой список
        DoubleLinkedListStack<String> originalList4 = new DoubleLinkedListStack<>();

        try {
            originalList4.insertAfter("one", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void insertBeforeTest() throws Exception {
        DoubleListsArrayForTests originalLists = new DoubleListsArrayForTests(4);

        // Тест на успешное добавление элемента в середине списка
        DoubleLinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.insertBefore("three", "threethree");

        DoubleLinkedListStack<String> listResultExpected0 = new DoubleLinkedListStack<>();
        String[] listArgs0 = {"five", "four", "three", "threethree", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное добавление элемента перед последним элементом списка
        DoubleLinkedListStack<String> originalList1 = originalLists.getList(1);

        originalList1.insertBefore("five", "fivefive");

        DoubleLinkedListStack<String> listResultExpected1 = new DoubleLinkedListStack<>();
        String[] listArgs1 = {"five","fivefive", "four", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на успешное добавление элемента перед первым элементом списка
        DoubleLinkedListStack<String> originalList2 = originalLists.getList(2);

        originalList2.insertBefore("one", "oneone");

        DoubleLinkedListStack<String> listResultExpected2 = new DoubleLinkedListStack<>();
        String[] listArgs2 = {"five", "four", "three", "two", "one", "oneone", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected2));

        // Тест на перехват ошибки при вставке перед несуществующим элементом
        DoubleLinkedListStack<String> originalList3 = originalLists.getList(3);

        try {
            originalList3.insertAfter("notExistedElement", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при вставке в пустой список
        DoubleLinkedListStack<String> originalList4 = new DoubleLinkedListStack<>();

        try {
            originalList4.insertAfter("one", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }
}