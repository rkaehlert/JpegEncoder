package exception.huffmann;

import logger.LoggerError;

public class ExceptionInvalidTreeSize extends Exception {

	public ExceptionInvalidTreeSize(String message){
		super(message);
		LoggerError.log(message);
	}
}
