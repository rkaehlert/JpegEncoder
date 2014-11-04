package exercise_one.converter;

import java.math.BigInteger;

import exercise_one.exception.NotYetImplementedException;
import java.util.ArrayList;

public class ConverterToByte implements Converter<Byte> {

    public static byte[] convert(int value) {
        return BigInteger.valueOf(value).toByteArray();
    }

    public static byte[] convert(BigInteger value) {
        return value.toByteArray();
    }

    public static byte[] convertPositiveIntToByteWithExactByteNumber(int value, int byteLimit) {
        if (value > Math.pow(byteLimit * 8, 2) || value < 0) {
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

    @Override
    public Byte convert() {
        try {
            throw new NotYetImplementedException();
        }
        catch (NotYetImplementedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
