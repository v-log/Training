package Algorithms_Chapter_1.Ex_1_3_38;

/**
 * Created by vl on 04.12.16.
 */
public class QueueAsArrayForTests {
    private GeneralizedQueueAsArray<String>[] queuesArray;

    @SuppressWarnings("unchecked")
    public QueueAsArrayForTests(int arraySize) {
        queuesArray = new GeneralizedQueueAsArray[arraySize];

        for (int i = 0; i < queuesArray.length; i++) {
            queuesArray[i] = new GeneralizedQueueAsArray<>();

            String[] queueArgs = {"one", "two", "three", "four", "five"};

            for (String arg : queueArgs) {
                queuesArray[i].insert(arg);
            }
        }
    }

    public GeneralizedQueueAsArray<String> getQueue(int queueIndex) {
        return queuesArray[queueIndex];
    }
}