package Ex_1_3_24__1_3_26;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vl on 10.10.16.
 */
public class LinkedListStackTest {
    @Test
    public void removeAfterTest() throws Exception {
        ListsArrayForTests originalLists = new ListsArrayForTests(4);

        // Тест на успешное удаление после элемента в середине списка
        LinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.removeAfter("two");

        LinkedListStack<String> listResultExpected0 = new LinkedListStack<>();
        String[] listArgs0 = {"five", "four", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное удаление после первого элемента списка
        LinkedListStack<String> originalList1 = originalLists.getList(1);

        originalList1.removeAfter("one");

        LinkedListStack<String> listResultExpected1 = new LinkedListStack<>();
        String[] listArgs1 = {"five", "four", "three", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на перехват ошибки при удалении после последнего элемента списка
        LinkedListStack<String> originalList2 = originalLists.getList(2);

        try {
            originalList2.removeAfter("five");
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {}

        // Тест на перехват ошибки при удалении после несуществующиего элемента
        LinkedListStack<String> originalList3 = originalLists.getList(3);

        try {
            originalList3.removeAfter("notExistedElement");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при удалении из пустого списка
        LinkedListStack<String> emptyList0 = new LinkedListStack<>();

        try {
            emptyList0.removeAfter("one");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void insertAfterTest() throws Exception {
        ListsArrayForTests originalLists = new ListsArrayForTests(4);

        // Тест на успешное добавление элемента в середине списка
        LinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.insertAfter("three", "threethree");

        LinkedListStack<String> listResultExpected0 = new LinkedListStack<>();
        String[] listArgs0 = {"five", "four", "threethree", "three", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное добавление элемента после последнего элемента списка
        LinkedListStack<String> originalList1 = originalLists.getList(1);

        originalList1.insertAfter("five", "fivefive");

        LinkedListStack<String> listResultExpected1 = new LinkedListStack<>();
        String[] listArgs1 = {"fivefive", "five", "four", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на успешное добавление элемента после первого элемента списка
        LinkedListStack<String> originalList2 = originalLists.getList(2);

        originalList2.insertAfter("one", "oneone");

        LinkedListStack<String> listResultExpected2 = new LinkedListStack<>();
        String[] listArgs2 = {"five", "four", "three", "two", "oneone", "one", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected2));

        // Тест на перехват ошибки при вставке после несуществующиего элемента
        LinkedListStack<String> originalList3 = originalLists.getList(3);

        try {
            originalList3.insertAfter("notExistedElement", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при вставке в пустой список
        LinkedListStack<String> emptyList0 = new LinkedListStack<>();

        try {
            emptyList0.insertAfter("notExistedElement", "inserted");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void removeTest() throws Exception {
        ListsArrayForTests originalLists = new ListsArrayForTests(6);

        // Тест на успешное удаление элемента в середине списка
        LinkedListStack<String> originalList0 = originalLists.getList(0);

        originalList0.remove(originalList0, "three");

        LinkedListStack<String> listResultExpected0 = new LinkedListStack<>();
        String[] listArgs0 = {"five", "four", "two", "one", };
        for (String i : listArgs0) {
            listResultExpected0.push(i);
        }

        assertTrue(originalList0.equals(listResultExpected0));

        // Тест на успешное удаление элемента в конце списка
        LinkedListStack<String> originalList1 = originalLists.getList(1);

        originalList1.remove(originalList1, "five");

        LinkedListStack<String> listResultExpected1 = new LinkedListStack<>();
        String[] listArgs1 = {"four", "three", "two", "one", };
        for (String i : listArgs1) {
            listResultExpected1.push(i);
        }

        assertTrue(originalList1.equals(listResultExpected1));

        // Тест на успешное удаление элемента в начале списка
        LinkedListStack<String> originalList2 = originalLists.getList(2);

        originalList2.remove(originalList2, "one");

        LinkedListStack<String> listResultExpected2 = new LinkedListStack<>();
        String[] listArgs2 = {"five", "four", "three", "two", };
        for (String i : listArgs2) {
            listResultExpected2.push(i);
        }

        assertTrue(originalList2.equals(listResultExpected2));

        // Тест на перехват ошибки при удалении отсутствующего в списке элемента
        LinkedListStack<String> originalList3 = originalLists.getList(3);

        try {
            originalList3.remove(originalList3, "notExistedElement");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват ошибки при удалении в пустом списке
        LinkedListStack<String> emptyList0 = new LinkedListStack<>();

        try {
            emptyList0.removeAfter("one");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на успешное удаление всех элементов списка
        // (проверка size() на равность нулю)
        LinkedListStack<String> originalList4 = originalLists.getList(4);
        String[] listArgsToRemove0 = {"five", "four", "three", "two", "one", };

        for(String arg : listArgsToRemove0) {
            originalList4.remove(originalList4, arg);
        }

        assertEquals(0, originalList4.size());

        // Тест на успешное удаление всех элементов списка
        // (проверка на отсутствие элементов в спике)
        LinkedListStack<String> originalList5 = originalLists.getList(5);
        LinkedListStack<String> emptyList1 = new LinkedListStack<>();
        String[] listArgsToRemove1 = {"five", "four", "three", "two", "one", };

        for(String arg : listArgsToRemove1) {
            originalList5.remove(originalList5, arg);
        }

        assertTrue(originalList5.equals(emptyList1));
    }
}