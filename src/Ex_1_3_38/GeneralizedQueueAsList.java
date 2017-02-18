package Ex_1_3_38;

import java.util.Iterator;

/**
 * Created by vl on 10.01.17.
 */
public class GeneralizedQueueAsList<Item> {

    // Первый элемент - элемент в начале очереди
    private DoubleNode first;

    // Последний элемен - элемент в конце очереди
    private DoubleNode last;

    // Количество элементов в очереди
    private int N = 0;

    public class DoubleNode {

        private Item item;
        private DoubleNode next;
        private DoubleNode previous;

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

    public void insert(Item itemToInsert) {
        // Добавление элемента в конец очереди

        // Создание узла для нового элемента
        DoubleNode nodeToInsert = new DoubleNode();

        // Добавление значения новому узлу
        nodeToInsert.item = itemToInsert;

        // Если очередь пуста, вставляемый элемент
        // будет и первым и последним
        if (isEmpty()) {

            // Вставляемый элемент - первый
            first = nodeToInsert;

            // Вставляемый элемент - последний
            last = nodeToInsert;

        } else {

            // Иначе, если в очереди один элемент,
            if (first == last) {

                // Вставляемый элемент - последний
                last = nodeToInsert;

                // Первый элемент ссылается на
                // новый, как на следующий
                first.next = nodeToInsert;

                // Новый элемент ссылается на первый,
                // как на предыдущий
                nodeToInsert.previous = first;

            } else {

                // Иначе, если в очереди больше одного элемента

                // Новый элемент ссылается на последний,
                // как на предыдущий
                nodeToInsert.previous = last;

                // Последний элемент ссылается на новый,
                // как на следующий
                last.next = nodeToInsert;

                //Вставляемый элемент - последний
                last = nodeToInsert;
            }
        }

        N++;
    }

    public Item delete(int k) {
        // Удаление и возврат k-го последнего добавленного элемента

        // Удаляемый и возвращаемый элемент
        DoubleNode nodeToReturn;

        // Если очередь пуста, бросить исключение
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        // Если k выходит за рамки индексов массива, бросить исключение
        if ( k <= 0 || k > size() ) {
            throw new IllegalArgumentException("\"k\" must be: " +
                    " 0 < k <= " + size());
        }

        // Переход к удаляемому элементу
        nodeToReturn = last;
        for (int i = 0; i < k - 1; i++) {

            nodeToReturn = nodeToReturn.previous;
        }

        // Если в очереди один элемент
        if(size() == 1) {

            // Обнулить ссылки первого и последнего элемента на него
            if (nodeToReturn == first && nodeToReturn == last) {
                first = null;
                last = null;
            }
        } else {
            // Иначе, если в очереди не один элемент

            // Если удаляется элемент не в конце и не в начале очереди,
            // установить ссылку у элементов справа и слева друг на друга
            if (nodeToReturn != last && nodeToReturn != first) {

                nodeToReturn.previous.next = nodeToReturn.next;
                nodeToReturn.next.previous = nodeToReturn.previous;
            }

            // Если удаляется первый элемент очереди,
            // обнулить ссылку на него у следующего элемента
            // и сделать следующий элемент первым
            if (nodeToReturn == first) {

                nodeToReturn.next.previous = null;
                first = nodeToReturn.next;
            }

            // Если удаляется последний элемент очереди,
            // обнулить ссылку на него у предыдущего элемента
            // и сделать предыдущий элемент последним
            if (nodeToReturn == last) {

                nodeToReturn.previous.next = null;
                last = nodeToReturn.previous;
            }
        }

        N--;

        return nodeToReturn.item;
    }

    public Item dequeue() {
        // Удаление и возврат первого элемента в очереди

        return delete(size());
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private DoubleNode current = first;
        private DoubleNode realCurrent;
//        private DoubleNode previous = null;

        public boolean hasNext() {
            return current != null;
        }

        public boolean hasPrevious() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
//            previous = realCurrent;
            realCurrent = current;
            current = current.next;
            return realCurrent.item;
        }

        public Item previous() {
//            previous = realCurrent;
            realCurrent = current;
            current = current.previous;
            return realCurrent.item;
        }
    }

    public boolean equals(Object x) {

        if(this == x) return true;
        if(x == null) return false;
        if(this.getClass() != x.getClass()) return false;

        GeneralizedQueueAsList<Item> queueToCompareWith = (GeneralizedQueueAsList<Item>) x;

        if(this.size() != queueToCompareWith.size()) return false;

        Iterator<Item> iterator2 = queueToCompareWith.iterator();

        for(Iterator<Item> iterator1 = iterator(); iterator1.hasNext();) {
            if (!iterator1.next().equals(iterator2.next())) {
                return false;
            }
        }

        return true;
    }
}