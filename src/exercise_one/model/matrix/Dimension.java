package exercise_one.model.matrix;

public class Dimension {

    private int row 		= 0;
    private int column 		= 0;
    private int width 		= 0;
    private int height 		= 0;
    
	public void increaseRow(){
		this.row++;
	}
	
	public void increaseColumn(){
		this.column++;
	}
	
	public void resetColumn(){
		this.column = 0;
	}
	
	public void resetRow(){
		this.row = 0;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
