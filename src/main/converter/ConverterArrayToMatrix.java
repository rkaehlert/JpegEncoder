package main.converter;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterArrayToMatrix {

	public static Array2DRowRealMatrix convert(int width, int height, List<Integer> values){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(width,height);
		Iterator<Integer> iterator = values.iterator();
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				output.setEntry(i, j, iterator.next());
			}
		}
		return output;
	}
	
}
