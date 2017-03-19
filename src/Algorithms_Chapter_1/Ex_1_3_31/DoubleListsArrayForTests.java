package Algorithms_Chapter_1.Ex_1_3_31;

/**
 * Created by vl on 29.10.16.
 */
public class DoubleListsArrayForTests {
    private DoubleLinkedListStack<String>[] listsArray;

    @SuppressWarnings("unchecked")
    public DoubleListsArrayForTests(int arraySize) {
        listsArray = new DoubleLinkedListStack[arraySize];

        for (int i = 0; i < listsArray.length; i++) {
            listsArray[i] = new DoubleLinkedListStack<>();

            String[] listArgs = {"five", "four", "three", "two", "one", };

            for (String j : listArgs) {
                listsArray[i].push(j);
            }
        }
    }

    public DoubleLinkedListStack<String> getList(int listIndex) {
        return listsArray[listIndex];
    }
}