package main.filter;

import java.util.Map;
import java.util.TreeMap;

import main.exception.common.InvalidParameterException;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

public class FilterReductionByStep extends Filter {
	
	private int OFFSET_ROW = 1;
	private int OFFSET_COL = 2;
	
	public FilterReductionByStep(int offsetRow, int offsetCol) {
		OFFSET_COL = offsetCol;
		OFFSET_ROW = offsetRow;
	}
	
	public FilterReductionByStep() {
	}
	
	@Override
	public TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) throws InvalidParameterException {
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		if(pixel != null){
			YCbCr ycbcr = null;
			Coordinate coordinate = null;
			for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
				if(!(entry.getValue() instanceof YCbCr)){
					throw new IllegalArgumentException("es wurde ein falsches farbmodell uebergeben");
				}
				coordinate = (Coordinate)entry.getKey();
				ycbcr = (YCbCr)entry.getValue();
				if(!this.isValidCoordinate(coordinate)){
					if(ycbcr.getYChannel().isReduced() == true){
						ycbcr.getYChannel().reset();
					}
					if(ycbcr.getCbChannel().isReduced() == true){
						ycbcr.getCbChannel().reset();
					}
					if(ycbcr.getCrChannel().isReduced() == true){
						ycbcr.getCrChannel().reset();
					}
				}
				returnValue.put(coordinate, ycbcr);
			}
			return returnValue;
		}else{
			throw new InvalidParameterException("es wurde kein farbmodell uebergeben");
		}
	}
	
	private boolean isValidCoordinate(Coordinate coordinate) {
		if(this.isValidRow(coordinate.getY())){
			if(this.isValidCol(coordinate.getX())){
				return true;
			}
		}
		return false;
	}

	private boolean isValidRow(int row){
		return (row%OFFSET_ROW) == 0;
	}
	
	private boolean isValidCol(int column){
		return (column%OFFSET_COL) == 0;
	}
	
	@Override
	public void reset() {
		
	}	
	
}
