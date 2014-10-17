package exercise_one;

import java.io.IOException;

import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.filter.Filter;
import exercise_one.filter.FilterDefault;
import exercise_one.logger.CoordinateLogger;

public class JpegEncoder
{
    public static void main(String[] args)
    {
        try
        {
        	Filter filter = new FilterDefault();
            Image source = new Image("C:\\Users\\xSmorpheusSx\\Desktop\\16.ppm", filter);
            new CoordinateLogger(source.getPixels(), filter.getDimension().getWidth(), filter.getDimension().getHeight()).log();
            //System.out.println(source);
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}