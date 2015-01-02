package main.file.image;

import java.util.TreeMap;

import main.exception.common.ExceptionInvalidParameter;
import main.filter.Filter;
import main.model.color.Colormodel;
import main.model.matrix.Coordinate;

public abstract class Image {

    protected TreeMap<Coordinate, Colormodel> pixel = new TreeMap<Coordinate,Colormodel>();
    protected int width;
    protected int height;
    protected int maxColorValue = 0;

    public TreeMap<Coordinate, Colormodel> getPixel() {
        return this.pixel;
    }

    public TreeMap<Coordinate, Colormodel> filter(Filter filter) {
        try {
            this.pixel = filter.filter(this.pixel);
        }
        catch (ExceptionInvalidParameter e) {
            e.printStackTrace();
        }
        return null;
    }

	public void setPixel(TreeMap<Coordinate, Colormodel> pixel) {
		this.pixel = pixel;
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
}
