package exercise_one;

import java.io.IOException;
import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.file.image.PpmImage;
import exercise_one.logger.CoordinateLogger;
import exercise_one.logger.TimeLogger;

public class JpegEncoder
{
    private static final int PARAMETER_INDEX_IMAGE_PATH = 0;
    public static final int FILL_MODE_BLACK = 0;
    public static final int FILL_MODE_WHITE = 255;
    public static final int FILL_MODE_BORDER = 1;

    public static void main(String[] args)
    {
        try
        {
            TimeLogger timeLogger = new TimeLogger();
            CoordinateLogger coordinateLogger = new CoordinateLogger();

            timeLogger.start();

            PpmImage image = new PpmImage(args[PARAMETER_INDEX_IMAGE_PATH], 9, FILL_MODE_BORDER);
            image.convertToYCbCr();
            //pixel = new FilterReductionByMiddleValue().filter(pixel);
            coordinateLogger.log(image.getPixel(), image.getColormodel(), image.getWidth(), image.getHeight(), true);
            
            timeLogger.stop();
            timeLogger.log();
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
