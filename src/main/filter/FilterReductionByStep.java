package main.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import main.exception.common.ExceptionInvalidParameter;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class FilterReductionByStep extends Filter {
	
	private int OFFSET_ROW = 1;
	private int OFFSET_COL = 2;
	
	public FilterReductionByStep(int offsetRow, int offsetCol) {
		OFFSET_COL = offsetCol;
		OFFSET_ROW = offsetRow;
	}
	
	public FilterReductionByStep() {
	}
	
	@Override
	public HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> filter(TreeMap<Coordinate, Colormodel> pixel, int row_count, int column_count) throws ExceptionInvalidParameter {
		HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> returnValue = new HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix>();
		returnValue.put(YCbCr.ColorChannelYCbCr.Y, new Array2DRowRealMatrix(row_count, column_count));
		returnValue.put(YCbCr.ColorChannelYCbCr.Cb, new Array2DRowRealMatrix(row_count/2, column_count/2));
		returnValue.put(YCbCr.ColorChannelYCbCr.Cr, new Array2DRowRealMatrix(row_count/2, column_count/2));
		if(pixel != null){
			YCbCr ycbcr = null;
			Coordinate coordinate = null;
			for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
				if(!(entry.getValue() instanceof YCbCr)){
					throw new IllegalArgumentException("es wurde ein falsches farbmodell uebergeben");
				}
				coordinate = (Coordinate)entry.getKey();
				ycbcr = (YCbCr)entry.getValue();
				
				returnValue.get(YCbCr.ColorChannelYCbCr.Y).setEntry(coordinate.getY(), coordinate.getX(), ycbcr.getY());
				//ycbcr.getYChannel().reset();
				
				if(!this.isValidCoordinate(coordinate)){
					int col = (int)Math.floor(coordinate.getX() / 2);
					int row = (int)Math.floor(coordinate.getY() / 2);
					
					if(ycbcr.getCbChannel().isReduced() == true){
						returnValue.get(YCbCr.ColorChannelYCbCr.Cb).setEntry(row, col, ycbcr.getCb());
						ycbcr.getCbChannel().reset();
					}
					if(ycbcr.getCrChannel().isReduced() == true){
						returnValue.get(YCbCr.ColorChannelYCbCr.Cr).setEntry(row, col, ycbcr.getCr());
						ycbcr.getCrChannel().reset();
					}	
				}
			}
			return returnValue;
		}else{
			throw new ExceptionInvalidParameter("es wurde kein farbmodell uebergeben");
		}
	}
	
	private boolean isValidCoordinate(Coordinate coordinate) {
		if(this.isValidRow(coordinate.getY())){
			if(this.isValidCol(coordinate.getX())){
				return true;
			}
		}
		return false;
	}

	private boolean isValidRow(int row){
		return (row%OFFSET_ROW) == 0;
	}
	
	private boolean isValidCol(int column){
		return (column%OFFSET_COL) == 0;
	}
	
	@Override
	public void reset() {
		
	}	
	
}
