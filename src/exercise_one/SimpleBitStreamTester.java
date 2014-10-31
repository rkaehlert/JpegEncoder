package exercise_one;

import java.io.FileOutputStream;
import java.io.IOException;

import exercise_one.file.stream.SimpleBitOutputStream;
import exercise_one.logger.LoggerTimer;

public class SimpleBitStreamTester {

    public static void main(String[] args) {
        try {
            SimpleBitOutputStream stream = new SimpleBitOutputStream(new FileOutputStream("C:\\Users\\xSmorpheusSx\\Desktop\\outputSingleByte"));
            LoggerTimer timer = new LoggerTimer();

            timer.start();
            for (long i = 0; i < 10000001; i++) {
                stream.writeBit(1);
            }
            timer.stop();
            stream.close();
            timer.log();
        }
        catch (IOException ex) {

        }
    }
}
