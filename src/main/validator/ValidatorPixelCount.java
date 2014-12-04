package main.validator;

import main.datahandler.DataHandler;
import main.exception.common.ExceptionNotYetImplemented;
import main.exception.image.ImageException;
import main.file.image.Image;
import main.model.matrix.Dimension;

public class ValidatorPixelCount implements Validator {

	@Override
	public boolean validate(DataHandler datahandler) {
		try{
			throw new ExceptionNotYetImplemented();
		}catch(ExceptionNotYetImplemented e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validate(Dimension dimension, Image image) throws ImageException {
		if((dimension.getWidth() * dimension.getHeight()) != image.getPixel().size()){
        	throw new ImageException("Die ermittelte Groesse passt nicht zu den ausgewerteten Bildpunkten");
        }
		return true;
	}
	
}
