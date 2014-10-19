package exercise_one.filter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.exception.InvalidParameterException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByM extends Filter {
	
	private int OFFSET_ROW = 2;
	private int OFFSET_COL = 2;
	
	public FilterReductionByM(int offsetRow, int offsetCol) {
		OFFSET_COL = offsetCol;
		OFFSET_ROW = offsetRow;
	}
	
	public FilterReductionByM() {
	}
	
	@Override
	public TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) throws InvalidParameterException {
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		if(pixel != null){
			YCbCr ycbcr = null;
			Coordinate coordinate = null;
			for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
				coordinate = (Coordinate)entry.getKey();
				ycbcr = (YCbCr)entry.getValue();
				if(this.isValidRow(coordinate.getY())){
					if(this.isValidCol(coordinate.getX())){
						returnValue.put(coordinate, ycbcr);
					}
				}
			}
			return returnValue;
		}else{
			throw new InvalidParameterException("es wurde kein farbmodell uebergeben");
		}
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
