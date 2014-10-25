package exercise_one.converter;

import java.math.BigInteger;

import exercise_one.exception.NotYetImplementedException;

public class ConverterBigInteger implements Converter {

	@Override
	public BigInteger convert() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static BigInteger convert(int value) {
		return BigInteger.valueOf(value);
	}
	
	

}
