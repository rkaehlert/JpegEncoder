package main.filter;

import java.util.TreeMap;

import main.exception.common.InvalidParameterException;
import main.model.color.Colormodel;
import main.model.matrix.Coordinate;

public abstract class Filter{
	
	public abstract TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel) throws InvalidParameterException;
	public abstract void reset();
		
}
