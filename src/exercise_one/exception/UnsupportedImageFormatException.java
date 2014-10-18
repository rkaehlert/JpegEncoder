package exercise_one.exception;

public class UnsupportedImageFormatException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Das Bildformat wird nicht unterstuetzt";
    }
}
