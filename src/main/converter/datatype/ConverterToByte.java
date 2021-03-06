package main.converter.datatype;

import java.math.BigInteger;
import java.util.ArrayList;

import main.converter.Converter;

public class ConverterToByte implements Converter<Byte> {

    public static byte[] convert(int value) {
        return BigInteger.valueOf(value).toByteArray();
    }

    public static byte[] convert(BigInteger value) {
        return value.toByteArray();
    }

    public static byte[] convertPositiveIntToByteWithExactByteNumber(int value, int byteLimit) {
        if (value > Math.pow(2, byteLimit * 8) || value < 0) {
            throw new IllegalArgumentException("Wert passt nicht in angegebene Bytes oder ist negativ.");
        }

        ArrayList<Byte> liste = new ArrayList<>();
        byte[] converted = BigInteger.valueOf(value).toByteArray();

        for (byte b : converted) {
            if (b != (byte) 0x00) {
                liste.add(b);
            }
        }

        int leadingZeroeBytes = byteLimit - liste.size();
        byte[] data = new byte[liste.size() + leadingZeroeBytes];
        for (int i = 0; i < leadingZeroeBytes; i++) {
            data[i] = (byte) 0x00;
        }
        for (int i = leadingZeroeBytes; i < data.length; i++) {
            data[i] = (byte) liste.get(i - leadingZeroeBytes);
        }
        return data;
    }
    
    public static byte[] convert(Double[] value){
    	byte[] output = new byte[value.length];
    	for(int index = 0; index < value.length; index++){
    		output[index] = Double.valueOf(value[index]).byteValue();
    	}
    	return output;
    }
    
    public static byte[] convert(Integer[] value){
    	byte[] output = new byte[value.length];
    	for(int index = 0; index < value.length; index++){
    		output[index] = Integer.valueOf(value[index]).byteValue();
    	}
    	return output;
    }

}
