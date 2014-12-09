package main.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterImageTo8x8Block implements Converter {

	private static final int BLOCK_SIZE = 8;
	
	public static List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix){
			List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
			for(int i = 0; i < matrix.getRowDimension(); i+=BLOCK_SIZE){
				for(int j = 0; j < matrix.getColumnDimension(); j+=BLOCK_SIZE){
					output.add((Array2DRowRealMatrix)matrix.getSubMatrix(i, i+BLOCK_SIZE-1, j, j+BLOCK_SIZE-1));
				}
			}
			return output;
	}
	
}
