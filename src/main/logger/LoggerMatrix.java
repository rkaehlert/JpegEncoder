package main.logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class LoggerMatrix implements Logger {
	
	public static void log(Array2DRowRealMatrix matrix){
		NumberFormat formatter = new DecimalFormat("0.0000000");
		for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                System.out.print("(" + i + "," + j + ") " + formatter.format(matrix.getEntry(i,j))+" ");
            }
            System.out.println();
        }
	}

	public static void log(List<Array2DRowRealMatrix> matrix){
		for(Array2DRowRealMatrix currentMatrix : matrix){
			LoggerMatrix.log(currentMatrix);
			System.out.println("\n");
		}
	}
	
}