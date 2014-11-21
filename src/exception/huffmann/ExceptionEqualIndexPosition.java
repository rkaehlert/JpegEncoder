package exception.huffmann;

import logger.LoggerError;

public class ExceptionEqualIndexPosition extends Exception {

	public ExceptionEqualIndexPosition(String message){
		super(message);
		LoggerError.log(message);
	}
	
}
