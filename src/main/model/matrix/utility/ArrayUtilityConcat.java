package main.model.matrix.utility;

import java.math.BigInteger;

import main.converter.ConverterToByte;

public class ArrayUtilityConcat {

	public static byte[] concat(byte[] source, int sourcePos, int length){
		byte[] returnValue = new byte[length];
		System.arraycopy(source, sourcePos, returnValue, 0, length);
		return returnValue;
	}
	
	public static byte[] concat(BigInteger source, int sourcePos){
		byte[] byteToWrite = ConverterToByte.convert(source);
		return ArrayUtilityConcat.concat(byteToWrite, sourcePos, byteToWrite.length-1);
	}
	
}
