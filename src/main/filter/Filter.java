package main.filter;

import java.util.HashMap;
import java.util.TreeMap;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import main.exception.common.ExceptionInvalidParameter;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

public abstract class Filter{
	
	public abstract HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> filter(TreeMap<Coordinate, Colormodel> pixel, int size_x, int size_y) throws ExceptionInvalidParameter;
	public abstract void reset();
		
}
