package exercise_one.filter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByMiddleValue extends Filter {
	
	private static final int OFFSET = 4;
	
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
				YCbCr newColorModel 	= new YCbCr();
				if(!ycbcr.getYChannel().isReduced()){
					newColorModel.getYChannel().setValue(ycbcr.getY());
				}
				if(!ycbcr.getCbChannel().isReduced()){
					newColorModel.getCbChannel().setValue(ycbcr.getCb());
				}
				if(!ycbcr.getCrChannel().isReduced()){
					newColorModel.getCrChannel().setValue(ycbcr.getCr());
				}
				returnValue.put(startingBlockCoordinate, newColorModel);
					
			}
			startingBlockColormodel = (YCbCr)returnValue.get(startingBlockCoordinate);
						
			if(ycbcr.getYChannel().isReduced()){
				startingBlockColormodel.add(ycbcr.getY()/(OFFSET*OFFSET), 0.0, 0.0);
			}
			if(ycbcr.getCbChannel().isReduced()){
				startingBlockColormodel.add(0.0, ycbcr.getCb()/(OFFSET*OFFSET), 0.0);
			}
			if(ycbcr.getCrChannel().isReduced()){
				startingBlockColormodel.add(0.0, 0.0, ycbcr.getCr()/(OFFSET*OFFSET));
			}
			if(!startingBlockCoordinate.equals(entry.getKey())){
				returnValue.put(entry.getKey(), this.generateReducedColorModel(ycbcr));
			}
		}
		return returnValue;
	}
	
	private Colormodel generateReducedColorModel(YCbCr value) {
		Double y = value.getY();
		Double cb = value.getCb();
		Double cr = value.getCr();
		if(value.getYChannel().isReduced()){
			y = null;
		}else{
			y = value.getY();
		}
		if(value.getCbChannel().isReduced()){
			cb = null;
		}else{
			cb = value.getCb();
		}
		if(value.getCrChannel().isReduced()){
			cr = null;
		}else{
			cr = value.getCr();
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
