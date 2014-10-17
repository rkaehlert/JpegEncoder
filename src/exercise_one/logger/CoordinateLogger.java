package exercise_one.logger;

import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

public class CoordinateLogger {

	private TreeMap<Coordinate, Colormodel> pixels;
	private int width;
	private int height;
	
	public CoordinateLogger(TreeMap<Coordinate, Colormodel> pixels, int width, int height){
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}
	
	public void log(){
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				Coordinate coor = new Coordinate(col, row);
				if(this.pixels.containsKey(coor)){
					System.out.print("X");
//					System.out.print("(" + coor.getX() + "," + coor.getY() + ")");
				}else{
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
	
}
