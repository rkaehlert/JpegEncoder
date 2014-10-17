package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.exception.ImageException;
import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;

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
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void reset() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void validate(Image image) throws ImageException {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
	}

	
	
}
