package main.tester;

import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.logger.LoggerTimer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCTPerformance {

	public static final int SIZE = 256;
	
	public static void main(String[] args){
		double[][] m = new double[SIZE][SIZE];
		for(int x = 0; x < SIZE; x++){
			for(int y = 0; y < SIZE; y++){
				m[x][y] = (x + y * 8) % 256; 
			}
		}
		LoggerTimer timer = new LoggerTimer();
		Long end = System.currentTimeMillis() + 11000;
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(m); 
		ConverterDiscreteCosinusTransformation dct = new ConverterDiscreteCosinusTransformation();
		while(System.currentTimeMillis() < end){
			timer.start();
			dct.convert(matrix);
			timer.stop();
		}
		timer.log();
		timer.reset();
		ConverterDiscreteCosinusTransformationArai dcta = new ConverterDiscreteCosinusTransformationArai();
		end = System.currentTimeMillis() + 11000;
		while(System.currentTimeMillis() < end){
			timer.start();
			dcta.convert(matrix);
			timer.stop();
		}
		timer.log();
		timer.reset();
		ConverterDiscreteCosinusTransformationArai dctSeparate = new ConverterDiscreteCosinusTransformationArai();
		end = System.currentTimeMillis() + 11000;
		while(System.currentTimeMillis() < end){
			timer.start();
			dctSeparate.convert(matrix);
			timer.stop();
		}
		timer.log();
	}
	
}
