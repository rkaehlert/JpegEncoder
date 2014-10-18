package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.converter.ConverterStringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByM extends Filter<RGB> {
	
	private static final int OFFSET_ROW = 1;
	private static final int OFFSET_COL = 3;

	private boolean validRow = false;
	private boolean validCol = false;
	
	@Override
	public TreeMap<Coordinate, RGB> filter(List<Character> lstCorrdinate) {
		TreeMap<Coordinate, RGB> returnValue = new TreeMap<Coordinate, RGB>();
		this.setValidRow();
		this.setValidCol();
		if(this.validRow){
			if(this.validCol){
				 if (super.isRedChannel()){
		             super.setCurrentPixel(new RGB());
		             super.getCurrentPixel().setRed(ConverterStringToInteger.convert(lstCorrdinate));
		         }else if (super.isGreenChannel()){
		        	 super.getCurrentPixel().setGreen(ConverterStringToInteger.convert(lstCorrdinate));
		         }else if (super.isBlueChannel()){
		        	 super.getCurrentPixel().setBlue(ConverterStringToInteger.convert(lstCorrdinate));
					 returnValue.put(new Coordinate(super.getDimension().getColumn(), super.getDimension().getRow()), super.getCurrentPixel());
					 super.getDimension().increaseColumn();
		         }
			}
		}
		if(super.isBlueChannel()){
			super.getDimension().increaseColumn();
		}
		super.switchChannel();
		return returnValue;
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
//		new ValidatorPixelCount().validate(super.getDimension(), image);
//		new ValidatorRowCount().validate(super.getDimension());
	}

	
	
}
