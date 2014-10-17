package exercise_one.filter;

import java.util.List;

import exercise_one.Image;
import exercise_one.converter.StringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.model.color.RGB;
import exercise_one.validator.ValidatorPixelCount;
import exercise_one.validator.ValidatorRowCount;

public class FilterDefault extends Filter<RGB> {

	@Override
	public RGB filter(List<Character> lstCorrdinate) {
		 if (super.isRedChannel())
         {
             super.setCurrentPixel(new RGB());
             super.getCurrentPixel().setRed(StringToInteger.convert(lstCorrdinate));
             super.setGreenChannel();
         }
         else if (super.isGreenChannel())
         {
        	 super.getCurrentPixel().setGreen(StringToInteger.convert(lstCorrdinate));
             super.setBlueChannel();
         }
         else if (super.isBlueChannel())
         {
        	 super.getCurrentPixel().setBlue(StringToInteger.convert(lstCorrdinate));
             super.setRedChannel();
             super.setReady(true);
         }
		 return super.getCurrentPixel();
	}

	@Override
	public void reset() {
		super.setCurrentPixel(new RGB());
		super.setReady(false);
	}

	@Override
	public void validate(Image image) throws ImageException {
		new ValidatorPixelCount().validate(super.getDimension(), image);
		new ValidatorRowCount().validate(super.getDimension());
	}

}
