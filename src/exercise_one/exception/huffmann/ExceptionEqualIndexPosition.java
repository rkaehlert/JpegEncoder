package exercise_one.exception.huffmann;

import exercise_one.logger.LoggerError;

public class ExceptionEqualIndexPosition extends Exception {

	public ExceptionEqualIndexPosition(String message){
		super(message);
		LoggerError.log(message);
	}
	
}
