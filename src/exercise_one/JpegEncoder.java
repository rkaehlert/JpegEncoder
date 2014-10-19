package exercise_one;

import java.io.IOException;
import java.util.TreeMap;

import exercise_one.converter.ConverterRGBToYCbCr;
import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.file.reader.FileReaderPPM;
import exercise_one.filter.FilterReductionByMiddleValue;
import exercise_one.logger.CoordinateLogger;
import exercise_one.logger.TimeLogger;
import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

public class JpegEncoder{
	
	private static final int PARAMETER_INDEX_IMAGE_PATH = 0;
	
    public static void main(String[] args)
    {
        try{   
        	TimeLogger timeLogger = new TimeLogger();
        	CoordinateLogger coordinateLogger = new CoordinateLogger();
        	
        	timeLogger.start();
        	
            FileReaderPPM source = new FileReaderPPM();
            
            TreeMap<Coordinate, Colormodel> pixel = source.read(args[PARAMETER_INDEX_IMAGE_PATH]);

            pixel = ConverterRGBToYCbCr.convert(pixel);
            
        	pixel = new FilterReductionByMiddleValue().filter(pixel);
            
            coordinateLogger.log(pixel, source.getWidth(), source.getHeight(), true);
            
            timeLogger.stop();
            timeLogger.log();
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}