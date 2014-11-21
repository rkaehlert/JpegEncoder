package main;



import java.io.FileOutputStream;
import java.io.IOException;

import main.file.stream.SimpleBitOutputStream;
import main.logger.LoggerTimer;

public class SimpleBitStreamTester {

    public static void main(String[] args) {
        try {
            SimpleBitOutputStream stream = new SimpleBitOutputStream(new FileOutputStream("C:\\Users\\robin\\Desktop\\outputSingleByte.jpg"));
            LoggerTimer timer = new LoggerTimer();

            timer.start();
            for (long i = 0; i < 10000; i++) {
                stream.writeInt(128);
            }
            timer.stop();
            stream.close();
            timer.log();
        }
        catch (IOException ex) {

        }
    }
}
