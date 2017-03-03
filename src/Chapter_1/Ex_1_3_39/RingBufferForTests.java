package Chapter_1.Ex_1_3_39;

/**
 * Created by vl on 07.02.17.
 */
public class RingBufferForTests {

    private RingBuffer<String>[] ringBuffersArray;

    @SuppressWarnings("unchecked")
    public RingBufferForTests(int arraySize, String[] bufferArgs) {
        ringBuffersArray = new RingBuffer[arraySize];

        for (int i = 0; i < ringBuffersArray.length; i++) {
            ringBuffersArray[i] = new RingBuffer<>(10);

            for (String arg : bufferArgs) {
                ringBuffersArray[i].enqueue(arg);
            }
        }
    }

    public RingBuffer<String> getBuffer(int bufferIndex) {
        return ringBuffersArray[bufferIndex];
    }
}