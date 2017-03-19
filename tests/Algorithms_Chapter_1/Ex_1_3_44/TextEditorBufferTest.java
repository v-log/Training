package Algorithms_Chapter_1.Ex_1_3_44;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by vl on 14.02.17.
 */
public class TextEditorBufferTest {
    private boolean invariantTest(TextEditorBuffer bufferToTest) throws Exception {
        return invariantTestOne(bufferToTest) && invariantTestTwo(bufferToTest);
    }

    private boolean invariantTestOne(TextEditorBuffer bufferToTest) throws Exception {

        // Тест 1
        // Если буфер пуст, левый и правый стеки пустые,
        // указатель на позиции ноль

        if (bufferToTest.isEmpty()) {
            if (!bufferToTest.getLeftStack().isEmpty() ||
                    !bufferToTest.getRightStack().isEmpty() || bufferToTest.getPointer() != 0) {
                return false;
            }
        } else {
            // Если буфер непустой, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }

        return true;
    }

    private boolean invariantTestTwo(TextEditorBuffer bufferToTest) throws Exception {

        // Тест 2
        // Если в буфере есть элементы,
        // размер буфера равен сумме размеров правого и левого стеков,
        // указатель находится в пределах от нуля (включительно)
        // до размера буфера (включительно),
        // указатель равен размеру левого стека и разности размера
        // буфера и размера правого стека,
        // при прямом проходе количество ненулевых элементов равно размеру буфера.


        int size = bufferToTest.size();
        int leftSize = bufferToTest.getLeftStack().size();
        int rightSize = bufferToTest.getRightStack().size();
        int pointer = bufferToTest.getPointer();
        boolean flag = true;

        if (size > 0) {
            if (size != leftSize + rightSize || pointer < 0 ||
                    pointer > size ||
                    pointer != size - rightSize) {
                flag = false;
            }

            int count = 0;

            for (Iterator iterator = bufferToTest.iterator(); iterator.hasNext();) {
                iterator.next();
                count++;
            }

            if (count != size) {
                flag = false;
            }
        } else {
            // Если буфер непустой, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }

        return flag;
    }

    @Test
    public void insertTest() throws Exception {

        // Тест на добавление элементов в пустой буфер
        // (указатель всегда находится в конце буфера)

        TextEditorBuffer buffer0 = new TextEditorBuffer();

        // Аргументы для добавления в буфер
        char[] bufferArgs = {'a', 'b', 'c', 'd', 'e'};

        // Добавление аргументов в буфер
        for (char arg : bufferArgs) {
            buffer0.insert(arg);
        }

        boolean flag0 = true;

        // Проверка символов в буфере на
        // соответствие добавленным символам
        Iterator iterator0 = buffer0.iterator();

        if (buffer0.size() == bufferArgs.length) {
            for (char arg : bufferArgs) {
                if (!iterator0.next().equals(arg)) {
                    flag0 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag0 && invariantTest(buffer0));
    }

    @Test
    public void deleteTest() throws Exception {

        // Аргументы для буферов
        char[] bufferArgs = {'a', 'b', 'c', 'd', 'e', };

        // Буферы для тестов
        BufferArrayForTests buffers = new BufferArrayForTests(3, bufferArgs);


        // Тест на удаление одного элемента из буфера
        // (указатель находится в конце буфера)
        TextEditorBuffer buffer0 = buffers.getBuffer(0);

        buffer0.delete();

        char[] bufferArgsForCheck0 = {'a', 'b', 'c', 'd', };

        boolean flag0 = true;

        Iterator iterator0 = buffer0.iterator();

        if (buffer0.size() == bufferArgsForCheck0.length) {
            for (char arg : bufferArgsForCheck0) {
                if (!iterator0.next().equals(arg)) {
                    flag0 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag0 && invariantTest(buffer0));


        // Тест на удаление всех элементов из очереди
        // (указатель всегда находится в конце буфера)
        TextEditorBuffer buffer1 = buffers.getBuffer(1);

        int size0 = buffer1.size();

        for (int i = 0; i < size0; i++) {
            buffer1.delete();
        }

        assertTrue(invariantTest(buffer1));


        // Тест на перехват исключения при удалении из пустого буфера
        TextEditorBuffer buffer2 = new TextEditorBuffer();

        try {
            buffer2.delete();
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}


        // Тест на перехват исключения при удалении,
        // когда указатель находится в начале буфера
        TextEditorBuffer buffer3 = buffers.getBuffer(2);

        buffer3.left(5);

        try {
            buffer3.delete();
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    @Test
    public void leftTest() throws Exception {

        // Аргументы для буферов
        char[] bufferArgs = {'a', 'b', 'c', 'd', 'e', };

        // Буферы для тестов
        BufferArrayForTests buffers = new BufferArrayForTests(5, bufferArgs);


        // Тест на перемещение указателя в начало
        // буфера (проверка через добавление туда символа)
        TextEditorBuffer buffer0 = buffers.getBuffer(0);

        buffer0.left(5);

        buffer0.insert('i');

        char[] bufferArgsForCheck0 = {'i', 'a', 'b', 'c', 'd', 'e'};

        boolean flag0 = true;

        Iterator iterator0 = buffer0.iterator();

        if (buffer0.size() == bufferArgsForCheck0.length) {
            for (char arg : bufferArgsForCheck0) {
                if (!iterator0.next().equals(arg)) {
                    flag0 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag0 && buffer0.getPointer() == 1 && invariantTest(buffer0));


        // Тест на перемещение указателя в середину
        // буфера (проверка через добавление туда символа)
        TextEditorBuffer buffer1 = buffers.getBuffer(1);

        buffer1.left(3);

        buffer1.insert('i');

        char[] bufferArgsForCheck1 = {'a', 'b', 'i', 'c', 'd', 'e', };

        boolean flag1 = true;

        Iterator iterator1 = buffer1.iterator();

        if (buffer0.size() == bufferArgsForCheck0.length) {
            for (char arg : bufferArgsForCheck1) {
                if (!iterator1.next().equals(arg)) {
                    flag1 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag1 && buffer1.getPointer() == 3 && invariantTest(buffer1));


        // Тест на перехват исключения при
        // перемещении указателя влево на отрицательное число
        TextEditorBuffer buffer2 = buffers.getBuffer(2);

        try {
            buffer2.left(-1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при
        // перемещении указателя влево на ноль позиций
        TextEditorBuffer buffer3 = buffers.getBuffer(3);

        try {
            buffer3.left(0);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при
        // перемещении указателя влево на число позиций
        // большее, чем текущая позиция указателя
        TextEditorBuffer buffer4 = buffers.getBuffer(4);

        try {
            buffer4.left(6);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void rightTest() throws Exception {

        // Аргументы для буферов
        char[] bufferArgs = {'a', 'b', 'c', 'd', 'e', };

        // Буферы для тестов
        BufferArrayForTests buffers = new BufferArrayForTests(5, bufferArgs);


        // Тест на перемещение указателя из начала
        // буфера в конец (проверка через добавление туда символа)
        TextEditorBuffer buffer0 = buffers.getBuffer(0);

        buffer0.left(5);

        buffer0.right(5);

        buffer0.insert('i');

        char[] bufferArgsForCheck0 = {'a', 'b', 'c', 'd', 'e', 'i'};

        boolean flag0 = true;

        Iterator iterator0 = buffer0.iterator();

        if (buffer0.size() == bufferArgsForCheck0.length) {
            for (char arg : bufferArgsForCheck0) {
                if (!iterator0.next().equals(arg)) {
                    flag0 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag0 && buffer0.getPointer() == 6 && invariantTest(buffer0));


        // Тест на перемещение указателя из начала
        // буфера в середину (проверка через добавление туда символа)
        TextEditorBuffer buffer1 = buffers.getBuffer(1);

        buffer1.left(5);

        buffer1.right(5);

        buffer1.insert('i');

        char[] bufferArgsForCheck1 = {'a', 'b', 'c', 'd', 'e', 'i'};

        boolean flag1 = true;

        Iterator iterator1 = buffer1.iterator();

        if (buffer1.size() == bufferArgsForCheck1.length) {
            for (char arg : bufferArgsForCheck1) {
                if (!iterator1.next().equals(arg)) {
                    flag1 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag1 && buffer0.getPointer() == 6 && invariantTest(buffer1));


        // Тест на перехват исключения при
        // перемещении указателя вправо на отрицательное число
        TextEditorBuffer buffer2 = buffers.getBuffer(2);

        try {
            buffer2.right(-1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при
        // перемещении указателя вправо на ноль позиций
        TextEditorBuffer buffer3 = buffers.getBuffer(3);

        try {
            buffer3.right(0);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при
        // перемещении указателя вправо на число позиций
        // большее, чем количество символов справа от указателя
        TextEditorBuffer buffer4 = buffers.getBuffer(4);

        buffer4.left(5);

        try {
            buffer4.right(6);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void returnBufferTest() throws Exception {

        // Аргументы для буферов
        char[] bufferArgs = {'a', 'b', 'c', 'd', 'e', };

        // Буферы для тестов
        BufferArrayForTests buffers = new BufferArrayForTests(1, bufferArgs);


        // Тест на возврат верного массива элементов буфера
        TextEditorBuffer buffer0 = buffers.getBuffer(0);

        String returnedBuffer0 = buffer0.returnBuffer();

        char[] returnedStringAsArray0 = returnedBuffer0.toCharArray();

        boolean flag0 = true;

        if (returnedStringAsArray0.length == bufferArgs.length) {
            for (int i = 0; i < returnedStringAsArray0.length; i++) {
                if (returnedStringAsArray0[i] != (bufferArgs[i])) {
                    flag0 = false;
                }
            }
        } else {
            throw new IllegalArgumentException("Unequal array and buffer sizes");
        }

        assertTrue(flag0 && invariantTest(buffer0));


        // Тест на перехват исключения при возврате пустого буфера
        TextEditorBuffer buffer1 = new TextEditorBuffer();

        try {
            buffer1.returnBuffer();
            fail("Should throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {}
    }
}