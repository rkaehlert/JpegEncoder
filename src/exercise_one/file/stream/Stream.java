package exercise_one.file.stream;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.model.stream.bit.utility.BitUtilityAppend;
import exercise_one.model.stream.bit.utility.BitUtilityShiftLeft;

public class Stream {

	private BigInteger buffer = null;
	
	public void append(EnumBitMask mask) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, 1, mask.getValue());
	}
	
	public void append(BigInteger appendValue) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
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
	
}
