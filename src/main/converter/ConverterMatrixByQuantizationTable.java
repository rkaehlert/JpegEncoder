package main.converter;

import main.exception.common.ExceptionInvalidParameter;
import main.utility.array.UtilityArrayDivision;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterMatrixByQuantizationTable implements Converter {

	public Array2DRowRealMatrix convert(Array2DRowRealMatrix quantizationTable, Array2DRowRealMatrix matrix){
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
	
}
