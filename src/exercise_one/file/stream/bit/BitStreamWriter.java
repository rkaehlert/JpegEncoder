package exercise_one.file.stream.bit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;
import exercise_one.model.stream.bit.utility.BitUtilityAppend;
import exercise_one.model.stream.bit.utility.BitUtilityShiftLeft;

public class BitStreamWriter extends FileOutputStream {
	
	private static final int MAX_BUFFER_BIT_SIZE = 200;
	
	BigInteger buffer = null;
	
	public BitStreamWriter(File file) throws FileNotFoundException {
		super(file);
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
			super.write(ConverterToByte.convert(this.buffer));
			this.reset();
		}
	}
	
	public void flush() throws IOException{
		if(this.buffer != null){
			super.write(ConverterToByte.convert(this.buffer));
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
