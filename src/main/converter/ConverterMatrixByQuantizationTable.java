package main.converter;

import java.util.LinkedList;
import java.util.List;

import main.exception.common.ExceptionInvalidParameter;
import main.utility.array.UtilityArrayDivision;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterMatrixByQuantizationTable implements Converter {

	public static Array2DRowRealMatrix convert(Array2DRowRealMatrix quantizationTable, Array2DRowRealMatrix matrix){
		Array2DRowRealMatrix output = null;
		if(quantizationTable.getColumnDimension() == matrix.getColumnDimension() && quantizationTable.getRowDimension() == matrix.getRowDimension()){
			output = new Array2DRowRealMatrix(quantizationTable.getRowDimension(), quantizationTable.getColumnDimension());
			for(int row = 0; row < quantizationTable.getRowDimension(); row++){
				double[] quantizationRow = quantizationTable.getRow(row);
				double[] matrixRow = matrix.getRow(row);
				output.setRow(row, UtilityArrayDivision.divide(matrixRow, quantizationRow));
			}
		}else{
			throw new ExceptionInvalidParameter("falsche groesse der matrizen. es koennen nur matrizen gleicher groesse verarbeitet werden");
		}
		return output;
	}
	
	public static List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix quantizationTable, List<Array2DRowRealMatrix> matrix){
		LinkedList<Array2DRowRealMatrix> output = new LinkedList<Array2DRowRealMatrix>();
		for(Array2DRowRealMatrix currentMatrix : matrix){
			output.addLast(ConverterMatrixByQuantizationTable.convert(quantizationTable, currentMatrix));
		}
		return output;
	}
	
}
