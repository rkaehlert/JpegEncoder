package exercise_one;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import exercise_one.converter.ConverterStringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.filter.Filter;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;
import exercise_one.validator.ValidatorMaxColorValue;

public class Image implements Cloneable
{
	private static final int ASCII_LINE_FEED = 10;
	
    private int maxColorValue;
    private TreeMap<Coordinate, Colormodel> pixels;
    private Filter filter;
    
    public Image()
    {
        maxColorValue = 0;
//      farbraum = 0;
        pixels = new TreeMap<>();
    }

    public Image(String src, Filter<RGB> filter) throws UnsupportedImageFormatException, ImageException, IOException
    {
        this();
        this.filter = filter;
        File imageFile = new File(src);
        FileReader fr = null;

        try
        {
            fr = new FileReader(imageFile);

            if ((char) fr.read() == 'P' && (char) fr.read() == '3')
            {
            	boolean isLineBreak = false;
                int zeichen;
                int valueCounter = 0;

                ArrayList<Character> charList = new ArrayList<>();

                while ((zeichen = fr.read()) != -1)
                {
                    if ((char) zeichen >= '0' && (char) zeichen <= '9')
                    {
                        charList.add((char) zeichen);
                    }
                    else if((char) zeichen == '#'){
                    	while(!isLineBreak){
                        	zeichen = fr.read();
                        	isLineBreak = zeichen == ASCII_LINE_FEED;
                    	}
                    }
                    else
                    {
                        if (!charList.isEmpty())
                        {
                            if (valueCounter == 0)
                            {
                                filter.getDimension().setWidth(ConverterStringToInteger.convert(charList));
                            }
                            else if (valueCounter == 1)
                            {
                            	filter.getDimension().setHeight(ConverterStringToInteger.convert(charList));
                                //pixels = new int[height][width][3];
                            }
                            else if (valueCounter == 2)
                            {
                        		maxColorValue = ConverterStringToInteger.convert(charList);
                            	new ValidatorMaxColorValue().validate(maxColorValue);
                            }
                            else
                            {
                                //pixels[rowCounter][columnCounter][currentChannel] = getIntRepresentation(charList);
                            	TreeMap<Coordinate, RGB> filteredPixels = filter.filter(charList);
                            	if(filteredPixels != null){
                            		if(filteredPixels.size() > 0){
                            			this.pixels.putAll(filteredPixels);
                            		}
                            	}
                            	if (filter.getDimension().getColumn() == filter.getDimension().getWidth())
                                {
                                    filter.getDimension().increaseRow();
                                    filter.getDimension().resetColumn();
                                }
                            }
                            valueCounter++;
                            charList.clear();
                        }
                    }
                }
                filter.validate(this);
            }
            else
            {
                throw new UnsupportedImageFormatException();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Fehler beim Einlesen des Bildes.");
            throw ex;
        }
        finally
        {
            try
            {
                if (fr != null)
                {
                    fr.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Fehler beim Schliessen des Streams.");
                throw e;
            }
        }
    }
    
    public TreeMap<Coordinate, Colormodel> getPixels()
    {
        return pixels;
    }

    public void setPixels(TreeMap<Coordinate, Colormodel> pixel)
    {
        this.pixels = pixel;
    }

    public String getColormodel()
    {
        if (pixels.containsKey(new Coordinate(0, 0)))
        {
            return pixels.get(new Coordinate(0, 0)).getClass().getName();
        }
        else
        {
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "Bild [farbmodell = " + getColormodel() + ", breite = " + filter.getDimension().getWidth() + ", laenge = " + filter.getDimension().getHeight() + ", farbraum = "
                + maxColorValue + ", pixel = " + pixels.toString() + "]";
    }

/*    private Image transformRGBtoYCbCr()
    {
        Image neuBild = this.clone();
        for (Coordinate k : this.getPixels().keySet())
        {
            RGB rgb = (RGB) this.getPixels().get(k);
            YCbCr ycbcr = new YCbCr();
            ycbcr.setY(0 + ((0.299 * rgb.getRed()) + (0.587 * rgb.getGreen()) + (0.114 * rgb.getBlue())));
            ycbcr.setCb(0.5 + ((-0.1687 * rgb.getRed()) + (-0.3312 * rgb.getGreen()) + (0.5 * rgb.getBlue())));
            ycbcr.setCr(0.5 + ((0.5 * rgb.getRed()) + (-0.4186 * rgb.getGreen()) + (-0.0813 * rgb.getBlue())));
            neuBild.getPixels().put(k, ycbcr);
        }
        return neuBild;
    }

    public Image clone()
    {
        Image bild = new Image(this.filter);
        bild.setWidth(this.filter.getDimension().getWidth());
        bild.setHeight(this.filter.getDimension().getHeight());
        //bild.setFarbraum(this.farbraum);
        bild.setPixels((TreeMap<Coordinate, Colormodel>) this.pixels.clone());
        return bild;
    }*/
}
