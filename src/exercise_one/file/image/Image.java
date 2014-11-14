package exercise_one.file.image;

import java.util.TreeMap;

import exercise_one.exception.common.InvalidParameterException;
import exercise_one.filter.Filter;
import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

public abstract class Image {

    protected TreeMap<Coordinate, Colormodel> pixel = new TreeMap<>();
    protected int width;
    protected int height;
    protected int maxColorValue = 0;

    //public abstract void read();
    public TreeMap<Coordinate, Colormodel> getPixel() {
        return this.pixel;
    }

    public TreeMap<Coordinate, Colormodel> filter(Filter filter) {
        try {
            this.pixel = filter.filter(this.pixel);
        }
        catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
