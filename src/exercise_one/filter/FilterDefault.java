package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.converter.ConverterStringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;
import exercise_one.validator.ValidatorPixelCount;
import exercise_one.validator.ValidatorRowCount;

public class FilterDefault extends Filter<RGB> {
	
	@Override
	public TreeMap<Coordinate, RGB> filter(List<Character> lstCorrdinate) {
		TreeMap<Coordinate, RGB> returnValue = new TreeMap<Coordinate, RGB>();
		 if (super.isRedChannel())
         {
             super.setCurrentPixel(new RGB());
             super.getCurrentPixel().setRed(ConverterStringToInteger.convert(lstCorrdinate));
             super.setGreenChannel();
         }
         else if (super.isGreenChannel())
         {
        	 super.getCurrentPixel().setGreen(ConverterStringToInteger.convert(lstCorrdinate));
             super.setBlueChannel();
         }
         else if (super.isBlueChannel())
         {
        	 super.getCurrentPixel().setBlue(ConverterStringToInteger.convert(lstCorrdinate));
             super.setRedChannel();
     		 super.getDimension().increaseColumn();
        	 returnValue.put(new Coordinate(super.getDimension().getColumn(), super.getDimension().getRow()), super.getCurrentPixel());
         }
		 return returnValue;
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void validate(Image image) throws ImageException {
		new ValidatorPixelCount().validate(super.getDimension(), image);
		new ValidatorRowCount().validate(super.getDimension());
	}

}
