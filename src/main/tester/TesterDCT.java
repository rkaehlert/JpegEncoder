package main.tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterInverseCosinusTransformation;
import main.converter.ConverterMatrixToBlock;
import main.file.image.JPEGImage;
import main.file.image.resource.ImageResourceReader;
import main.logger.LoggerMatrix;
import main.model.color.Colormodel;
import main.model.color.RGB;
import main.model.matrix.Coordinate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCT {

	public static int SIZE = 400;
	
	public static void main(String[] args){
		ImageResourceReader reader = new ImageResourceReader();
		JPEGImage image = reader.read("test_small.ppm", 255);
		int size = image.getWidth() - (image.getWidth() % 8); 
		double[][] input = new double[size][size];
		try{
			for(Map.Entry<Coordinate, Colormodel> entry : image.getPixel().entrySet()){
				input[entry.getKey().getX()][entry.getKey().getY()] = ((RGB)entry.getValue()).getRed();
			}	
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("array erreicht");
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
								{230, 187, 186,188,201,246,259,206,270, 197, 196,194,292,207,229,244},
								{240, 197, 196,198,202,206,209,204,240, 197, 196,198,202,206,209,204},
								{230, 187, 186,188,201,246,259,206,270, 197, 196,194,292,207,229,244},
								{230, 187, 186,188,201,246,259,206,270, 197, 196,194,292,207,229,244}
		};
		
		input2 = new double[][] {
				{154,123,123,123,123,123,123,136},
				{192,180,136,154,154,154,136,110},
				{254,198,154,154,180,154,123,123},
				{239,180,136,180,180,166,123,123},
				{180,154,136,167,166,149,136,136},
				{128,136,123,136,154,180,198,154},
				{123,105,110,149,136,136,180,166},
				{110,136,123,123,123,136,154,136}
				};
		
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(input2);

		
		List<Array2DRowRealMatrix> output = new ConverterDiscreteCosinusTransformation().convert(matrix);
		LoggerMatrix.log(matrix);
		
		List<Array2DRowRealMatrix> inverse = new ArrayList<Array2DRowRealMatrix>();
		
		for(Array2DRowRealMatrix currentDCT : output){
			LoggerMatrix.log(currentDCT);
			inverse.addAll(new ConverterInverseCosinusTransformation().convert(currentDCT));	
		}
		
		List<Array2DRowRealMatrix> temp = ConverterMatrixToBlock.convert(matrix, 8);
	
		System.out.println(ComparatorMatrixEquals.compare(inverse,temp,0.1));
	}
	
}
