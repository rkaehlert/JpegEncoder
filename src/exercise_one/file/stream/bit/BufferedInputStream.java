package exercise_one.file.stream.bit;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterBigInteger;
import exercise_one.model.stream.bit.utility.BitUtilityAppend;
import exercise_one.model.stream.bit.utility.BitUtilityShiftLeft;

public class BufferedInputStream {
	
	private static final int MAX_BUFFER_BIT_SIZE = 200;
	
	private	StreamReader reader;
	private BigInteger buffer = null;
	
	public BufferedInputStream(StreamReader reader){
		this.reader = reader;
	}
	
	public void append(BigInteger appendValue) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
	}
	
	public int getByte(int startingPositionOfByte){
		return this.getBit(startingPositionOfByte, Byte.SIZE);
	}
	
	public int getBit(int startingPosition, int offset){
		int shifted = buffer.intValue() >>> startingPosition;
		int mask = BitUtilityShiftLeft.shift(BigInteger.valueOf(1), offset).intValueExact()-1;
	    return shifted & mask;
	}
	
	public void read() throws IOException{
		int currentValue = 0;
		while((currentValue = this.reader.read()) != -1){
			this.append(ConverterBigInteger.convert(currentValue));
		}
	}
	
	public void reset(){
		this.buffer = null;
	}

	public BigInteger getBuffer() {
		return buffer;
	}

	public void setBuffer(BigInteger buffer) {
		this.buffer = buffer;
	}
}
