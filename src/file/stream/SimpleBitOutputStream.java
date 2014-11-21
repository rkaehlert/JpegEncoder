package file.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public final class SimpleBitOutputStream {

    private static final int BUFFER_SIZE = 100000000;
    private OutputStream output;
    private byte currentByte;
    private byte[] byteBuffer;

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
        byteBuffer = new byte[BUFFER_SIZE];
    }

    public void write(byte b, int offset) throws IOException {

        currentByte = (byte) (currentByte << offset | b);
        currentBitsInCurrentByte += offset;
        if (currentBitsInCurrentByte == 8) {
            byteBuffer[currentBytesInBuffer] = currentByte;
            currentBytesInBuffer++;
            currentBitsInCurrentByte = 0;
            currentByte = 0;

            if (currentBytesInBuffer == BUFFER_SIZE) {
                for (int i = 0; i < byteBuffer.length; i++) {
                    output.write(byteBuffer[i]);
                }
                byteBuffer = new byte[BUFFER_SIZE];
                currentBytesInBuffer = 0;
            }
        }
    }

    public void writeBit(int b) throws IOException {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        write((byte) b, 1);
    }

    public void writeByte(byte b) throws IOException {
        write(b, 8);
    }

    public void writeByteArray(byte[] ba) throws IOException {
        for (int i = 0; i < ba.length; i++) {
            writeByte(ba[i]);
        }
    }

    public void writeInt(int zahl) throws IOException {
        byte[] conv = BigInteger.valueOf(zahl).toByteArray();
        for (int i = 0; i < conv.length; i++) {
            if (conv[i] != (byte)0x00 || i > 0) {
                writeByte(conv[i]);
            }
        }
    }

    public void close() throws IOException {
        if (currentBytesInBuffer < BUFFER_SIZE - 1) {
            for (int i = 0; i < currentBytesInBuffer; i++) {
                output.write(byteBuffer[i]);
            }
        }
        if (currentBitsInCurrentByte != 0) {
            writeBit(byteBuffer[byteBuffer.length - 1]);
            while (currentBitsInCurrentByte != 0) {
                writeBit((byte) 0);
            }
            output.write(byteBuffer[currentBytesInBuffer - 1]);
        }
        output.close();

    }
}
