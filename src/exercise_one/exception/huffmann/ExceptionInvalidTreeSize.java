package exercise_one.exception.huffmann;

import exercise_one.logger.LoggerError;

public class ExceptionInvalidTreeSize extends Exception {

	public ExceptionInvalidTreeSize(String message){
		super(message);
		LoggerError.log(message);
	}
}
