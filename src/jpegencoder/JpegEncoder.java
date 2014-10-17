package jpegencoder;

import java.io.IOException;

public class JpegEncoder
{
    public static void main(String[] args)
    {
        try
        {
            Image source = new Image("G:\\Studium\\6. Semester\\MI2\\ppm_bild.ppm");
            System.out.println(source);
        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}