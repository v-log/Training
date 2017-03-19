package Algorithms_Chapter_1.Ex_1_3_38;

/**
 * Created by vl on 11.01.17.
 */
public class QueueAsListAsArrayForTests {
    private GeneralizedQueueAsList<String>[] queuesArray;

    @SuppressWarnings("unchecked")
    public QueueAsListAsArrayForTests(int arraySize) {
        queuesArray = new GeneralizedQueueAsList[arraySize];

        for (int i = 0; i < queuesArray.length; i++) {
            queuesArray[i] = new GeneralizedQueueAsList<>();

            String[] queueArgs = {"one", "two", "three", "four", "five"};

            for (String arg : queueArgs) {
                queuesArray[i].insert(arg);
            }
        }
    }

    public GeneralizedQueueAsList<String> getQueue(int queueIndex) {
        return queuesArray[queueIndex];
    }
}