package Ex_1_3_31;

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

public class DoubleLinkedListStack<Item> implements Iterable<Item> {

    private DoubleNode first; // top of stack (most recently added node)
    private int N; // number of items

    private class DoubleNode {

        // nested class to define nodes
        Item item;
        DoubleNode previous;
        DoubleNode next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {

        // Add item to top of stack.
        DoubleNode oldfirst = first;
        first = new DoubleNode();
        first.item = item;
        first.next = oldfirst;
        if(oldfirst != null) {
            oldfirst.previous = first;
        }
        N++;
    }

    public Item pop() {

        // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public void insertAtBeginning(Item itemToInsert) {

        // Вставить значение в начало списка
        this.push(itemToInsert);
    }

    public void insertAtEnd(Item itemToInsert) {

        // Вставить значение в конец списка
        DoubleNode newLast = new DoubleNode();
        newLast.item = itemToInsert;

        // Начальное значение DoubleNode для перехода к последнему DoubleNode
        DoubleNode tempNode = this.first;

        // Последний DoubleNode
        DoubleNode lastNode = null;

        // Переход к последнему DoubleNode
        for (int i = 0; i < this.size(); i++) {
            lastNode = tempNode;
            tempNode = tempNode.next;
        }

        // Добавление нового элемента в конец списка
        if(lastNode != null) {
            lastNode.next = newLast;
            newLast.previous = lastNode;
        }
        N++;
    }

    public void removeFromBeginning() {

        // Если в списке нет элементов, бросить исключение
        if(this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");
        this.pop();
    }

    public void removeFromEnd() {

        // Начальное значение DoubleNode для перехода к предпоследнему DoubleNode
        DoubleNode tempNode = this.first;
        // Предпоследний DoubleNode
        DoubleNode penult = null;

        // Если в списке нет элементов, бросить исключение
        if(this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");

        // Переход к предпоследнему DoubleNode
        penult = getDoubleNodeAt(this.size() - 2);

        // Обнуление ссылки на следующий элемент у предпоследнего элемента списка
        if(penult != null && penult.next != null) {
            penult.next = null;
        }

        N--;
    }

    public void removeAfter(Item itemToRemoveAfter) throws IllegalArgumentException {

        // Remove DoubleNode from this linked-list stack after DoubleNode with item of itemToRemoveAfter
        // Индекс искомого DoubleNode с item равным itemToRemoveAfter
        int indexToRemoveAfter = indexSearch(itemToRemoveAfter);

        // Если искомый элемент не найден, бросить соответствующее исключение
        if(indexToRemoveAfter == -1) throw new IllegalArgumentException("List " + this
                + " does not contain \"" + itemToRemoveAfter.toString() + "\"");

        // Искомый DoubleNode
        DoubleNode resultNode = getDoubleNodeAt(indexToRemoveAfter);

        // Обращение к внутреннему методу для совершения операции удаления
        this.removeAfterInner(resultNode);
    }

    private void removeAfterInner(DoubleNode nodeToRemoveAfter) throws IndexOutOfBoundsException {

        // Inner method for removeAfter()
        if ( nodeToRemoveAfter != null && nodeToRemoveAfter.next != null ) {
            DoubleNode nodeToRemove = nodeToRemoveAfter.next;
            nodeToRemoveAfter.next = nodeToRemove.next;
            N--;
        } else {
            throw new IndexOutOfBoundsException("This or the next element is null");
        }
    }

    public void insertAfter(Item itemToInsertAfter, Item itemToInsert) throws IllegalArgumentException {

        // Insert DoubleNode from this Linked-List Stack after DoubleNode with item <itemToInsertAfter>
        // Вставляемый DoubleNode
        DoubleNode nodeToInsert = new DoubleNode();
        nodeToInsert.item = itemToInsert;

        // Индекс искомого DoubleNode с item равным itemToInsertAfter
        int indexToInsertAfter = indexSearch(itemToInsertAfter);

        // Если искомый элемент не найден, бросить соответствующее исключение
        if(indexToInsertAfter == -1) {
            throw new IllegalArgumentException("List \"" + this
                    + "\" does not contain \"" + itemToInsertAfter.toString() + "\"");
        }

        // Искомый DoubleNode
        DoubleNode resultNode = getDoubleNodeAt(indexToInsertAfter);

        // Обращение к внутреннему методу для совершения операции вставки
        this.insertAfterInner(resultNode, nodeToInsert);
    }

    private void insertAfterInner(DoubleNode nodeToInsertAfter, DoubleNode nodeToInsert) {

        // Inner method for insertAfter()
        if ( nodeToInsertAfter != null && nodeToInsert != null ) {

            // Первоначальный элемент перед nodeToInsertBefore,
            // вместо которого вставляется nodeToInsert
            DoubleNode oldNext = nodeToInsertAfter.next;

            // Если nodeToInsertAfter - не последний элемент списка,
            // вставляем nodeToInsert между двумя элементами
            if (oldNext != null) {
                oldNext.previous = nodeToInsert;
                nodeToInsert.next = oldNext;
            }

            nodeToInsertAfter.next = nodeToInsert;
            nodeToInsert.previous = nodeToInsertAfter;
            N++;
        }
    }

    public void insertBefore(Item itemToInsertBefore, Item itemToInsert) throws IllegalArgumentException {

        // Insert DoubleNode from this Linked-List Stack before DoubleNode with item <itemToInsertAfter>
        // Вставляемый DoubleNode
        DoubleNode nodeToInsert = new DoubleNode();
        nodeToInsert.item = itemToInsert;

        // Индекс искомого DoubleNode с item равным itemToInsertBefore
        int indexToInsertBefore = indexSearch(itemToInsertBefore);

        // Если искомый элемент не найден, бросить соответствующее исключение
        if(indexToInsertBefore == -1) {
            throw new IllegalArgumentException("List \"" + this
                    + "\" does not contain \"" + itemToInsertBefore.toString() + "\"");
        }

        // Искомый DoubleNode
        DoubleNode resultNode = getDoubleNodeAt(indexToInsertBefore);

        // Обращение к внутреннему методу для совершения операции вставки
        this.insertBeforeInner(resultNode, nodeToInsert);
    }

    private void insertBeforeInner(DoubleNode nodeToInsertBefore, DoubleNode nodeToInsert) {

        // Iner method for insertBefore()
        if ( nodeToInsertBefore != null && nodeToInsert != null) {

            // Первоначальный элемент перед nodeToInsertBefore,
            // вместо которого вставляется nodeToInsert
            DoubleNode oldPrevious = nodeToInsertBefore.previous;

            // Если nodeToInsertBefore - не первый элемент списка,
            // вставляем nodeToInsert между двумя элементами,
            // иначе используем push()
            if (oldPrevious != null) {
                oldPrevious.next = nodeToInsert;
                nodeToInsert.previous = oldPrevious;
                N++;
            } else {
                push(nodeToInsert.item);
            }

            nodeToInsertBefore.previous = nodeToInsert;
            nodeToInsert.next = nodeToInsertBefore;
        }
    }

    public void remove(DoubleLinkedListStack<Item> list, String key) {

        // Remove from Double Linked-List Stack <list> a DoubleNode with string item "key"
        // Счетчик несовпадения удаляемого элемента при проходе по списку
        // (для определения неверно введенного <key>)
        int noMatchCount = 0;
        // Фиксирование изначального размера списка
        // (для определения неверно введенного <key>)
        int listSize = this.size();
        Iterator<Item> iter = list.iterator();

        // Поиск и удаление DoubleNode из списка при совпадении <iter> с <key>
        while (iter.hasNext()) {
            Item curr = iter.next();

            if ( curr.equals(key) ) {
                iter.remove();
                N--;
            } else {
                noMatchCount++;
            }
        }

        // Если удаляемый элемент отсутствует в списке , бросить исключение
        if(noMatchCount == listSize) {
            throw new IllegalArgumentException("List \""
                    + this + "\" does not contain \"" + key + "\"");
        }
    }

    private int indexSearch(Item itemToSearch) {

        // Inner method to get index of searched item
        // Индекс искомого DoubleNode с item равным itemToSearch
        int indexSearchTemp = -1;
        int indexSearch = -1;

        // Поиск DoubleNode с item равным itemToSearch
        for(Item args : this) {
            if(!args.equals(itemToSearch)) {
                indexSearchTemp++;
            } else {
                indexSearchTemp++;
                indexSearch = indexSearchTemp;
                break;
            }
        }

        return indexSearch;
    }

    private DoubleNode getDoubleNodeAt(int index) {

        // Inner method to get Double Node at <index>
        // Начальное значение DoubleNode для искомого DoubleNode
        DoubleNode tempNode = this.first;
        // Искомый DoubleNode
        DoubleNode resultNode = null;

        // Определение искомого DoubleNode
        for (int i = 0; i < index + 1; i++) {
            resultNode = tempNode;
            tempNode = tempNode.next;
        }
        return resultNode;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
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

            if ( prev == null ) {
                realCurrent = null;
                first = current;
            } else {
                prev.next = current;
            }
        }

        public Item next() {
            prev = realCurrent;
            realCurrent = current;
            Item item = current.item;
            current = current.next;
            return item;
        }

        public Item previous() {
            prev = realCurrent;
            realCurrent = current;
            Item item = current.item;
            current = current.previous;
            return item;
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        DoubleLinkedListStack<Item> listToCompareWith = (DoubleLinkedListStack<Item>) x;

        if(this.size() != listToCompareWith.size()) return false;

        Iterator<Item> iterator2 = listToCompareWith.iterator();

        for(Iterator<Item> iterator1 = this.iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }
}