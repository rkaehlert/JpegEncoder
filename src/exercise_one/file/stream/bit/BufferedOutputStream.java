package exercise_one.file.stream.bit;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;
import exercise_one.file.stream.EnumBitMask;
import exercise_one.file.stream.Stream;
import exercise_one.model.matrix.utility.ArrayUtilityConcat;

public class BufferedOutputStream extends Stream {
	
	private	StreamWriter writer;
	
	public BufferedOutputStream(StreamWriter writer){
		this.writer = writer;
	}
	
	public void append(EnumBitMask mask) throws IOException{
		super.append(mask);
		this.write();
	}
	
	public void append(BigInteger appendValue) throws IOException{
		super.append(appendValue);
		this.write();
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		super.append(appendValue);
		this.write();
	}
	
	public void write() throws IOException{
		if(super.getBuffer().bitCount() > Stream.MAX_BUFFER_BIT_SIZE){
			//stream has a 0 byte at first position. dont know why so i cut it off ^^
			byte[] byteToWrite = ArrayUtilityConcat.concat(super.getBuffer(), 1);
			this.writer.write(byteToWrite);
			super.reset();
		}
	}
	
	public void flush() throws IOException{
		if(super.getBuffer() != null){
			//stream has a 0 byte at first position. dont know why so i cut it off ^^
			byte[] byteToWrite = ArrayUtilityConcat.concat(super.getBuffer(), 1);
			this.writer.write(byteToWrite);
			super.reset();
		}
	}

}
