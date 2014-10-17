package jpegencoder;

class ImageException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Das einzulesende Bild enthält Fehler";
    }
}
