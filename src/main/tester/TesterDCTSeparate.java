package main.tester;

import java.util.List;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterSeparateCosinusTransformation;
import main.file.image.JPEGImage;
import main.file.image.resource.ImageResourceReader;
import main.logger.LoggerMatrix;
import main.logger.LoggerTimer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCTSeparate {

	public static int SIZE = 400;
	
	public static void main(String[] args){
		ImageResourceReader reader = new ImageResourceReader();
		JPEGImage image = reader.read("test_small.ppm", 255);
		int size = image.getWidth() - (image.getWidth() % 8); 
		
		Array2DRowRealMatrix imagePart = new Array2DRowRealMatrix(8,8);
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                imagePart.setEntry(i,j,i*j);
            }
        }
        
		
		double[][] input2 = new double[][]{
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204}
		};
		
		input2 = new double[][]{
				{128,127,130,128,134,130,128,128},
				{128,127,130,128,134,130,128,128},
				{125,126,130,127,130,131,128,127},
				{124,128,127,126,129,130,127,127},
				{131,132,130,129,132,128,129,131},
				{129,131,134,131,133,129,131,131},
				{129,134,134,136,136,137,134,132},
				{133,135,136,138,137,140,135,132}
				};
		
		LoggerTimer loggerTimer = new LoggerTimer();
		loggerTimer.start();
		List<Array2DRowRealMatrix> output = new ConverterDiscreteCosinusTransformation().convert(new Array2DRowRealMatrix(input2));
		loggerTimer.stop();
		loggerTimer.log();
		LoggerMatrix.log(output);
		loggerTimer.reset();
		loggerTimer.start();
		List<Array2DRowRealMatrix> separate = new ConverterSeparateCosinusTransformation().convert(new Array2DRowRealMatrix(input2));
		loggerTimer.stop();
		loggerTimer.log();
		LoggerMatrix.log(separate);
		System.out.println(ComparatorMatrixEquals.compare(output, separate, 0.1));
	}
	
}
