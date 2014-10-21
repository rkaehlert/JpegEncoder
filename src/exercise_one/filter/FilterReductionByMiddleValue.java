package exercise_one.filter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.exception.InvalidParameterException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByMiddleValue extends Filter {
	
	private static final int OFFSET = 4;
	
	@Override
	public TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) throws InvalidParameterException {
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		if(pixel != null){
			Coordinate startingBlockCoordinate = null;
			YCbCr startingBlockColormodel = null;
			Coordinate currentCoordinate = null;
			YCbCr ycbcr = null;
			
			for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
				currentCoordinate = entry.getKey();
				ycbcr = (YCbCr)entry.getValue();
				startingBlockCoordinate = this.getStartingPoint(currentCoordinate.getY(), currentCoordinate.getX());
				if(!returnValue.containsKey(startingBlockCoordinate)){
					returnValue.put(startingBlockCoordinate, new YCbCr());
				}
				startingBlockColormodel = (YCbCr)returnValue.get(startingBlockCoordinate);
				startingBlockColormodel.add(ycbcr.getY()/(OFFSET*OFFSET), ycbcr.getCb()/(OFFSET*OFFSET), ycbcr.getCr()/(OFFSET*OFFSET));
				
				if(!entry.getKey().equals(startingBlockCoordinate)){
					returnValue.put(entry.getKey(), this.generateReducedColorModel(ycbcr));
				}
			}
		}else{
			throw new InvalidParameterException("es wurde kein farbmodell uebergeben");
		}
		return returnValue;
	}
	
	private Colormodel generateReducedColorModel(YCbCr value) {
		Double y = value.getY();
		Double cb = value.getCb();
		Double cr = value.getCr();
		if(value.getYChannel().isReduced()){
			y = null;
		}
		if(value.getCbChannel().isReduced()){
			cb = null;
		}
		if(value.getCrChannel().isReduced()){
			cr = null;
		}
		return new YCbCr(y,cb,cr);
	}

	private Coordinate getStartingPoint(int row, int column) {
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
