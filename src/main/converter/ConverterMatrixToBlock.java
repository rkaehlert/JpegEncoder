package main.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterMatrixToBlock implements Converter {
	
	public static List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix, int blockSize){
			List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
			for(int i = 0; i < matrix.getRowDimension(); i+=blockSize){
				for(int j = 0; j < matrix.getColumnDimension(); j+=blockSize){
					if((i+blockSize) <= matrix.getRowDimension() && (j+blockSize) <= matrix.getColumnDimension()){
						output.add((Array2DRowRealMatrix)matrix.getSubMatrix(i, i+blockSize-1, j, j+blockSize-1));
					}else{
						System.out.println("dei groesse des bildes ist nicht durch 8 teilbar. die restlichen pixel werden entfernt");
						return output;
					}
				}
			}
			return output;
	}
	
}
