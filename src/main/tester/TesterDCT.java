package main.tester;

import java.util.Map;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterInverseCosinusTransformation;
import main.file.image.JPEGImage;
import main.file.image.resource.ImageResourceReader;
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
		
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(input);

		Array2DRowRealMatrix output = new ConverterDiscreteCosinusTransformation().convert(matrix,size,size);
		output = new ConverterInverseCosinusTransformation().convert(output,  size,size);
		output.setEntry(15, 23, 32.5);
		output.setEntry(100, 67, 32.5);
		System.out.println(ComparatorMatrixEquals.compare(output,matrix,0.1));
	}
	
}
