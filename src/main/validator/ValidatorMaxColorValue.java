package main.validator;

import main.datahandler.DataHandler;
import main.exception.common.NotYetImplementedException;
import main.exception.image.ImageException;

public class ValidatorMaxColorValue implements Validator {

	@Override
	public boolean validate(DataHandler datahandler) {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return false;
	} 
	
	public boolean validate(Integer maxColorValue) throws ImageException {
		if(!(maxColorValue > 0 && maxColorValue < 65536)){
    		throw new ImageException("der maximale farbwert darf nicht groesser als 65536 sein");	
    	}
		return true;
	}

}
