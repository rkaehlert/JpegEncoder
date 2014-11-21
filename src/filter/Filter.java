package filter;

import java.util.TreeMap;

import exception.common.InvalidParameterException;
import model.color.Colormodel;
import model.matrix.Coordinate;

public abstract class Filter{
	
	public abstract TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) throws InvalidParameterException;
	public abstract void reset();
		
}
