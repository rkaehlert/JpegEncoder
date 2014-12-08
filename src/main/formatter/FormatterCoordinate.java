package main.formatter;

import java.text.DecimalFormat;

import main.exception.common.ExceptionNotYetImplemented;
import main.model.color.YCbCr;

public class FormatterCoordinate implements Formatter {

    DecimalFormat format = new DecimalFormat(" ###.00;-###.00");
	
	@Override
	public String format() {
		try{
			throw new ExceptionNotYetImplemented();
		}catch(ExceptionNotYetImplemented e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String format(YCbCr ycbcr) {
        String y = ycbcr.getYChannel().getValue() == null ? null : format.format(ycbcr.getYChannel().getValue());
        String cb = ycbcr.getCbChannel().getValue() == null ? null : format.format(ycbcr.getCbChannel().getValue());
        String cr = ycbcr.getCrChannel().getValue() == null ? null : format.format(ycbcr.getCrChannel().getValue());
        return "(" + y + "," + cb + "," + cr + ")";
	}

}
