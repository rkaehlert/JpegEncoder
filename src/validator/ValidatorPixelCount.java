package validator;

import exception.image.ImageException;
import file.image.Image;
import model.matrix.Dimension;

public class ValidatorPixelCount implements Validator {

	@Override
	public void validate() throws Exception {
		
	}
	
	public void validate(Dimension dimension, Image image) throws ImageException {
		if((dimension.getWidth() * dimension.getHeight()) != image.getPixel().size()){
        	throw new ImageException("Die ermittelte Groesse passt nicht zu den ausgewerteten Bildpunkten");
        }
	}
	
}
