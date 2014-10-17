package jpegencoder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Image implements Cloneable
{

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private int width;
    private int height;
    private int maxColorValue;
//  private int farbraum;
//  private int[][][] pixels;
    private TreeMap<Coordinate, Colormodel> pixels;

    public Image()
    {
        width = 0;
        height = 0;
        maxColorValue = 0;
//      farbraum = 0;
        pixels = new TreeMap<>();
    }

    public Image(String src) throws UnsupportedImageFormatException, ImageException, IOException
    {
        this();
        File imageFile = new File(src);
        FileReader fr = null;

        try
        {
            fr = new FileReader(imageFile);

            if ((char) fr.read() == 'P' && (char) fr.read() == '3')
            {
                int zeichen;
                int valueCounter = 0;
                int rowCounter = 0;
                int columnCounter = 0;
                int currentChannel = RED;
                RGB currentPixel = new RGB();

                ArrayList<Character> charList = new ArrayList<>();

                while ((zeichen = fr.read()) != -1)
                {
                    if ((char) zeichen >= '0' && (char) zeichen <= '9')
                    {
                        charList.add((char) zeichen);
                    }
                    else
                    {
                        if (!charList.isEmpty())
                        {
                            if (valueCounter == 0)
                            {
                                width = getIntRepresentation(charList);
                            }
                            else if (valueCounter == 1)
                            {
                                height = getIntRepresentation(charList);
                                //pixels = new int[height][width][3];
                            }
                            else if (valueCounter == 2)
                            {
                                maxColorValue = getIntRepresentation(charList);
                            }
                            else
                            {
                                //pixels[rowCounter][columnCounter][currentChannel] = getIntRepresentation(charList);

                                if (currentChannel == RED)
                                {
                                    currentPixel = new RGB();
                                    currentPixel.setRed(getIntRepresentation(charList));
                                    currentChannel = GREEN;
                                }
                                else if (currentChannel == GREEN)
                                {
                                    currentPixel.setGreen(getIntRepresentation(charList));
                                    currentChannel = BLUE;
                                }
                                else if (currentChannel == BLUE)
                                {
                                    currentPixel.setBlue(getIntRepresentation(charList));
                                    pixels.put(new Coordinate(columnCounter, rowCounter), currentPixel);
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
                if (rowCounter < height)
                {
                    throw new ImageException();
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
                System.out.println("Fehler beim Schließen des Streams.");
                throw e;
            }
        }
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
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
        return "Bild [farbmodell = " + getColormodel() + ", breite = " + width + ", laenge = " + height + ", farbraum = "
                + maxColorValue + ", pixel = " + pixels.toString() + "]";
    }

    private Image transformRGBtoYCbCr()
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
        Image bild = new Image();
        bild.setWidth(this.width);
        bild.setHeight(this.height);
        //bild.setFarbraum(this.farbraum);
        bild.setPixels((TreeMap<Coordinate, Colormodel>) this.pixels.clone());
        return bild;
    }

    private int getIntRepresentation(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list)
        {
            builder.append(ch);
        }
        return Integer.parseInt(builder.toString());
    }
}
