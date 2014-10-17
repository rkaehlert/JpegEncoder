package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.converter.StringToInteger;
import exercise_one.exception.ImageException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;
import exercise_one.validator.ValidatorPixelCount;
import exercise_one.validator.ValidatorRowCount;

public class FilterReductionByMiddleValue extends Filter<RGB> {
	
	private static final int OFFSET_ROW = 2;
	private static final int OFFSET_COL = 2;

    private TreeMap<Coordinate, Colormodel> pixels;
	
	private boolean validRow = false;
	private boolean validCol = false;
	
	private int average_red = 0;
	private int average_green = 0;
	private int average_blue = 0;
	
	@Override
	public RGB filter(List<Character> lstCorrdinate) {
		this.setValidRow();
		this.setValidCol();
		int value = StringToInteger.convert(lstCorrdinate);
     	switch(super.getCurrentChannel()){
        	case RED:  	super.setGreenChannel();
        				this.average_red += value;
        				break;
        	case GREEN: super.setBlueChannel();
        				this.average_green += value;
        				break;
        	case BLUE:	super.setRedChannel();
        				this.average_blue += value;
        				break;
     	}
		if(this.validCol){
             super.getCurrentPixel().setRed(this.average_red/3);
             super.getCurrentPixel().setGreen(this.average_green/3);
        	 super.getCurrentPixel().setBlue(this.average_blue/3);
			 return super.getCurrentPixel();
		}
//		super.getDimension().increaseColumn();
		return null;
	}
	
	private void setValidRow(){
		this.validRow = super.getDimension().getRow() != 0 || (super.getDimension().getRow()%OFFSET_ROW) == 0;
	}
	
	private void setValidCol(){
		this.validCol = super.getDimension().getColumn() != 0 || (super.getDimension().getColumn()%OFFSET_COL) == 0;
	}

	@Override
	public void reset() {
		this.average_red = 0;
		this.average_green = 0;
		this.average_blue = 0;
	}

	@Override
	public void validate(Image image) throws ImageException {
		new ValidatorPixelCount().validate(super.getDimension(), image);
		new ValidatorRowCount().validate(super.getDimension());
	}

	
	
}
