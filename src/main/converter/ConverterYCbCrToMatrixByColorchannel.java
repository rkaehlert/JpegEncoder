package main.converter;

import java.util.Map;
import java.util.TreeMap;

import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;
import main.tester.TesterJpegEncoder;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterYCbCrToMatrixByColorchannel {

    public static Array2DRowRealMatrix convertY(TreeMap<Coordinate, Colormodel> pixel, int width, int height) {
        Array2DRowRealMatrix output = new Array2DRowRealMatrix(height, width);
        for (Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()) {
            YCbCr ycbcr = (YCbCr) currentEntry.getValue();
            if (ycbcr.getY() != null) {
                output.setEntry(currentEntry.getKey().getY(), currentEntry.getKey().getX(), ycbcr.getY());
            }
        }
        return output;
    }

    public static Array2DRowRealMatrix convertCb(TreeMap<Coordinate, Colormodel> pixel, int width, int height) {
        Array2DRowRealMatrix output = new Array2DRowRealMatrix(height/TesterJpegEncoder.subsample, width/TesterJpegEncoder.subsample);
        for (Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()) {
            YCbCr ycbcr = (YCbCr) currentEntry.getValue();
            if (ycbcr.getCb() != null) {
                output.setEntry(currentEntry.getKey().getY()/TesterJpegEncoder.subsample, currentEntry.getKey().getX()/TesterJpegEncoder.subsample, ycbcr.getCb());
            }
        }
        return output;
    }

    public static Array2DRowRealMatrix convertCr(TreeMap<Coordinate, Colormodel> pixel, int width, int height) {
        Array2DRowRealMatrix output = new Array2DRowRealMatrix(height/TesterJpegEncoder.subsample, width/TesterJpegEncoder.subsample);
        for (Map.Entry<Coordinate, Colormodel> currentEntry : pixel.entrySet()) {
            YCbCr ycbcr = (YCbCr) currentEntry.getValue();
            if (ycbcr.getCr() != null) {
                output.setEntry(currentEntry.getKey().getY()/TesterJpegEncoder.subsample, currentEntry.getKey().getX()/TesterJpegEncoder.subsample, ycbcr.getCr());
            }
        }
        return output;
    }
}
