package main.exception.image;

public class UnsupportedImageFormatException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Das Bildformat wird nicht unterstuetzt";
    }
}
