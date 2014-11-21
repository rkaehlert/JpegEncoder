package exception.image;

public class ImageException extends Exception
{
	public ImageException(String message){
		super(message); 
	}
	
	public ImageException(){
		
	}
	
    @Override
    public String getMessage()
    {
    	return super.getMessage() == null ? "Das einzulesende Bild enthaelt Fehler" : super.getMessage(); 
    }
}
