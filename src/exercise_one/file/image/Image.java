package exercise_one.file.image;

import java.util.TreeMap;

import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

public abstract class Image {
	
	private TreeMap<Coordinate, Colormodel> pixel = new TreeMap<Coordinate, Colormodel>();
	
	public abstract void read();
	
	public TreeMap<Coordinate, Colormodel> getPixel(){
		return this.pixel;
	}
}
