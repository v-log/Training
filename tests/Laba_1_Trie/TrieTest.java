package Laba_1_Trie;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vl on 24.02.17.
 */
public class TrieTest {
    private boolean invariantTest(Trie trieToTest) throws Exception {
        return invariantTestOne(trieToTest) && invariantTestTwo(trieToTest);
    }

    private boolean invariantTestOne(Trie trieToTest) {

        // Тест 1
        // Если дерево пустое, у корневого узла потомков нет
        if (trieToTest.isEmpty()) {

            return trieToTest.getChildren(trieToTest.getRoot()).isEmpty();
        } else {

            // Если дерево непустое, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    private boolean invariantTestTwo(Trie trieToTest) {

        // Тест 2
        // Если дерево непустое, каждый узел, у которого нет
        // потомка - лист (кроме корневого),
        // и количество листов равно размеру дерева.

        if (!trieToTest.isEmpty()) {
            return leafCheck(trieToTest.getRoot().children) &&
                    leafCount(trieToTest.getRoot().children, 0) == trieToTest.size();
        } else {

            // Если дерево пустое, начальных условий для теста нет,
            // и нет смысла возвращать false
            return true;
        }
    }

    private boolean leafCheck(Map<Character, Trie.TrieNode> children) {

        Iterator<Map.Entry<Character, Trie.TrieNode>> iterator = children.entrySet().iterator();

        while (iterator.hasNext()) {

            Trie.TrieNode currentNode = iterator.next().getValue();

            if (!currentNode.children.isEmpty()) {
                leafCheck(currentNode.children);
            } else {

                if (!currentNode.isLeaf) {
                    return false;
                }
            }
        }

        return true;
    }

    private Integer leafCount(Map<Character, Trie.TrieNode> children, Integer counter) {

        Iterator<Map.Entry<Character, Trie.TrieNode>> iterator = children.entrySet().iterator();

        while (iterator.hasNext()) {

            Trie.TrieNode currentNode = iterator.next().getValue();

            if (currentNode.isLeaf) {
                counter++;
            }

            if (!currentNode.children.isEmpty()) {
                counter = leafCount(currentNode.children, counter);
            }
        }

        return counter;
    }

    @Test
    public void insertTest() throws Exception {

        // Аргументы для заполнения деревьев
        String[] input = {"abcde", "abclz", "abkpo", "k", "hd", "cma", };

        // Деревья для тестов
        TrieAsArrayForTests originalTries = new TrieAsArrayForTests(1, input);


        // Тест на добавление элемента в пустое дерево
        Trie trie0 = new Trie();

        trie0.insert("abcd");

        assertTrue(invariantTest(trie0));


        // Тест на добавление нескольких элементов в пустое дерево
        Trie trie1 = originalTries.getTrie(0);

        assertTrue(invariantTest(trie1));


        // Тест на перехват исключения при добавлении строки
        // с небуквенными символами (нижнее подчеркивание)
        Trie trie2 = new Trie();

        try {
            trie2.insert("_");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}

        // Тест на перехват исключения при добавлении строки
        // с небуквенными символами (дефис посередине строки)
        Trie trie3 = new Trie();

        try {
            trie3.insert("ab-cd");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void searchTest() throws Exception {

        // Аргументы для заполнения деревьев
        String[] input = {"abcde", "abclz", "abkpo", "k", "hd", "cma", };

        // Деревья для тестов
        TrieAsArrayForTests originalTries = new TrieAsArrayForTests(1, input);


        // Тест на поиск строки в дереве с элементами
        Trie trie0 = originalTries.getTrie(0);

        boolean flag0 = true;

        for (String arg : input) {
            flag0 = trie0.search(arg);
        }

        assertTrue(flag0 && invariantTest(trie0));


        // Тест на перехват исключения при поиске строки
        // с небуквенными символами (нижнее подчеркивание)
        Trie trie1 = new Trie();

        try {
            trie1.search("_");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при поиске строки
        // с небуквенными символами (дефис посередине строки)
        Trie trie2 = new Trie();

        try {
            trie2.search("ab-cd");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void removeTest() throws Exception {

        // Аргументы для заполнения деревьев
        String[] input = {"abcde", "abclz", "abkpo", "k", "hd", "cma", };

        // Деревья для тестов
        TrieAsArrayForTests originalTries = new TrieAsArrayForTests(4, input);


        // Тест на удаление одного элемента
        Trie trie0 = originalTries.getTrie(0);

        trie0.remove("abkpo");

        assertTrue(!trie0.search("abkpo") && invariantTest(trie0));


        // Тест на удаление всех элементов
        Trie trie1 = originalTries.getTrie(1);

        for (String arg : input) {
            trie1.remove(arg);
        }

        assertTrue(trie1.isEmpty() && invariantTest(trie1));


        // Тест на перехват исключения при удалении
        // из пустого дерева
        Trie trie2 = new Trie();

        try {
            trie2.remove("");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при удалении строки
        // с небуквенными символами (нижнее подчеркивание)
        Trie trie3 = originalTries.getTrie(2);

        try {
            trie3.search("_");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}


        // Тест на перехват исключения при удалении строки
        // с небуквенными символами (дефис посередине строки)
        Trie trie4 = originalTries.getTrie(3);

        try {
            trie4.search("ab-cd");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {}
    }
}