package exercise_one.file.stream.bit;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;
import exercise_one.model.stream.bit.utility.BitUtilityAppend;
import exercise_one.model.stream.bit.utility.BitUtilityShiftLeft;

public class BufferedOutputStream {
	
	private static final int MAX_BUFFER_BIT_SIZE = 200;
	
	private	BitStreamWriter writer;
	private BigInteger buffer = null;
	
	public BufferedOutputStream(BitStreamWriter writer){
		this.writer = writer;
	}
	
	public void append(BigInteger appendValue) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
		this.write();
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		buffer = BitUtilityAppend.append(this.buffer, appendValue);
		this.write();
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
	
	public void write() throws IOException{
		if(buffer.bitCount() > MAX_BUFFER_BIT_SIZE){
			byte[] byteToWrite = ConverterToByte.convert(this.buffer);
			//stream has a 0 byte at first position. dont know why so i cut it off ^^
			System.arraycopy(byteToWrite, 1, byteToWrite, 0, byteToWrite.length-1);
			this.writer.write(byteToWrite);
			this.reset();
		}
	}
	
	public void flush() throws IOException{
		if(this.buffer != null){
			this.writer.write(ConverterToByte.convert(this.buffer));
			this.reset();
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
