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

public class FilterReductionByMiddleValue extends Filter<RGB> {
	
	private static final int OFFSET = 2;
	
	private TreeMap<Coordinate, RGB> sum_rgb = new TreeMap<>();
	
	@Override
	public TreeMap<Coordinate, RGB> filter(List<Character> lstCorrdinate) {
		Coordinate startingPointCoordinate = this.getStartingPoint();
		int value = ConverterStringToInteger.convert(lstCorrdinate);
		if(!sum_rgb.containsKey(startingPointCoordinate)){
			sum_rgb.put(startingPointCoordinate, new RGB());
		}
     	switch(super.getCurrentChannel()){
        	case RED:  	super.setGreenChannel();
        				this.sum_rgb.get(startingPointCoordinate).increaseRed(value/OFFSET);
        				break;
        	case GREEN: super.setBlueChannel();
        				this.sum_rgb.get(startingPointCoordinate).increaseGreen(value/OFFSET);
        				break;
        	case BLUE:	super.setRedChannel();
        				this.sum_rgb.get(startingPointCoordinate).increaseBlue(value/OFFSET);
        				break;
     	}
		if(this.isValidRow()){
			if(this.isValidColumn()){
				return sum_rgb;
			}
		}
		return null;
	}
	
	private Coordinate getStartingPoint(){
		int x = super.getDimension().getColumn()-OFFSET+(super.getDimension().getColumn()%OFFSET);
		int y = super.getDimension().getRow()-OFFSET+(super.getDimension().getRow()%OFFSET);
		if(x < 0){
			x = 0;
		}
		if(y < 0){
			y = 0;
		}
		return new Coordinate(x, y);
	}
	
	private boolean isValidRow(){
		return super.getDimension().getRow() != 0 && (super.getDimension().getRow()%OFFSET) == 0;
	}
	
	private boolean isValidColumn(){
		return super.getDimension().getColumn() != 0 && (super.getDimension().getColumn()%OFFSET) == 0;
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
