package Ex_1_3_44;

import java.util.Iterator;

/**
 * Created by vl on 11.02.17.
 */

/*
* Text editor buffer. Develop a data type for a buffer
in a text editor that implements the following API:

API for a text buffer
public class Buffer {
    Buffer();               // create an empty buffer
    void insert(char c);    // insert c at the cursor position
    char delete();          // delete and return the character at the cursor
    void left(int k);       // move the cursor k positions to the left
    void right(int k);      // move the cursor k positions to the right
    int size();             // number of characters in the buffer
};
Hint: Use two stacks.
*/

public class TextEditorBuffer implements Iterable {

    // Левый стек символов буфера
    private StackArrayForChar leftStack;
    // Правый стек символов буфера
    private StackArrayForChar rightStack;

    TextEditorBuffer() {
        leftStack = new StackArrayForChar();
        rightStack = new StackArrayForChar();
    }

    public StackArrayForChar getLeftStack() {

        // Для тестов
        return leftStack;
    }

    public StackArrayForChar getRightStack() {

        // Для тестов
        return rightStack;
    }

    public int getPointer() {

        // Для тестов
        return leftStack.size();
    }

    public int size() {
        return leftStack.size() + rightStack.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(char c) {

        // Добавление символа 'с' на позицию указателя в буфер
        leftStack.push(c);
    }

    public char delete() {

        // Удаление и возврат из буфера символа на позиции указателя

        // Если буфер пустой, бросить исключение
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Buffer is empty");
        }

        // Если указатель находится в начале буфера, бросить исключение
        if (leftStack.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Pointer is at the beginning - nothing to delete");
        }

        return leftStack.pop();
    }

    public void left(int k) {

        // Переместить указатель на k позиций влево

        // Размер левого стека буфера
        int arraySize = leftStack.size();

        // Если k меньше одного или больше значения
        // указателя, бросить исключение
        if (k < 1 || k > arraySize) {
            throw new IllegalArgumentException("k should be between 1 and " + arraySize + " (inclusive");
        }

        for (int i = 0; i < k; i++) {
            rightStack.push(leftStack.pop());
        }
    }

    public void right(int k) {

        // Переместить указатель на k позиций вправо

        // Размер правого стека буфера
        int arraySize = rightStack.size();

        // Если k меньше одного или больше количества
        // элементов правого стека, бросить исключение
        if (k < 1 || k > arraySize) {
            throw new IllegalArgumentException("k should be between 1 and " + arraySize + " (inclusive");
        }

        for (int i = 0; i < k; i++) {
            leftStack.push(rightStack.pop());
        }
    }

    public String returnBuffer() {

        // Вернуть массив со всеми символами из буфера

        // Если буфер пуст, бросить искючение
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Buffer is empty");
        }

        return String.valueOf(leftStack.getStackAsArray()) +
                (new StringBuilder(String.valueOf(rightStack.getStackAsArray())).reverse());
    }

    public Iterator iterator() {
        return new TextBufferIterator();
    }

    private class TextBufferIterator implements Iterator {
        String returnedBuffer = returnBuffer();
        char[] a = returnedBuffer.toCharArray();
        private int currentIndex = 0;
        private Character current = a[currentIndex];
        private Character realCurrent = current;

        public boolean hasNext() {
            return realCurrent != null;
        }

        public Character next() {
            current = a[currentIndex++];
            if (currentIndex < a.length) {
                realCurrent = a[currentIndex];
            } else {
                realCurrent = null;
            }

            return current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        TextEditorBuffer bufferToCompareWith = (TextEditorBuffer) x;

        if(this.size() != bufferToCompareWith.size()) return false;

        Iterator iterator2 = bufferToCompareWith.iterator();

        for(Iterator iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        TextEditorBuffer buffer0 = new TextEditorBuffer();

        buffer0.insert('a');
        buffer0.insert('b');
        buffer0.insert('c');
        buffer0.insert('d');
        buffer0.insert('k');
        buffer0.insert('l');
        buffer0.left(3);
        buffer0.insert('z');

        String a0 = buffer0.returnBuffer();

        for(char arg : a0.toCharArray()) {
            System.out.println(arg);
        }

        System.out.println();

        Iterator iterator = buffer0.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}