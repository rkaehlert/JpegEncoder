package main.exception.huffmann;

import main.logger.LoggerError;

public class ExceptionInvalidTreeSize extends Exception {

	public ExceptionInvalidTreeSize(String message){
		super(message);
		LoggerError.log(message);
	}
}
