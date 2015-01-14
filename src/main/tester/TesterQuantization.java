package main.tester;

import main.converter.ConverterMatrixByQuantizationTable;
import main.converter.datatype.ConverterToDouble;
import main.logger.LoggerMatrix;
import main.model.quantization.JPEGQuantizationTable;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterQuantization {

	public static int SIZE = 8;
	
	public static void main(String[] args){
		
		double[][] m = new double[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                m[x][y] = (x + y * 8) % 256;
            }
        }
		
		
		double[][] jpegStdChrominance = ConverterToDouble.convert(JPEGQuantizationTable.JpegStdChrominance);
		
		Array2DRowRealMatrix inputMatrix = new Array2DRowRealMatrix(m);

		Array2DRowRealMatrix quantizationMatrix = new Array2DRowRealMatrix(jpegStdChrominance);
		
		ConverterMatrixByQuantizationTable converterQuantization = new ConverterMatrixByQuantizationTable();
		Array2DRowRealMatrix output = converterQuantization.convert(quantizationMatrix, inputMatrix);
		LoggerMatrix.log(output);
	}
	
}
