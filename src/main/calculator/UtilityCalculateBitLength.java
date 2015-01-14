package main.calculator;

import java.math.BigInteger;

public class UtilityCalculateBitLength {

	public static Integer calculate(Integer value) {
		return 	new BigInteger(value.toString()).abs().bitLength();
	}

}
