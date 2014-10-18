package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.exception.ImageException;
import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;

public class FilterReductionByMiddleValue extends Filter<RGB> {
	
	private static final int OFFSET = 2;
	
	private TreeMap<Coordinate, RGB> sum_rgb = new TreeMap<>();
	
	@Override
	public TreeMap<Coordinate, RGB> filter(List<Character> lstCorrdinate) {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate(Image image) throws ImageException {
		// TODO Auto-generated method stub
		
	}
	
}
