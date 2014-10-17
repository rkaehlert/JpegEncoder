package jpegencoder;

class UnsupportedImageFormatException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Das Bildformat wird nicht unterstützt";
    }
}
