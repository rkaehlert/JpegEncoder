package main.comparator;

import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;


public class ComparatorMatrixEquals {

	public static boolean compare(Array2DRowRealMatrix expected, Array2DRowRealMatrix actual, double tolerance){
		boolean output = true;
		tolerance = Math.abs(tolerance);
		if(expected == null || actual == null){
			return false;
		}
		if(expected.getRowDimension() != actual.getRowDimension() || expected.getColumnDimension() != actual.getColumnDimension()){
			return false;
		}
		for(int i = 0; i < expected.getRowDimension(); i++){
			for(int j = 0; j < expected.getColumnDimension(); j++){
				if( (expected.getEntry(i, j) > actual.getEntry(i, j)-tolerance && expected.getEntry(i, j) < actual.getEntry(i, j)+tolerance) == false){
					System.out.println("fehler in zeile " + i + " spalte " + j);
					output = false;
				}
			}
		}
		return output;
	}
	
	public static boolean compare(List<Array2DRowRealMatrix> expected, List<Array2DRowRealMatrix> actual, double tolerance){
		if(expected.size() != actual.size()){
			return false;
		}
		boolean output = true;
		for(int index = 0; index < expected.size(); index++){
			output = ComparatorMatrixEquals.compare(expected.get(index), actual.get(index), tolerance);
		}
		return output;
	}
	
}
