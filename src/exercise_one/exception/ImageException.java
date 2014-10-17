package exercise_one.exception;

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
    	return super.getMessage() == null ? "Das einzulesende Bild enthält Fehler" : super.getMessage(); 
    }
}
