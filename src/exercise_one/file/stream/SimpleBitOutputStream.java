package exercise_one.file.stream;

import java.io.IOException;
import java.io.OutputStream;

public final class SimpleBitOutputStream {

    private static final int BUFFER_SIZE = 12500001;
    private OutputStream output;
    private int currentByte;
    private int[] byteBuffer;

    private int currentBitsInCurrentByte;
    private int currentBytesInBuffer;

    public SimpleBitOutputStream(OutputStream out) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        output = out;
        currentByte = 0;
        currentBitsInCurrentByte = 0;
        currentBytesInBuffer = 0;
        byteBuffer = new int[BUFFER_SIZE];
    }

    public void write(int b) throws IOException {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        currentByte = currentByte << 1 | b;
        currentBitsInCurrentByte++;
        if (currentBitsInCurrentByte == 8) {
            byteBuffer[currentBytesInBuffer] = currentByte;
            currentBytesInBuffer++;
            currentBitsInCurrentByte = 0;
            currentByte = 0;

            if (currentBytesInBuffer == BUFFER_SIZE) {
                for (int i = 0; i < byteBuffer.length; i++) {
                    output.write(byteBuffer[i]);
                }
                byteBuffer = new int[BUFFER_SIZE];
                currentBytesInBuffer = 0;
            }
        }
    }

    public void close() throws IOException {
    	if(currentBytesInBuffer < BUFFER_SIZE-1){
            for (int i = 0; i < currentBytesInBuffer; i++) {
            	output.write(byteBuffer[i]);
            }
    	}
    	if(currentBitsInCurrentByte != 0){
    		write(byteBuffer[byteBuffer.length-1]);
        	while (currentBitsInCurrentByte != 0) {
	            write(0);
	        }
	        output.write(byteBuffer[currentBytesInBuffer-1]);
    	}
        output.close();
    	
    }
}
