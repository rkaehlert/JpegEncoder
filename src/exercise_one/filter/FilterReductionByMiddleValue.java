package exercise_one.filter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByMiddleValue extends Filter {
	
	private static final int OFFSET = 2;
	
	@Override
	public TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) {
		Coordinate startingBlockCoordinate = null;
		YCbCr startingBlockColormodel = null;
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		YCbCr ycbcr = null;
		Coordinate currentCoordinate = null;
		for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
			currentCoordinate = entry.getKey();
			ycbcr = (YCbCr)entry.getValue();
			startingBlockCoordinate = this.getStartingPoint(currentCoordinate.getY(), currentCoordinate.getX());
			if(!returnValue.containsKey(startingBlockCoordinate)){
				returnValue.put(startingBlockCoordinate, new YCbCr());
			}
			startingBlockColormodel = (YCbCr)returnValue.get(startingBlockCoordinate);
			startingBlockColormodel.add(ycbcr.getY()/OFFSET*OFFSET, ycbcr.getCb()/OFFSET*OFFSET, ycbcr.getCr()/OFFSET*OFFSET);
		}
		return returnValue;
	}
	
	private Coordinate getStartingPoint(int row, int column){
		int x = column-(column%OFFSET);
		int y = row-(row%OFFSET);
		if(x < 0){
			x = 0;
		}
		if(y < 0){
			y = 0;
		}
		return new Coordinate(x, y);
	}
	
	@Override
	public void reset() {
		
	}
	
}
