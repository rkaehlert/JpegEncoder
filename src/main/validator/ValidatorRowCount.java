package main.validator;

import main.exception.image.ImageException;
import main.model.matrix.Dimension;

public class ValidatorRowCount implements Validator {

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void validate(Dimension dimension) throws ImageException {
        if (dimension.getRow() < dimension.getHeight()){
            throw new ImageException("Die ermittelten Zeilen passen nicht zur Hoehe des Bildes");
        }
	}

}
