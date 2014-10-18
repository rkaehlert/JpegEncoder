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
        	long start = System.currentTimeMillis();
        	Filter filter = new FilterDefault();
            Image source = new Image("G:\\Studium\\6. Semester\\MI2\\ppm_bild.ppm", filter);
            new CoordinateLogger(source.getPixels(), filter.getDimension().getWidth(), filter.getDimension().getHeight()).log(true);
            //System.out.println(System.currentTimeMillis() - start);
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}