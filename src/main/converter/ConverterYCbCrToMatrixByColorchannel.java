package main.converter;

import java.util.Map;
import java.util.TreeMap;

import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterYCbCrToMatrixByColorchannel {

	public static Array2DRowRealMatrix convertY(TreeMap<Coordinate, Colormodel> pixel){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix();
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getY());
		}
		return output;
	}
	
	public static Array2DRowRealMatrix convertCb(TreeMap<Coordinate, Colormodel> pixel){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix();
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getCb());
		}
		return output;
	}
	
	public static Array2DRowRealMatrix convertCr(TreeMap<Coordinate, Colormodel> pixel){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix();
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getCr());
		}
		return output;
	}
}
