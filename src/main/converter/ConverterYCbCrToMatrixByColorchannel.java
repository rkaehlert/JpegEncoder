package main.converter;

import java.util.Map;
import java.util.TreeMap;

import main.exception.common.ExceptionInvalidParameter;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterYCbCrToMatrixByColorchannel {

	public static Array2DRowRealMatrix convertY(TreeMap<Coordinate, Colormodel> pixel){
		double size = Math.sqrt(pixel.size());
		if((size % 1) != 0){
			throw new ExceptionInvalidParameter("die groesse des eingegebenen bildes ist nicht quadratisch");
		}
		Array2DRowRealMatrix output = new Array2DRowRealMatrix((int)size,(int)size);
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			if(ycbcr.getY() != null){
				output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getY());
			}
		}
		return output;
	}
	
	public static Array2DRowRealMatrix convertCb(TreeMap<Coordinate, Colormodel> pixel){
		int size = pixel.size()/2;
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(size,size);
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			if(ycbcr.getCb() != null){
				output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getCb());
			}
		}
		return output;
	}
	
	public static Array2DRowRealMatrix convertCr(TreeMap<Coordinate, Colormodel> pixel){
		int size = pixel.size()/2;
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(size,size);
		for(Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()){
			YCbCr ycbcr = (YCbCr) currentEntry.getValue();
			if(ycbcr.getCr() != null){
				output.setEntry(currentEntry.getKey().getX(), currentEntry.getKey().getY(), ycbcr.getCr());
			}
		}
		return output;
	}
}
