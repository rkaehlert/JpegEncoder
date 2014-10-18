package exercise_one;

import java.io.IOException;

import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.filter.Filter;
import exercise_one.filter.FilterReductionByM;
import exercise_one.logger.CoordinateLogger;

public class JpegEncoder
{
    public static void main(String[] args)
    {
        try
        {   
        	//long start = System.currentTimeMillis();
        	Filter filter = new FilterReductionByM();
            Image source = new Image("C:\\Users\\xSmorpheusSx\\Desktop\\16.ppm", filter);
            new CoordinateLogger(source.getPixels(), filter.getDimension().getWidth(), filter.getDimension().getHeight()).log(false);
            //System.out.println(System.currentTimeMillis() - start);
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}