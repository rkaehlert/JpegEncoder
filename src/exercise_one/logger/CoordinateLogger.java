package exercise_one.logger;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
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
	
	public void log(boolean isLoggingEnabled){
		System.out.println("----------------------------------------------------------");
		System.out.println("Anzahl Eintraege: " + pixels.size());
		System.out.println("Anzahl Zeilen: " + height);
		System.out.println("Anzahl Spalten: " + width);
		System.out.println("----------------------------------------------------------");
		if(isLoggingEnabled){
			int last_y = 0;
			for(Map.Entry<Coordinate, Colormodel> entry : pixels.entrySet()){
				if(entry.getKey().getY() > last_y){
					System.out.print("\n");
					last_y = 0;
				}
				last_y = entry.getKey().getY();
				if(entry.getValue() instanceof RGB){
					//System.out.print("X");
					System.out.print("(" + ((RGB)entry.getValue()).getRed() + "," + ((RGB)entry.getValue()).getGreen() + "," + ((RGB)entry.getValue()).getBlue() + ")");
					//System.out.print("(" + entry.getKey().getX() + "," + entry.getKey().getY() + ")");
				}else{
					System.out.print("X");
				}
			}
		}
	}
	
}
