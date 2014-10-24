package exercise_one.converter;

import java.math.BigInteger;

import exercise_one.exception.NotYetImplementedException;

public class ConverterToByte implements Converter<Byte> {

	public static byte[] convert(int value){
		return BigInteger.valueOf(value).toByteArray();
	}
	
	public static byte[] convert(BigInteger value){
		return value.toByteArray();
	}

	@Override
	public Byte convert() {
        try
        {
            throw new NotYetImplementedException();
        }
        catch (NotYetImplementedException e)
        {
            e.printStackTrace();
        }
        return null;
	}
	
}
