package main.validator;

import main.exception.image.ImageException;

public class ValidatorMaxColorValue implements Validator {

	@Override
	public void validate() throws ImageException {

	} 
	
	public void validate(Integer maxColorValue) throws ImageException {
		if(!(maxColorValue > 0 && maxColorValue < 65536)){
    		throw new ImageException("der maximale farbwert darf nicht groesser als 65536 sein");	
    	}
	}

}
