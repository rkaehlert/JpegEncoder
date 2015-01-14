package main.converter;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterMatrixToZickZackSequence implements Converter {


	private  static int[][] pattern = {     {0, 0}, {0, 1}, {1, 0}, {2, 0}, {1, 1}, {0, 2}, {0, 3}, {1, 2}, 
							{2, 1}, {3, 0}, {4, 0}, {3, 1}, {2, 2}, {1, 3}, {0, 4}, {0, 5}, 
							{1, 4}, {2, 3}, {3, 2}, {4, 1}, {5, 0}, {6, 0}, {5, 1}, {4, 2}, 
							{3, 3}, {2, 4}, {1, 5}, {0, 6}, {0, 7}, {1, 6}, {2, 5}, {3, 4}, 
							{4, 3}, {5, 2}, {6, 1}, {7, 0}, {7, 1}, {6, 2}, {5, 3}, {4, 4}, 
							{3, 5}, {2, 6}, {1, 7}, {2, 7}, {3, 6}, {4, 5}, {5, 4}, {6, 3}, 
							{7, 2}, {7, 3}, {6, 4}, {5, 5}, {4, 6}, {3, 7}, {4, 7}, {5, 6},
                            {6, 5}, {7, 4}, {7, 5}, {6, 6}, {5, 7}, {6, 7}, {7, 6}, {7, 7}
                     };

	
	public static Integer[] convert(Array2DRowRealMatrix matrix){
		int rows = matrix.getRowDimension();
		int cols = matrix.getColumnDimension();
		Integer[] output = new Integer[64];
		if(rows == 8 && cols == 8){
			for(int i = 0; i < 64; i++){
				output[i] = (int) matrix.getEntry(pattern[i][0], pattern[i][1]);
			}
		}
		return output;
	}
	
	public static List<Integer[]> convert(List<Array2DRowRealMatrix> lstMatrix){
		List<Integer[]> output = new LinkedList<Integer[]>();
		for(Array2DRowRealMatrix matrix : lstMatrix){
			output.add(ConverterMatrixToZickZackSequence.convert(matrix));
		}
		return output;
	}
}
