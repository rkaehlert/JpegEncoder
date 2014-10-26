package exercise_one.model.stream.bit.utility;

import java.math.BigInteger;

public class BitUtilityAppend {
	
	public static BigInteger append(BigInteger source, BigInteger appendValue){
		if(source == null){
			source = BigInteger.valueOf(0);
		}
		BigInteger returnValue = BitUtilityShiftLeft.shiftByte(source);
		return returnValue.or(appendValue);
	}
	
	public static BigInteger append(BigInteger source, int offset, int mask){
		if(source == null){
			source = BigInteger.valueOf(0);
		}
		BigInteger returnValue = BitUtilityShiftLeft.shift(source, offset);
		return returnValue.or(BigInteger.valueOf(mask));
	}
	
	public static BigInteger append(BigInteger source, BigInteger[] appendValue){
		BigInteger returnValue = source == null ? BigInteger.valueOf(0) : source;
		for(int index = 0; index < appendValue.length; index++){
			returnValue = BitUtilityAppend.append(source, appendValue[index]);
		}
		return returnValue;
	}
	
	public static BigInteger append(BigInteger source, byte[] appendValue){
		BigInteger returnValue = source == null ? BigInteger.valueOf(0) : source;
		for(int index = 0; index < appendValue.length; index++){
			returnValue = BitUtilityAppend.append(returnValue, BigInteger.valueOf(appendValue[index]));
		}
		return returnValue;
	}
	
}
