package main.file.image;

import java.util.HashMap;
import java.util.TreeMap;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import main.exception.common.ExceptionInvalidParameter;
import main.filter.Filter;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

public abstract class Image {

    protected TreeMap<Coordinate, Colormodel> pixel = new TreeMap<Coordinate,Colormodel>();
    private HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> reducedPixel = new HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix>();
    protected int width;
    protected int height;
    protected int maxColorValue = 0;

    public TreeMap<Coordinate, Colormodel> getPixel() {
        return this.pixel;
    }

    public HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> filter(Filter filter) {
        try {
            return filter.filter(this.pixel, width, height);
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

	public HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> getReducedPixel() {
		return reducedPixel;
	}

	public void setReducedPixel(HashMap<YCbCr.ColorChannelYCbCr, Array2DRowRealMatrix> reducedPixel) {
		this.reducedPixel = reducedPixel;
	}
}
