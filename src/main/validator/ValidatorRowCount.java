package main.validator;

import main.datahandler.DataHandler;
import main.exception.common.ExceptionNotYetImplemented;
import main.exception.image.ImageException;
import main.model.matrix.Dimension;

public class ValidatorRowCount implements Validator {

	@Override
	public boolean validate(DataHandler datahandler) {
		try{
			throw new ExceptionNotYetImplemented();
		}catch(ExceptionNotYetImplemented e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validate(Dimension dimension) throws ImageException {
        if (dimension.getRow() < dimension.getHeight()){
            throw new ImageException("Die ermittelten Zeilen passen nicht zur Hoehe des Bildes");
        }
        return true;
	}

}
