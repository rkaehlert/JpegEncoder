package exercise_one;

import java.io.IOException;
import java.util.TreeMap;

import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.file.image.PpmImage;
import exercise_one.logger.LoggerColormodel;
import exercise_one.logger.LoggerTimer;
import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

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
            LoggerTimer timeLogger = new LoggerTimer();
            LoggerColormodel coordinateLogger = new LoggerColormodel();
            TreeMap<Coordinate, Colormodel> filteredPixel;
            
            timeLogger.start();

            PpmImage image = new PpmImage(args[PARAMETER_INDEX_IMAGE_PATH], 8, FILL_MODE_BORDER);
           // image.convertToYCbCr();
           // filteredPixel = image.filter(new FilterReductionByMiddleValue());
            
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
