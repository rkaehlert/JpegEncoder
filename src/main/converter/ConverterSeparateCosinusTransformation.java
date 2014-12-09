package main.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;



public class ConverterSeparateCosinusTransformation implements Converter{

	private static final int BLOCK_SIZE = 8;
	
	public Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix X){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(X.getRowDimension(), X.getColumnDimension());
		
		for(int u = 0; u < X.getRowDimension(); u++){
			output.setRow(u, convert1D(X.getRow(u)));
		}
		
		double[] column = new double[X.getColumnDimension()];
		
		for(int col = 0; col < X.getColumnDimension(); col++){
			column = output.getColumn(col);
			double[] temp = convert1D(column);
			for(int row = 0; row < output.getRowDimension(); row++){
				output.setEntry(row, col, temp[row]);
			}
		}
		
	   return output;
	}
	
	public double[] convert1D(double[] data){
		final double const1 = (1 / Math.sqrt(2.0)) * Math.sqrt(2.0 / data.length);
		final double const2 = Math.sqrt(2.0 / data.length);
		
		double[] output = new double[data.length];
		
		for(int k = 0; k < data.length; k++){
			for(int i = 0; i < output.length; i++){
				output[k] += data[i] * Math.cos(( (2*i+1) * k * Math.PI) / (2 * data.length));
			}
			output[k] *= (k == 0 ? const1 : const2);
		}
		
		return output;
	}
	
	public List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix){
		List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
		for(Array2DRowRealMatrix currentMatrix : ConverterImageTo8x8Block.convert(matrix)){
				Array2DRowRealMatrix matrix8x8 = this.convert8x8(currentMatrix);
				output.add(matrix8x8);
		}
		return output;
	}
	
	
}
