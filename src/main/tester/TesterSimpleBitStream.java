package main.tester;



import java.io.File;
import java.io.IOException;

import main.file.stream.SimpleBitWriter;

public class TesterSimpleBitStream {

    public static void main(String[] args) {
        try {
            SimpleBitWriter stream = new SimpleBitWriter(new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputSingleByte.jpg"));
            stream.write(100);
            stream.writeBit(1);
            stream.writeBit(1);
            stream.writeBit(0);
            stream.writeBit(0);
            stream.writeBit(1);
            stream.writeBit(0);
            stream.writeBit(0);
            stream.writeFillBits();
            stream.writeBit(0);
            stream.writeBit(0);
            stream.writeBit(0);
            stream.writeBit(0);
            stream.writeBit(1);
            stream.writeBit(1);
            stream.write(100);
            stream.close();
//            LoggerTimer timer = new LoggerTimer();

//            timer.start();
//            for (long i = 0; i < 10000; i++) {
//                stream.writeInt(128);
//            }
//            timer.stop();
//            stream.close();
//            timer.log();
        }
        catch (IOException ex) {

        }
    }
}
