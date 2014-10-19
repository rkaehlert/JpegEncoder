package exercise_one.converter;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.exception.ConverterException;
import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class ConverterRGBToYCbCr implements Converter<YCbCr> {

	@Override
	public YCbCr convert() {
		try{
			throw new NotYetImplementedException();	
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static TreeMap<Coordinate, Colormodel> convert(TreeMap<Coordinate, Colormodel> pixel){
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		try {
		    for (Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
		    	if(entry.getValue() instanceof RGB){
		            RGB rgb = (RGB) entry.getValue();
		            YCbCr ycbcr = new YCbCr();
		            ycbcr.setY(0 + ((0.299 * rgb.getRed()) + (0.587 * rgb.getGreen()) + (0.114 * rgb.getBlue())));
		            ycbcr.setCb(0.5 + ((-0.1687 * rgb.getRed()) + (-0.3312 * rgb.getGreen()) + (0.5 * rgb.getBlue())));
		            ycbcr.setCr(0.5 + ((0.5 * rgb.getRed()) + (-0.4186 * rgb.getGreen()) + (-0.0813 * rgb.getBlue())));
		            returnValue.put(entry.getKey(), ycbcr);
		    	}else{
		    		throw new ConverterException("Konvertierung aufgrund von falschem Farbmodell nicht möglich");
		    	}
		    }
		}catch(ConverterException e){
			e.printStackTrace();
		}
        return returnValue;
	}

}
