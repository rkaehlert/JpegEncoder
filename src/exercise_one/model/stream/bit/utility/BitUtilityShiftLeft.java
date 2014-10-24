package exercise_one.model.stream.bit.utility;

import java.math.BigInteger;


public class BitUtilityShiftLeft {

	public static BigInteger shift(BigInteger value, int offset){
		return value.shiftLeft(offset);
	}
	
	public static BigInteger shiftByte(BigInteger value){
		return value.shiftLeft(Byte.SIZE);
	}
	
}
