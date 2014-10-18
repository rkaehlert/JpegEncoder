package exercise_one.filter;

import java.util.List;
import java.util.TreeMap;

import exercise_one.Image;
import exercise_one.exception.ImageException;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;
import exercise_one.model.matrix.Dimension;

public abstract class Filter <T> {

    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
	
    private Dimension dimension = new Dimension();
    private RGB currentPixel = new RGB();
	private int currentChannel = RED;
	
	public abstract TreeMap<Coordinate, RGB> filter(List<Character> lstCorrdinate);
	public abstract void reset();
	public abstract void validate(Image image) throws ImageException;

	public RGB getCurrentPixel() {
		return currentPixel;
	}
	public void setCurrentPixel(RGB currentPixel) {
		this.currentPixel = currentPixel;
	}
	public int getCurrentChannel() {
		return currentChannel;
	}
	public void setCurrentChannel(int currentChannel) {
		this.currentChannel = currentChannel;
	}
	public void setGreenChannel() {
		this.currentChannel = GREEN;
	}
	public void setRedChannel(){
		this.currentChannel = RED;
	}
	
	public void setBlueChannel(){
		this.currentChannel = BLUE;
	}
	
	public boolean isBlueChannel(){
		return this.currentChannel == BLUE;
	}
	
	public boolean isRedChannel(){
		return this.currentChannel == RED;
	}
	
	public boolean isGreenChannel(){
		return this.currentChannel == GREEN;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public void switchChannel(){
		 if (this.isRedChannel()){
             this.setGreenChannel();
         }
         else if (this.isGreenChannel()){
        	 this.setBlueChannel();
         }
         else if (this.isBlueChannel()){
        	 this.setRedChannel();
         }
	}
	
}
