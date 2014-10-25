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
	
	public static BigInteger append(BigInteger source, BigInteger[] appendValue){
		BigInteger returnValue = source;
		for(int index = 0; index < appendValue.length; index++){
			BitUtilityAppend.append(source, appendValue[index]);
			/*source = BitUtilityShiftLeft.shiftByte(source);
			returnValue = source | appendValue[index];*/
		}
		return returnValue;
	}
	
}
