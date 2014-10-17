package exercise_one.filter;

import java.util.List;

import exercise_one.Image;
import exercise_one.converter.StringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.model.color.RGB;
import exercise_one.validator.ValidatorPixelCount;
import exercise_one.validator.ValidatorRowCount;

public class FilterReductionByM extends Filter<RGB> {
	
	private static final int OFFSET_ROW = 1;
	private static final int OFFSET_COL = 1;

	private boolean validRow = false;
	private boolean validCol = false;
	
	@Override
	public RGB filter(List<Character> lstCorrdinate) {
		this.setValidRow();
		this.setValidCol();
		if(this.validRow){
			if(this.validCol){
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
		             super.getDimension().increaseColumn();
		         }
				 return super.getCurrentPixel();
			}
		}
		return null;
	}
	
	private void setValidRow(){
		this.validRow = (super.getDimension().getRow()%OFFSET_ROW) == 0;
	}
	
	private void setValidCol(){
		this.validCol = (super.getDimension().getColumn()%OFFSET_COL) == 0;
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
