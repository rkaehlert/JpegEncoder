package exercise_one.model.matrix.utility;

import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;

public class ArrayUtilityConcat {

	public static byte[] concat(byte[] source, int sourcePos, int length){
		byte[] returnValue = new byte[length];
		System.arraycopy(source, sourcePos, returnValue, 0, length);
		return returnValue;
	}
	
	public static byte[] concat(BigInteger source, int sourcePos){
		byte[] byteToWrite = ConverterToByte.convert(source);
		byte[] returnValue = new byte[byteToWrite.length-1];
		System.arraycopy(byteToWrite, sourcePos, returnValue, 0, byteToWrite.length-1);
		return returnValue;
	}
	
}
