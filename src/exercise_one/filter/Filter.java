package exercise_one.filter;

import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

public abstract class Filter{
	
	public abstract TreeMap<Coordinate, Colormodel> filter(TreeMap<Coordinate, Colormodel> pixel);
	public abstract void reset();
		
}
