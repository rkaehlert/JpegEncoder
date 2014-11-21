package main.exception.huffmann;

import main.logger.LoggerError;

public class ExceptionEqualIndexPosition extends Exception {

	public ExceptionEqualIndexPosition(String message){
		super(message);
		LoggerError.log(message);
	}
	
}
