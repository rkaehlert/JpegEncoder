package exercise_one.file.stream;

import java.io.IOException;
import java.io.OutputStream;

public final class SimpleBitOutputStream {

    private static final int BUFFER_SIZE = 1000000;
    private OutputStream output;
    private int currentByte;
    private int[] byteBuffer;

    private int numBitsInCurrentByte;
    private int numBytesInBuffer;

    public SimpleBitOutputStream(OutputStream out) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        output = out;
        currentByte = 0;
        numBitsInCurrentByte = 0;
        numBytesInBuffer = 0;
        byteBuffer = new int[BUFFER_SIZE];
    }

    public void write(int b) throws IOException {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        currentByte = currentByte << 1 | b;
        numBitsInCurrentByte++;
        if (numBitsInCurrentByte == 8) {
            byteBuffer[numBytesInBuffer] = currentByte;
            numBytesInBuffer++;
            numBitsInCurrentByte = 0;
            currentByte = 0;

            if (numBytesInBuffer == BUFFER_SIZE) {
                for (int i = 0; i < byteBuffer.length; i++) {
                    output.write(byteBuffer[i]);
                }
                byteBuffer = new int[BUFFER_SIZE];
                numBytesInBuffer = 0;
            }
        }
    }

    public void close() throws IOException {
        while (numBitsInCurrentByte != 0) {
            write(0);
        }
        output.close();
    }
}
