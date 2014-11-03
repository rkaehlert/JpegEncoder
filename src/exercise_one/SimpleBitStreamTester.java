package exercise_one;

import java.io.FileOutputStream;
import java.io.IOException;

import exercise_one.file.stream.SimpleBitOutputStream;
import exercise_one.logger.LoggerTimer;

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
