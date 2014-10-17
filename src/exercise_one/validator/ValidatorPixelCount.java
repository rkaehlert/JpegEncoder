package exercise_one.validator;

import exercise_one.Image;
import exercise_one.exception.ImageException;
import exercise_one.model.matrix.Dimension;

public class ValidatorPixelCount implements Validator {

	@Override
	public void validate() throws Exception {
		
	}
	
	public void validate(Dimension dimension, Image image) throws ImageException {
		if((dimension.getWidth() * dimension.getHeight()) != image.getPixels().size()){
        	throw new ImageException("Die ermittelte Größe passt nicht zu den ausgewerteten Bildpunkten");
        }
	}
	
}
