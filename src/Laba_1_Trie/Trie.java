package Laba_1_Trie;

import java.util.*;

/**
 * Created by Владимир on 09.01.2017.
 */

/*
Реализовать алгоритмы хеширования и префиксного дерева для
определения уникальных строк в массиве строк;
*/
public class Trie implements Iterable<String> {

    // Корень дерева
    private TrieNode root = new TrieNode();

    // Количество элементов дерева
    private int N = 0;

    // Внутренный класс узла дерева
    public static class TrieNode {
        // public - для тестов

        // Дочерние элементы узла
        Map<Character, TrieNode> children = new HashMap<>();

        // Является ли узел листом
        boolean isLeaf = false;
    }

    public TrieNode getRoot() {
        // Для тестов
        return root;
    }

    public Map<Character, TrieNode> getChildren(TrieNode node) {
        return node.children;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(String stringToInsert) throws IllegalArgumentException {

        // Вставка строки в дерево

        checkInput(stringToInsert);

        // Начальное значение для текущего узла
        TrieNode currentNode = root;

        // Посимвольный проход по переданной строке
        for (char ch : stringToInsert.toLowerCase().toCharArray()) {

            // Если текущий узел не содержит в потомках текущего
            // символа из строки, добавить его
            if (!currentNode.children.containsKey(ch)) {
                currentNode.children.put(ch, new TrieNode());
            }

            // Переход к потомку с текущим символом из строки
            currentNode = currentNode.children.get(ch);
        }

        // Обозначить узел с последним символом как лист
        currentNode.isLeaf = true;

        N++;
    }

    public boolean search(String stringToSearch) throws IllegalArgumentException {

        // Поиск строки в дереве

        checkInput(stringToSearch);

        // Начальное значение для текущего узла
        TrieNode currentNode = root;

        // Посимвольный проход по переданной строке
        for (char ch : stringToSearch.toLowerCase().toCharArray()) {

            // Если текущий узел не содержит в потомках текущего
            // символа из строки, вернуть false
            if (!currentNode.children.containsKey(ch)) {
                return false;
            } else {

                // Иначе перейти к потомку с текущим символом из строки
                currentNode = currentNode.children.get(ch);
            }
        }

        // При успешном проходе по всем символам переданной
        // строки, и если последний узел - лист,
        // строка присутствует в дереве
        return currentNode.isLeaf;
    }

    public void remove(String stringToRemove) throws IllegalArgumentException {

        // Удаление строки из дерева

        checkInput(stringToRemove);

        removeStringWithStack(getNodesRoute(stringToRemove), stringToRemove);

        N--;
    }

    private Stack<TrieNode> getNodesRoute(String stringForRoute)
            throws IllegalArgumentException {

        // Получение стека узлов дерева по строке

        // Стек узлов для возврата
        Stack<TrieNode> nodesRoute = new Stack<>();

        char[] stringAsCharArray = stringForRoute.toLowerCase().toCharArray();

        // Начальное значение для текущего узла
        TrieNode currentNode = root;

        // Посимвольный проход по переданной строке
        // для заполнения стека, а также для
        // определения наличия строки в дереве
        for (char ch : stringAsCharArray) {

            // Если текущий узел содержит в потомках текущий символ
            // из строки, добавить его в стек
            if (currentNode.children.containsKey(ch)) {

                // Переход к потомку с текущим символом из строки
                currentNode = currentNode.children.get(ch);

                // Добавление текущего узла в стек
                nodesRoute.push(currentNode);
            } else {

                // Иначе строка не содержится в дереве - бросить исключение
                throw new IllegalArgumentException("Trie has no such string1: " + stringForRoute);
            }
        }

        return nodesRoute;
    }

    private void removeStringWithStack(Stack<TrieNode> stack, String stringToRemove)
            throws IllegalArgumentException {

        // Удаление строки из дерева при помощи стека
        // для обратного прохода

        // Если последний узел стека - лист
        // (значит слово присутствует в дереве,
        // и можно его удалять),
        // удалить те узлы из этой строки,
        // начиная с конца, у которых нет других потомков
        if (stack.peek().isLeaf) {

            int initialStackSize = stack.size();

            stack.peek().isLeaf = false;

            for (int i = 0; i < initialStackSize; i++) {

                // Узел из стека для обратного прохода
                TrieNode currentNode = stack.pop();

                // Если у текущего узла при обратном проходе
                // нет потомков
                if (currentNode.children.isEmpty() && !currentNode.isLeaf) {

                    // Если текущий узел - узел с первым символом
                    // удаляемой строки, удалить текущий узел из
                    // потомков корня
                    if (stack.isEmpty()) {
                        root.children.remove(stringToRemove.toLowerCase().charAt(0));
                    } else {

                        // Иначе удалить его из потомков его узла-предка
                        stack.peek().children.remove(stringToRemove.toLowerCase().charAt(stack.size()));
                    }
                } else {
                    break;
                }
            }
        } else {

            // Иначе строка не содержится в дереве - бросить исключение
            throw new IllegalArgumentException("Trie has no such string: " + stringToRemove);
        }
    }

    private static void checkInput(String stringToCheck) throws IllegalArgumentException {

        // Проверка строки на отсутствие небуквенных символов

        if (!stringToCheck.matches("[\\p{Lu}\\p{Ll}]+")) {
            throw new IllegalArgumentException("Input should contain only letters");
        }
    }

    public void printTrie() {

        // Вывод на экран всех строк в дереве

        for (String arg : this) {
            System.out.println(arg);
        }
    }

    public Iterator<String> iterator() {
        return new Trie.TrieIterator();
    }

    private class TrieIterator implements Iterator<String> {

        Stack<Iterator<Map.Entry<Character, TrieNode>>> stackOfIterators = new Stack<>();

        Iterator<Map.Entry<Character, TrieNode>> currentIterator =
                stackOfIterators.push(root.children.entrySet().iterator());

        StringBuilder stringToReturn = new StringBuilder();

        boolean flagForReturn;

        boolean pushed;

        int fullIteratorCount = 0;

        public boolean hasNext() {
            return !stackOfIterators.isEmpty();
        }

        public String next() {

            // Проход по дереву осуществляется при помощи
            // стека итераторов, начиная от корня

            flagForReturn = false;
            pushed = false;
            String temp = null;

            currentIterator = stackOfIterators.peek();

            // Осуществляется проход по текущему итератору:
            // если текущий узел - лист, возвращает лист
            // (индикатор - flagForReturn);

            // если у текущего узла есть потомки, итератор
            // потомков этого узла заносится в стек
            // (индикатор - pushed),
            // и проход продолжается по этому добавленному итератору;

            // когда текущий итератор пройден, из стека достается
            // итератор и по нему продолжается проход, по тем же правилам.

            if (currentIterator.hasNext()) {

                Map.Entry<Character, TrieNode> currentEntry = currentIterator.next();

                Set<Map.Entry<Character, TrieNode>> currentChildren = currentEntry.getValue().children.entrySet();

                stringToReturn.append(currentEntry.getKey());

                if (!currentIterator.hasNext()) {
                    fullIteratorCount++;
                }

                if (!currentChildren.isEmpty()) {
                    stackOfIterators.push(currentChildren.iterator());
                    pushed = true;
                }

                if (currentEntry.getValue().isLeaf) {
                    temp = stringToReturn.toString();
                    flagForReturn = true;
                }
            } else {
                if (!stackOfIterators.isEmpty()) {
                    stackOfIterators.pop();
                    fullIteratorCount--;
                }
            }

            // Проверка на количество завершившихся итераторов в стеке -
            // для определения последнего шага итератора всего дерева
            if (fullIteratorCount == stackOfIterators.size()) {
                stackOfIterators.clear();
            }

            // При переходе к следующему потомку узла из строки для
            // возврата (после сохранения ее в temp) удаляется
            // последний символ, кроме случаев когда в стек был
            // добавлен новый итератор (перешли на уровень ниже)
            if (!pushed && stringToReturn.length() > 0) {
                stringToReturn.deleteCharAt(stringToReturn.length() - 1);
            }

            if (flagForReturn) {
                return temp;
            } else {
                return next();
            }
        }
    }

    public static void main (String[] args) {

        Trie trie = new Trie();

//        String[] input = {"zbjcde", "zbj", "zbjkpo", "kp", "hd", "ab", };
//        String[] input = {"az", "bx", "em", };
        String[] input = {"a", "ab", "abc", "abcd", "abcde", };
//        String[] input = {"abcde", "abcd", "abc", "ab", "a", };
//        String[] input = {"a", "b", "c", "d", "e", };

        for (String str : input) {
            System.out.println("inserting " + str);
            trie.insert(str);
        }

        trie.printTrie();

        for (String str : input) {
            System.out.println("removing " + str);
            trie.remove(str);
        }

        System.out.println();
        System.out.println("After removing all:");
        System.out.println("------------");

        trie.insert("zpd");
        trie.insert("jfk");

        trie.printTrie();

        System.out.println();

        System.out.println(trie.search("mno"));
    }
}