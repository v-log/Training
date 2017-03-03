package Laba_1_Trie;

import java.util.*;

/**
 * Created by Владимир on 09.01.2017.
 */

/*
Реализовать алгоритмы хеширования и префиксного дерева для
определения уникальных строк в массиве строк;
*/
public class Trie {

    // Корень дерева
    private TrieNode root = new TrieNode();

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

    public boolean isEmpty() {
        return root.children.isEmpty();
    }

    public void insert(String stringToInsert) throws IllegalArgumentException {

        // Вставка строки в дерево

        // Если введенная строка содержит небуквенные символы, бросить исключение
        if (!stringToInsert.matches("[\\p{Lu}\\p{Ll}]+")) {
            throw new IllegalArgumentException("Input should contain only letters");
        }

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
    }

    public boolean search(String stringToSearch) throws IllegalArgumentException {

        // Поиск строки в дереве

        // Если искомая строка содержит небуквенные символы, бросить исключение
        if (!stringToSearch.matches("[\\p{Lu}\\p{Ll}]+")) {
            throw new IllegalArgumentException("Input should contain only letters");
        }

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

        // Если удаляемая строка содержит небуквенные символы, бросить исключение
        if (!stringToRemove.matches("[\\p{Lu}\\p{Ll}]+")) {
            throw new IllegalArgumentException("Input should contain only letters");
        }

        removeStringWithStack(getNodesRoute(stringToRemove), stringToRemove);
    }

    private StackArray<TrieNode> getNodesRoute(String stringForRoute)
            throws IllegalArgumentException {

        // Получение стека узлов дерева по строке

        // Стек узлов для возврата
        StackArray<TrieNode> nodesRoute = new StackArray<>();

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
                throw new IllegalArgumentException("Trie has no such string");
            }
        }

        return nodesRoute;
    }

    private void removeStringWithStack(StackArray<TrieNode> stack, String stringToRemove)
            throws IllegalArgumentException {

        // Удаление строки из дерева при помощи стека
        // для обратного прохода

        // Если последний узел стека - лист,
        // удалить те узлы из этой строки,
        // начиная с конца, у которых нет других потомков
        if (stack.peek().isLeaf) {

            int initialStackSize = stack.size();

            for (int i = 0; i < initialStackSize; i++) {

                // Узел из стека для обратного прохода
                TrieNode currentNode = stack.pop();

                // Если у текущего узла при обратном проходе
                // нет потомков
                if (currentNode.children.isEmpty()) {

                    // Если текущий узел - узел с первым символом
                    // удаляемой строки, удалить текущий узел из
                    // потомков корня
                    if (stack.isEmpty()) {
                        root.children.remove(stringToRemove.toLowerCase().charAt(0));
                    } else {

                        // Иначе удалить его из потомков его узла-предка
                        stack.peek().children.remove(stringToRemove.toLowerCase().charAt(stack.size()));
                    }
                }
            }
        } else {

            // Иначе строка не содержится в дереве - бросить исключение
            throw new IllegalArgumentException("Trie has no such string");
        }
    }

    public void printTrie() {

        // Печать всех строк в дереве

        // Строка для вывода строк в дереве
        StringBuilder stringToPrint = new StringBuilder();

        printTrieInner(root, stringToPrint);
    }

    private void printTrieInner(TrieNode root, StringBuilder stringToPrint) {

        // Внутренний метод для вывода на экран всех строк в дереве

        // Проход по узлам-потомкам
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {

            // Добавление текущего символа к строке для вывода
            stringToPrint.append(entry.getKey());

            // Если узел - лист
            if (entry.getValue().isLeaf) {

                // Вывод на экран строки для вывода
                System.out.println(stringToPrint.toString());
            }

            // Если у текущего узла есть потомки
            if (!entry.getValue().children.isEmpty()) {

                // Повторить процедуру с потомками
                printTrieInner(entry.getValue(), stringToPrint);
            }

            stringToPrint.deleteCharAt(stringToPrint.length() - 1);
        }
    }

    public static void main (String[] args) {

        Trie trie = new Trie();

        String[] input = {"abcde", "zp", "abkpo", "k", "hd", "abr", };
        for (String str : input) {
            trie.insert(str);
        }

        trie.printTrie();

        for (String str : input) {
            trie.remove(str);
        }

        trie.insert("mno");
        trie.insert("jfk");

        System.out.println();
        System.out.println("After removing all:");

        trie.printTrie();

        System.out.println("------------");

        System.out.println(trie.search("mno"));
    }
}