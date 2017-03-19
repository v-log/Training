package Chapter_1.Ex_1_3_44;

/**
 * Created by vl on 15.02.17.
 */
public class BufferArrayForTests {
    private TextEditorBuffer[] buffersArray;

    public BufferArrayForTests(int arraySize, char[] bufferArgs) {
        buffersArray = new TextEditorBuffer[arraySize];

        for (int i = 0; i < buffersArray.length; i++) {
            buffersArray[i] = new TextEditorBuffer();

            for (char arg : bufferArgs) {
                buffersArray[i].insert(arg);
            }
        }
    }

    public TextEditorBuffer getBuffer(int bufferIndex) {
        return buffersArray[bufferIndex];
    }
}