package Chapter_1.Ex_1_3_31;

import java.util.Iterator;

/**
 * Created by vl on 24.10.16.
 */

/* 1.3.31 Implement a nested class DoubleNode for building doubly-linked lists,
where each node contains a reference to the item preceding it and the
item following it in the list (null if there is no such item).

Then implement static methods for the following tasks:
insert at the beginning, insert at the end, remove from the beginning,
remove from the end, insert before a given node,
insert after a given node, and remove a given node. */

public class DoubleLinkedListStack<Item> implements Iterable<DoubleLinkedListStack<Item>.DoubleNode> {

    private DoubleNode first; // top of stack (most recently added node)
    private DoubleNode last;
    private int N; // number of items

    public class DoubleNode {

        // nested class to define nodes
        private Item item;
        private DoubleNode previous;
        private DoubleNode next;

        public Item getItem() {
            return item;
        }

        public DoubleNode getNext() {
            return next;
        }

        public DoubleNode getPrevious() {
            return previous;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public DoubleNode getFirst() {
        return first;
    }

    public DoubleNode getLast() {
        return last;
    }

    public void push(Item item) {

        // Add item to top of stack.
        DoubleNode oldfirst = first;
        first = new DoubleNode();
        first.item = item;
        first.next = oldfirst;

        if (oldfirst != null) {
            oldfirst.previous = first;
        }

        if (first.next == null) {
            last = first;
        }

        N++;
    }

    public Item pop() {

        // Remove item from top of stack.
        Item item = first.item;

        // Если в списке больше одного элемента, обнулить ссылку у второго
        // элемента на первый и приравнять первый ко второму.
        if (first.next != null) {
            first.next.previous = null;
            first = first.next;
        } else {
            // Иначе (если в списке один элемент) обнулить и первый и, что то же самое,
            // последний.
            first = null;
            last = null;
        }

        N--;

        return item;
    }

    public void insertAtBeginning(DoubleNode newFirst) throws IllegalArgumentException {

        // Вставка элемента в начало списка
        // Если вставляемый элемент - нулевой, бросить исключение
        if (newFirst == null) throw new IllegalArgumentException("New element is null");

        // Если список пустой, вставляемый элемент -
        // первый и последний
        if (first == null) {
            first = newFirst;
            last = newFirst;
            newFirst.next = null;
            newFirst.previous = null;
        } else {
            // Иначе, если в списке один элемент
            if (first == last) {
                first = newFirst;
                first.next = last;
            } else {
                // Иначе, если в списке несколько элементов
                newFirst.next = first;
                newFirst.previous = null;
                first.previous = newFirst;
                first = newFirst;
            }
        }

        N++;
    }

    public void insertAtEnd(DoubleNode newLast) throws IllegalArgumentException {

        // Вставка элемента в конец списка
        // Если вставляемый элемент - нулевой, бросить исключение
        if (newLast == null) throw new IllegalArgumentException("New element is null");

        // Добавление нового элемента в конец списка
        // Если в списке есть элементы, новый элемент
        // вставляется после last
        if (last != null) {
            last.next = newLast;
            newLast.previous = last;
            last = newLast;
        } else {
            // Иначе вставляемый элемент - первый и последний
            first = newLast;
            last = newLast;
            newLast.next = null;
            newLast.previous = null;
        }

        N++;
    }

    public void removeFromBeginning() throws IndexOutOfBoundsException {

        // Удаление первого элемента из списка
        // Если в списке нет элементов, бросить исключение
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        pop();
    }

    public void removeFromEnd() throws IndexOutOfBoundsException {

        // Удаление последнего элемента
        // Если в списке нет элементов, бросить исключение
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        // Если последний и первый - не один и тот же элемент,
        // обнуляем ссылку у предпоследнего элемента на последний
        // и делаем предпоследний элемент последним,
        if (last != first) {
            last.previous.next = null;
            last = last.previous;
        } else {
            // Иначе (если в списке остался только один элемент)
            // обнуляем ссылки на этот элемент.
            last = null;
            first = null;
        }

        N--;
    }

    public void removeAfter(DoubleNode nodeToRemoveAfter) throws IllegalArgumentException {

        // Удаление после переданного элемента
        // Если переданный или последующий элемент - нулевые,
        // бросить исключение
        if ( nodeToRemoveAfter == null || nodeToRemoveAfter.next == null ) {
            throw new IllegalArgumentException("This or the next element is null");
        }

        // Удаляемый элемент
        DoubleNode nodeToRemove = nodeToRemoveAfter.next;
        remove(nodeToRemove);
    }

    public void insertAfter(DoubleNode nodeToInsertAfter, DoubleNode nodeToInsert)
            throws IllegalArgumentException {

        // Вставка переданного nodeToInsert после переданного nodeToInsertAfter
        // Если переданные элементы - ненулевые,
        // вставить nodeToInsert после nodeToInsertAfter
        if ( nodeToInsertAfter != null && nodeToInsert != null ) {

            // Первоначальный элемент после nodeToInsertAfter,
            // вместо которого вставляется nodeToInsert
            DoubleNode oldNext = nodeToInsertAfter.next;

            // Если nodeToInsertAfter - не последний элемент списка,
            // вставить nodeToInsert между двумя элементами,
            if (oldNext != null) {
                oldNext.previous = nodeToInsert;
                nodeToInsert.next = oldNext;
            } else {
                // иначе вставить после последнего элемента
                nodeToInsert.next = null;
                last = nodeToInsert;
            }

            nodeToInsertAfter.next = nodeToInsert;
            nodeToInsert.previous = nodeToInsertAfter;

            N++;
        } else {
            // Иначе бросить исключение
            throw new IllegalArgumentException("One of the arguments is null");
        }
    }

    public void insertBefore(DoubleNode nodeToInsertBefore, DoubleNode nodeToInsert)
            throws IllegalArgumentException {

        // Вставка переданного nodeToInsert перед переданным nodeToInsertBefore
        // Если переданные элементы - ненулевые,
        // вставить nodeToInsert перед nodeToInsertBefore
        if ( nodeToInsertBefore != null && nodeToInsert != null) {

            // Первоначальный элемент перед nodeToInsertBefore,
            // вместо которого вставляется nodeToInsert
            DoubleNode oldPrevious = nodeToInsertBefore.previous;

            // Если nodeToInsertBefore - не первый элемент списка,
            // вставить nodeToInsert между двумя элементами,
            if (oldPrevious != null) {
                oldPrevious.next = nodeToInsert;
                nodeToInsert.previous = oldPrevious;
            } else {
                // иначе вставить перед первым элементом
                nodeToInsert.previous = null;
                first = nodeToInsert;
            }

            nodeToInsertBefore.previous = nodeToInsert;
            nodeToInsert.next = nodeToInsertBefore;

            N++;
        } else {
            // Иначе (если переданный элемент - нулевой) бросить исключение
            throw new IllegalArgumentException("One of the arguments is null");
        }
    }

    public void remove(DoubleNode nodeToRemove) throws IllegalArgumentException {

        // Удаление переданного элемента
        // Если переданный элемент - нулевой,
        // бросить исключение
        if (nodeToRemove == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        // Если у переданного элемента имеется последующий
        // и предыдущий элементы (т.е. он не первый и не последний),
        // изменить ссылки у этих элементов соответственно
        if (nodeToRemove != first && nodeToRemove != last) {
            nodeToRemove.next.previous = nodeToRemove.previous;
            nodeToRemove.previous.next = nodeToRemove.next;
            N--;
        }

        // Если у переданного элемента есть последующий,
        // но нет предыдущего элемента (т.е. он - первый)
        // использовать pop()
        if (nodeToRemove == first) {
            pop();
        }

        // Если у переданного элемента нет последующего,
        // но есть предыдущий элемент (т.е. он - последний)
        // использовать removeFromEnd()
        if (nodeToRemove == last) {
            removeFromEnd();
        }
    }

    public Iterator<DoubleNode> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<DoubleNode> {
        private DoubleNode current = first;
        private DoubleNode realCurrent;
        private DoubleNode prev = null;

        public boolean hasNext() {
            return current != null;
        }

        public boolean hasPrevious() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public DoubleNode next() {
            prev = realCurrent;
            realCurrent = current;
            current = current.next;
            return realCurrent;
        }

        public DoubleNode previous() {
            prev = realCurrent;
            realCurrent = current;
            current = current.previous;
            return realCurrent;
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        DoubleLinkedListStack<Item> listToCompareWith = (DoubleLinkedListStack<Item>) x;

        if(this.size() != listToCompareWith.size()) return false;

        Iterator<DoubleNode> iterator2 = listToCompareWith.iterator();

        for(Iterator<DoubleNode> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().getItem().equals(iterator2.next().getItem())) {
                return false;
            }
        }

        return true;
    }
}