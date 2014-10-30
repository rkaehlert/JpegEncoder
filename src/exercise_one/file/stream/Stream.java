package exercise_one.file.stream;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.model.stream.bit.utility.BitUtilityAppend;
import exercise_one.model.stream.bit.utility.BitUtilityShiftLeft;

public class Stream {

	private static final Integer BUFFER_MAX_SIZE = 200;
	
	private BigInteger buffer = null;
	private BigInteger value = null;
	
	public void append() throws IOException{
		if(buffer.bitCount() >= BUFFER_MAX_SIZE){
			value = BitUtilityAppend.append(value, buffer);
			this.reset();
		}
	}
	
	public void append(EnumBitMask mask) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, 1, mask.getValue());
		this.append();
	}
	
	public void append(BigInteger appendValue) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
		this.append();
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
		this.append();
	}
	
	/*public void append(byte appendValue[]) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
		this.write();
	}*/
	
	public int getByte(int startingPositionOfByte){
		return this.getBit(startingPositionOfByte, Byte.SIZE);
	}
	
	public int getBit(int startingPosition, int offset){
		int shifted = buffer.intValue() >>> startingPosition;
		int mask = BitUtilityShiftLeft.shift(BigInteger.valueOf(1), offset).intValueExact()-1;
	    return shifted & mask;
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

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}
	
}
