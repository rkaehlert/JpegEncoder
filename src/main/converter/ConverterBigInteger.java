package main.converter;

import java.math.BigInteger;

public class ConverterBigInteger implements Converter {
	
	public static BigInteger convert(int value) {
		return BigInteger.valueOf(value);
	}
	
	

}
