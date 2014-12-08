package main.comparator;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;


public class ComparatorMatrixEquals {

	public static boolean compare(Array2DRowRealMatrix expected, Array2DRowRealMatrix actual, double tolerance){
		if(expected == null || actual == null){
			return false;
		}
		if(expected.getRowDimension() != actual.getRowDimension() || expected.getColumnDimension() != actual.getColumnDimension()){
			return false;
		}
		for(int i = 0; i < expected.getRowDimension(); i++){
			for(int j = 0; j < expected.getColumnDimension(); j++){
				if( expected.getEntry(i, j) < actual.getEntry(i, j)-tolerance &&
					expected.getEntry(i, j) > actual.getEntry(i, j)+tolerance && 
					expected.getEntry(i, j) != actual.getEntry(i, j)){
					System.out.println("fehler in zeile " + i + " spalte " + j);
					return false;
				}
			}
		}
		return true;
	}
	
}
