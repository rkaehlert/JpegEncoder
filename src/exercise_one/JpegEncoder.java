package exercise_one;

import java.io.IOException;

import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.filter.Filter;
import exercise_one.filter.FilterReductionByMiddleValue;
import exercise_one.logger.CoordinateLogger;

public class JpegEncoder
{
    public static void main(String[] args)
    {
        try
        {   
        	long start = System.currentTimeMillis();
        	Filter filter = new FilterReductionByMiddleValue();
            Image source = new Image("C:\\Users\\xSmorpheusSx\\Desktop\\1257989_1_DSC_0237-2.ppm", filter);
            new CoordinateLogger(source.getPixels(), filter.getDimension().getWidth(), filter.getDimension().getHeight()).log(true);
            //System.out.println(System.currentTimeMillis() - start);
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}