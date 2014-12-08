package main.model.matrix;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;




public class Matrix2D extends Array2DRowRealMatrix {
		
	public Matrix2D(int rows, int cols){
		super(rows,cols);
	}

	public Matrix2D getBlock(int size){
		Matrix2D output = new Matrix2D(size,size);
		for(int col = 0; col < size; col++){
			if(col == size){
				return output;
			}
			for(int row = 0; row < size; row++){
				if(row == size){
					return output;
				}
				output.setEntry(col, row, this.getEntry(col,row));
			}
		}
		return output;
	}

}
