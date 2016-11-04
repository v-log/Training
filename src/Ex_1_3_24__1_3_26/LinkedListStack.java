package Ex_1_3_24__1_3_26;

import java.util.Iterator;

/**
 * Created by vl on 10.10.16.
 */
/*
1.3.24 Write a method removeAfterInner() that takes a linked-list Node as argument and
removes the node following the given one (and does nothing if the argument or the next
field in the argument node is null).

1.3.25 Write a method insertAfterInner() that takes two linked-list Node arguments and
inserts the second after the first on its list (and does nothing if either argument is null).

1.3.26 Write a method remove() that takes a linked list and a string key as arguments
and removes all of the nodes in the list that have key as its item field.
*/

public class LinkedListStack<Item> implements Iterable<Item> {

    public static void main(String[] args) {

        // Create a stack and push/pop strings as directed on StdIn.
        LinkedListStack<String> s = new LinkedListStack<>();
        String[] listArgs = {"one", "two", "three", "four", "five", };

        for (int i = 0; i < listArgs.length; i++) {
            s.push(listArgs[i]);
        }

        s.removeAfter("four");

        for(Iterator<String> iter = s.iterator(); iter.hasNext();) {
            System.out.print(iter.next() + " ");
        }
    }

    private Node first; // top of stack (most recently added node)
    private int N; // number of items

    private class Node {

        // nested class to define nodes
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {

        // Add item to top of stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {

        // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    private Node searchNode(Item itemToSearch) throws IllegalArgumentException {

        // Поиск Node из списка по значению Item
        // Начальное значение для перехода к искомому Node
        Node resultNode = first;

        while (resultNode != null) {

            // При наличии совпадения прервать поиск
            if (resultNode.item.equals(itemToSearch)) {
                break;
            }

            resultNode = resultNode.next;
        }

        // Если искомый элемент не найден, бросить соответствующее исключение
        if(resultNode == null) {
            throw new IllegalArgumentException("List " + this
                    + " does not contain \"" + itemToSearch.toString() + "\"");
        }

        return resultNode;
    }

    public void removeAfter(Item itemToRemoveAfter) {

        // Remove Node from this linked-list stack after
        // Node with item of itemToRemoveAfter
        // Node, после которого удаляется элемент
        Node nodeToRemoveAfter = searchNode(itemToRemoveAfter);

        // Обращение к внутреннему методу для совершения операции удаления
        removeAfterInner(nodeToRemoveAfter);
    }

    private void removeAfterInner(Node nodeToRemoveAfter)
            throws IndexOutOfBoundsException {

        // Inner method for removeAfter()
        if ( nodeToRemoveAfter != null && nodeToRemoveAfter.next != null ) {
            Node nodeToRemove = nodeToRemoveAfter.next;
            nodeToRemoveAfter.next = nodeToRemove.next;
            N--;
        } else {
            throw new IndexOutOfBoundsException("This or the next element is null");
        }
    }

    public void insertAfter(Item itemToInsertAfter, Item itemToInsert) {

        // Insert Node from this Linked-List Stack after Node with
        // item <itemToInsertAfter>
        // Node, после которого вставляется элемент
        Node nodeToInsertAfter = searchNode(itemToInsertAfter);

        // Вставляемый элемент
        Node nodeToInsert = new Node();
        nodeToInsert.item = itemToInsert;

        // Обращение к внутреннему методу для совершения операции вставки
        insertAfterInner(nodeToInsertAfter, nodeToInsert);
    }

    private void insertAfterInner(Node nodeToInsertAfter, Node nodeToInsert) {

        // Inner method for insertAfter()
        if ( nodeToInsertAfter != null && nodeToInsert != null ) {
            Node temp = nodeToInsertAfter.next;
            nodeToInsertAfter.next = nodeToInsert;
            nodeToInsert.next = temp;
            N++;
        }
    }

    public void remove(LinkedListStack<Item> list, String key) throws IllegalArgumentException {

        // Remove from Linked-List Stack <list> a Node with string item "key"
        // Счетчик несовпадения удаляемого элемента при проходе по списку
        // (для определения неверно введенного <key>)
        int noMatchCount = 0;
        // Фиксирование изначального размера списка
        // (для определения неверно введенного <key>)
        int listSize = size();
        // Флаг наличия удаления из списка
        boolean deleted = false;
        Iterator<Item> iter = list.iterator();

        // Поиск и удаление Node из <list> при совпадении <item> с <key>
        while (iter.hasNext()) {
            Item curr = iter.next();

            if ( curr.equals(key) ) {
                iter.remove();
                deleted = true;
            }
        }

        // Если удаляемый элемент отсутствует в списке <list>, бросить исключение
        if (!deleted) {
            throw new IllegalArgumentException("List \""
                    + this + "\" does not contain \"" + key + "\"");
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private Node realCurrent;
        private Node prev = null;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {

            if ( prev == null ) {
                realCurrent = null;
                first = current;
            } else {
                prev.next = current;
            }
            N--;
        }

        public Item next() {
            prev = realCurrent;
            realCurrent = current;
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        LinkedListStack<Item> listToCompareWith = (LinkedListStack<Item>) x;

        if(size() != listToCompareWith.size()) return false;

        Iterator<Item> iterator2 = listToCompareWith.iterator();

        for(Iterator<Item> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }
}