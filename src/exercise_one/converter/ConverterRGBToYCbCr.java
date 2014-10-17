package exercise_one.converter;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.YCbCr;

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

}
