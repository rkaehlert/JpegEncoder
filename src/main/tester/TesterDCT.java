package main.tester;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterDiscreteCosinusTransformationArai;
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
		
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(input);

		
		List<Array2DRowRealMatrix> output = new ConverterDiscreteCosinusTransformation().convert(matrix);
		List<Array2DRowRealMatrix> araiOutput = new ConverterDiscreteCosinusTransformationArai().convert(matrix, null);
		LoggerMatrix.log(matrix);
		
		List<Array2DRowRealMatrix> inverse = new ArrayList<Array2DRowRealMatrix>();
		
		for(Array2DRowRealMatrix currentDCT : output){
			inverse.addAll(new ConverterInverseCosinusTransformation().convert(currentDCT));	
		}
		
		List<Array2DRowRealMatrix> temp = ConverterMatrixToBlock.convert(matrix, 8);
	
		System.out.println(ComparatorMatrixEquals.compare(output, araiOutput, 0.1));
		System.out.println(ComparatorMatrixEquals.compare(inverse,temp,0.1));
		System.out.println(ComparatorMatrixEquals.compare(inverse,temp,0.1));
	}
	
}
