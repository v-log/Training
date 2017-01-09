package Laba_1_Trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Владимир on 09.01.2017.
 */

/*
Реализовать алгоритмы хеширования и префиксного дерева для
определения уникальных строк в массиве строк;
*/
public class TrieTree {

    // Корень дерева
    private TrieNode root = new TrieNode();

    // Список для вывода слов в дереве
    private static LinkedList<Character> listForPrint;

    // Внутренный класс узла дерева
    private static class TrieNode {

        // Дочерние элементы узла
        Map<Character, TrieNode> children = new HashMap<>();

        // Является ли узел листом
        boolean isLeaf = false;

        // Является ли узел уникальным
        boolean isUnique = true;
    }

    public void insert(String stringToInsert) {
        // Вставка строки в дерево

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

        // Если после прохода последний узел - лист
        // (т.е. слово уже есть в дереве),
        // установить уникальность на false
        if (currentNode.isLeaf) {
            currentNode.isUnique = false;
        } else {
            // Иначе обозначить узел с последним символом как лист
            currentNode.isLeaf = true;
        }
    }

    public boolean search(String stringToSearch) {
        // Поиск строки в дереве

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

        // Начальное значение для текущего узла
        TrieNode currentNode = root;

        //Массив для обратного прохода и удаления
        TrieNode[] nodesRoute = new TrieNode[stringToRemove.length()];

        char[] stringToCharArray = stringToRemove.toLowerCase().toCharArray();
        int i = 0;

        // Посимвольный проход по переданной строке
        // для заполнения массива для обратного прохода,
        // а также для определения наличия строки в дереве
        for (char ch : stringToCharArray) {

            // Если текущий узел содержит в потомках текущий символ
            // из строки, добавить его в массив для обратного прохода
            if (currentNode.children.containsKey(ch)) {

                // Переход к потомку с текущим символом из строки
                currentNode = currentNode.children.get(ch);

                // Добавление текущего узла в массив для обратного прохода
                nodesRoute[i] = currentNode;
            } else {
                // Иначе, если строка не содержится в дереве, бросить исключение
                throw new IllegalArgumentException("TrieTree has no such string");
            }

            i++;
        }

        // Если переданная строка - лист, удалить те узлы из этой строки,
        // начиная с конца, у которых нет других потомков
        if (currentNode.isLeaf) {

            // Обратный проход по узлам из строки
            for (int j = nodesRoute.length - 1; j >= 0; j--) {

                // Если у текущего узла из массива для
                // обратного прохода нет потомков,
                // удалить его из потомков его узла-предка
                if (nodesRoute[j].children.isEmpty()) {

                    // Если текущий узел - узел с первым символом строки,
                    // удалить текущий узел из потомков корня
                    if (j == 0) {
                        root.children.remove(stringToCharArray[j]);
                    } else {
                        nodesRoute[j - 1].children.remove(stringToCharArray[j]);
                    }
                } else break;
            }
        }
    }

    public boolean isUnique(String stringToCheck) {
        // Проверка строки на уникальность

        // Начальное значение для текущего узла
        TrieNode currentNode = root;

        // Посимвольный проход по переданной строке
        for (char ch : stringToCheck.toLowerCase().toCharArray()) {
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
        // строки, и если последний узел - лист и уникален,
        // строка уникальна
        return currentNode.isLeaf && currentNode.isUnique;
    }

    public void printTrie() {
        // Печать всех строк в дереве

        // Создание списка для сохранения пройденных символов (для вывода)
        listForPrint = new LinkedList<>();

        printTrieInner(root, listForPrint);
    }

    private void printTrieInner(TrieNode root, LinkedList<Character> listForPrint) {
        // Внутренний метод для печати всех строк в дереве

        // Строка на вывод
        StringBuilder output;

        // Проход по узлам-потомкам
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {

            // Если узел - лист
            if (entry.getValue().isLeaf) {

                // Если в списке символов для вывода есть символы,
                // либо если у текущего узла нет потомков
                if (!listForPrint.isEmpty()) {

                    // Обратный итератор для прохода по списку символов для вывода
                    Iterator<Character> iterator = listForPrint.listIterator();

                    output = new StringBuilder();

                    // Объединить символы из списка с символом в узле,
                    // чтобы получить слово, и вывести на экран
                    while (iterator.hasNext()) {
                        output.append(iterator.next());
                    }
                    output.append(entry.getKey());

                    // Вывод строки на экран
                    System.out.println(output);
                } else {
                    // Иначе вывод на экран символа текущего узла
                    System.out.println(entry.getKey());
                }
            }

            // Если у текущего узла есть потомки
            if (!entry.getValue().children.isEmpty()) {

                // Добавить текущий символ в список
                listForPrint.addLast(entry.getKey());


                // Повторить всю процедуру с потомками
                printTrieInner(entry.getValue(), listForPrint);

                // Удалить текущий символ из списка
                listForPrint.removeLast();
            }
        }
    }

    public static void main (String[] args) {

        TrieTree trie = new TrieTree();

        String[] input = {"abcde", "abcdk", "abcpt", "k", "bu", "hfj", };
        for (String str : input) {
            trie.insert(str);
        }

        trie.printTrie();

        for (String str : input) {
            trie.remove(str);
        }

        System.out.println();
        System.out.println("After removing all:");
        System.out.println();

        trie.printTrie();
    }
}
