package main.converter;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class Converter8x8MatrixToZickZackSequence implements Converter {


    int[][] pattern = {     {0, 0}, {0, 1}, {1, 0}, {2, 0}, {1, 1}, {0, 2}, {0, 3}, {1, 2}, 
							{2, 1}, {3, 0}, {4, 0}, {3, 1}, {2, 2}, {1, 3}, {0, 4}, {0, 5}, 
							{1, 4}, {2, 3}, {3, 2}, {4, 1}, {5, 0}, {6, 0}, {5, 1}, {4, 2}, 
							{3, 3}, {2, 4}, {1, 5}, {0, 6}, {0, 7}, {1, 6}, {2, 5}, {3, 4}, 
							{4, 3}, {5, 2}, {6, 1}, {7, 0}, {7, 1}, {6, 2}, {5, 3}, {4, 4}, 
							{3, 5}, {2, 6}, {1, 7}, {2, 7}, {3, 6}, {4, 5}, {5, 4}, {6, 3}, 
							{7, 2}, {7, 3}, {6, 4}, {5, 5}, {4, 6}, {3, 7}, {4, 7}, {5, 6},
                            {6, 5}, {7, 4}, {7, 5}, {6, 6}, {5, 7}, {6, 7}, {7, 6}, {7, 7}
                     };

	
	public double[] convert(Array2DRowRealMatrix matrix){
		int rows = matrix.getRowDimension();
		int cols = matrix.getColumnDimension();
		double[] output = new double[64];
		if(rows == 8 && cols == 8){
			for(int i = 0; i < 64; i++){
				output[i] =  matrix.getEntry(pattern[i][0], pattern[i][1]);
			}
		}
		return output;
	}
	
}
