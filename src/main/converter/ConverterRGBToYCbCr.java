package main.converter;

import java.util.Map;
import java.util.TreeMap;

import main.exception.image.ConverterException;
import main.model.color.ColorChannel;
import main.model.color.Colormodel;
import main.model.color.RGB;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

public class ConverterRGBToYCbCr implements Converter<YCbCr> {

    public static final boolean REDUCED_Y_CHANNEL = false;
    public static final boolean REDUCED_CB_CHANNEL = true;
    public static final boolean REDUCED_CR_CHANNEL = true;

    public static TreeMap<Coordinate, Colormodel> convert(TreeMap<Coordinate, Colormodel> pixel) {
        TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<>();
        try {
            for (Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()) {
                if (entry.getValue() instanceof RGB) {
                    RGB rgb = (RGB) entry.getValue();
                    YCbCr ycbcr = new YCbCr();
                    ycbcr.setY(new ColorChannel<Double>(0 + ((0.299 * rgb.getRed()) + (0.587 * rgb.getGreen()) + (0.114 * rgb.getBlue()))));
                    ycbcr.setCb(new ColorChannel<Double>(128.0 + ((-0.1687 * rgb.getRed()) + (-0.3312 * rgb.getGreen()) + (0.5 * rgb.getBlue()))));
                    ycbcr.setCr(new ColorChannel<Double>(128.0 + ((0.5 * rgb.getRed()) + (-0.4186 * rgb.getGreen()) + (-0.0813 * rgb.getBlue()))));
                    ycbcr.getYChannel().setReduced(REDUCED_Y_CHANNEL);
                    ycbcr.getCrChannel().setReduced(REDUCED_CR_CHANNEL);
                    ycbcr.getCbChannel().setReduced(REDUCED_CB_CHANNEL);
                    returnValue.put(entry.getKey(), ycbcr);
                }
                else {
                    throw new ConverterException("Konvertierung aufgrund von falschem Farbmodell nicht moeglich");
                }
            }
        }
        catch (ConverterException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

}