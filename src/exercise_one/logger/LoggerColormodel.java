package exercise_one.logger;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class LoggerColormodel implements Logger
{
	
	LoggerRGB loggerRGB = new LoggerRGB();
	LoggerYCbCr loggerYCbCr = new LoggerYCbCr();

    public void log(TreeMap<Coordinate, Colormodel> pixel, String colormodel, int width, int height, boolean isLoggingEnabled)
    {
        System.out.println("----------------------------------------------------------");
        System.out.println("Farbmodell: " + colormodel);
        System.out.println("Anzahl Eintraege: " + pixel.size());
        System.out.println("Anzahl Zeilen: " + height);
        System.out.println("Anzahl Spalten: " + width);
        System.out.println("----------------------------------------------------------");
        if (isLoggingEnabled)
        {
            int last_y = 0;
            for (Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet())
            {
                if (entry.getKey().getY() > last_y)
                {
                    System.out.print("\n");
                    last_y = 0;
                }
                last_y = entry.getKey().getY();
                if (entry.getValue() instanceof RGB)
                {
                	loggerRGB.log((RGB)entry.getValue());
                }
                else if (entry.getValue() instanceof YCbCr)
                {
                	loggerYCbCr.log((YCbCr)entry.getValue());
            		
                }
                else
                {
                    System.out.print("X");
                }
            }
        }
    }

    @Override
    public void log()
    {
        try
        {
            throw new NotYetImplementedException();
        }
        catch (NotYetImplementedException e)
        {
            e.printStackTrace();
        }
    }

}