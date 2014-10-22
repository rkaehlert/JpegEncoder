package exercise_one.file.image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exercise_one.JpegEncoder;
import exercise_one.converter.ConverterRGBToYCbCr;
import exercise_one.converter.ConverterStringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.exception.UnsupportedImageFormatException;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;

public class PpmImage extends Image implements Cloneable
{
    private static final int ASCII_LINE_FEED = 10;

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public PpmImage()
    {
        width = 0;
        height = 0;
        maxColorValue = 0;
    }

    public PpmImage(String src, int schrittweite, int fillmode) throws UnsupportedImageFormatException, ImageException, IOException
    {
        this();
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
                int rowCounter = 0;
                int columnCounter = 0;
                int currentChannel = RED;
                int randRechts = 0;
                int randUnten = 0;
                RGB currentPixel = new RGB();

                ArrayList<Character> charList = new ArrayList<>();

                while ((zeichen = fr.read()) != -1)
                {
                    if ((char) zeichen >= '0' && (char) zeichen <= '9')
                    {
                        charList.add((char) zeichen);
                    }
                    else if ((char) zeichen == '#')
                    {
                        while (!isLineBreak)
                        {
                            zeichen = fr.read();
                            //TODO was wenn anderes OS? LINE_FEED auch 10?
                            isLineBreak = zeichen == ASCII_LINE_FEED;
                        }
                    }
                    else
                    {
                        if (!charList.isEmpty())
                        {
                            if (valueCounter == 0)
                            {
                                width = ConverterStringToInteger.convert(charList);
                            }
                            else if (valueCounter == 1)
                            {
                                height = ConverterStringToInteger.convert(charList);
                            }
                            else if (valueCounter == 2)
                            {
                                maxColorValue = ConverterStringToInteger.convert(charList);
                                if (!(maxColorValue > 0 && maxColorValue < 65536))
                                {
                                    throw new ImageException("der maximale farbwert darf nicht groesser als 65536 sein");
                                }
                            }
                            else
                            {
                                if (currentChannel == RED)
                                {
                                    currentPixel = new RGB();
                                    currentPixel.setRed(ConverterStringToInteger.convert(charList));
                                    currentChannel = GREEN;
                                }
                                else if (currentChannel == GREEN)
                                {
                                    currentPixel.setGreen(ConverterStringToInteger.convert(charList));
                                    currentChannel = BLUE;
                                }
                                else if (currentChannel == BLUE)
                                {
                                    currentPixel.setBlue(ConverterStringToInteger.convert(charList));
                                    pixel.put(new Coordinate(columnCounter, rowCounter), currentPixel);
                                    currentChannel = RED;
                                    columnCounter++;

                                    if (columnCounter == width)
                                    {
                                        rowCounter++;
                                        columnCounter = 0;
                                    }
                                }
                            }
                            valueCounter++;
                            charList.clear();
                        }
                    }
                }
//                if ((width * height) != super.getPixel().size())
//                {
//                    throw new ImageException("Unzureichende Anzahl an Spalten");
//                }
//                if (rowCounter < height)
//                {
//                    throw new ImageException();
//                }

                columnCounter = 0;
                rowCounter = 0;
                RGB bottomRightPixel = (RGB) pixel.lastEntry().getValue();

                if (0 <= (randRechts = schrittweite - (width % schrittweite)) && (width % schrittweite) != 0)
                {
                    while (columnCounter < randRechts)
                    {
                        for (int i = 0; i < height; i++)
                        {
                            if (fillmode == JpegEncoder.FILL_MODE_BORDER)
                            {
                                pixel.put(new Coordinate(height + columnCounter, i), pixel.get(new Coordinate(width - 1, i)));
                            }
                            else
                            {
                                pixel.put(new Coordinate(height + columnCounter, i), new RGB(fillmode, fillmode, fillmode));
                            }
                        }
                        columnCounter++;
                    }
                }
                if (0 <= (randUnten = schrittweite - (height % schrittweite)) && (height % schrittweite) != 0)
                {
                    while (rowCounter < randUnten)
                    {
                        for (int i = 0; i < width; i++)
                        {
                            if (i < width)
                            {
                                if (fillmode == JpegEncoder.FILL_MODE_BORDER)
                                {
                                    pixel.put(new Coordinate(i, width + rowCounter), pixel.get(new Coordinate(i, height - 1)));
                                }
                                else
                                {
                                    pixel.put(new Coordinate(i, width + rowCounter), new RGB(fillmode, fillmode, fillmode));
                                }
                            }
                            else
                            {
                                if (fillmode == JpegEncoder.FILL_MODE_BORDER)
                                {
                                    pixel.put(new Coordinate(i, width + rowCounter), bottomRightPixel);
                                }
                                else
                                {
                                    pixel.put(new Coordinate(i, width + rowCounter), new RGB(fillmode, fillmode, fillmode));
                                }

                            }
                        }
                        rowCounter++;
                    }
                    height += randUnten;
                    width += randRechts;
                }
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

    public void convertToYCbCr()
    {
        pixel = ConverterRGBToYCbCr.convert(pixel);
    }

    public String getColormodel()
    {
        String colormodel = pixel.get(new Coordinate(0, 0)).getClass().getName();
        return colormodel.substring(colormodel.lastIndexOf(".") + 1, colormodel.length());
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    /*  
     public void switchChannel(){
     if (this.isRedChannel()){
     this.setGreenChannel();
     }
     else if (this.isGreenChannel()){
     this.setBlueChannel();
     }
     else if (this.isBlueChannel()){
     this.setRedChannel();
     }
     }
  
     public void setGreenChannel() {
     this.currentChannel = GREEN;
     }
     public void setRedChannel(){
     this.currentChannel = RED;
     }
	
     public void setBlueChannel(){
     this.currentChannel = BLUE;
     }
	
     public boolean isBlueChannel(){
     return this.currentChannel == BLUE;
     }
	
     public boolean isRedChannel(){
     return this.currentChannel == RED;
     }
	
     public boolean isGreenChannel(){
     return this.currentChannel == GREEN;
     }*/
    /*    public String getColormodel()
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
     }*/

    /*    private Image transformRGBtoYCbCr()
     {
        
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
