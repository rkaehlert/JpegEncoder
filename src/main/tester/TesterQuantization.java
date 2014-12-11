package main.tester;

import main.converter.ConverterMatrixByQuantizationTable;
import main.converter.datatype.ConverterToDouble;
import main.logger.LoggerMatrix;
import main.model.quantization.JPEGQuantizationTable;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterQuantization {

	public static void main(String[] args){
		double[][] input = new double[][]{
			{581,-144,56,17,15,-7,25,-9},
			{-242,133,-48,42,-2,-7,13,-4},
			{108,-18,-40,71,-33,12,6,-10},
			{-56,-93,48,19,-8,7,6,-2},
			{-17,9,7,-23,-3,-10,5,3},
			{4,9,-4,-5,2,2,-7,3},
			{-9,7,8,-6,5,12,2,-5},
			{-9,-4,-2,-3,6,1,-1,-1},
		};
		
		double[][] jpegStdChrominance = ConverterToDouble.convert(JPEGQuantizationTable.JpegStdChrominance);
		
		Array2DRowRealMatrix inputMatrix = new Array2DRowRealMatrix(jpegStdChrominance);
		
		double[][] quantization = new double[][]{
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50},
				{50,50,50,50,50,50,50,50}
		};
		Array2DRowRealMatrix quantizationMatrix = new Array2DRowRealMatrix(quantization);
		
		ConverterMatrixByQuantizationTable converterQuantization = new ConverterMatrixByQuantizationTable();
		Array2DRowRealMatrix output = converterQuantization.convert(quantizationMatrix, inputMatrix);
		LoggerMatrix.log(output);
	}
	
}
