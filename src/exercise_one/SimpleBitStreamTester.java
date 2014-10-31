package exercise_one;

import exercise_one.file.stream.SimpleBitOutputStream;
import exercise_one.logger.LoggerTimer;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleBitStreamTester {

    public static void main(String[] args) {
        try {
            SimpleBitOutputStream stream = new SimpleBitOutputStream(new FileOutputStream("C:\\Users\\robin\\Desktop\\outputSingleByte"));
            LoggerTimer timer = new LoggerTimer();
            
            timer.start();
            for(long i=0;i<=10000000;i++)
            {
                stream.write(1);
            }
            timer.stop();
            timer.log();
            
            stream.close();
        }
        catch (IOException ex) {

        }
    }
}
