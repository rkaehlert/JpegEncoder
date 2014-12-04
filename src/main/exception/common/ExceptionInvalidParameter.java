package main.exception.common;

import main.logger.LoggerText;

public class ExceptionInvalidParameter extends RuntimeException {

	public ExceptionInvalidParameter() {
		super();
	}

	public ExceptionInvalidParameter(String message){
		super(message);
		LoggerText.log(message);
	}
	
}
