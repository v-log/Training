package Laba_1_Trie;

/**
 * Created by vl on 02.03.17.
 */
public class TrieAsArrayForTests {
    private Trie[] triesArray;

    @SuppressWarnings("unchecked")
    public TrieAsArrayForTests(int arraySize, String[] trieArgs) {
        triesArray = new Trie[arraySize];

        for (int i = 0; i < triesArray.length; i++) {
            triesArray[i] = new Trie();

            for (String arg : trieArgs) {
                triesArray[i].insert(arg);
            }
        }
    }

    public Trie getTrie(int trieIndex) {
        return triesArray[trieIndex];
    }
}