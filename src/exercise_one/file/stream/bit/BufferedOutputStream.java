package exercise_one.file.stream.bit;

import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;
import exercise_one.file.stream.EnumBitMask;

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
			byte[] byteToWrite = ConverterToByte.convert(super.getBuffer());
			//stream has a 0 byte at first position. dont know why so i cut it off ^^
			System.arraycopy(byteToWrite, 1, byteToWrite, 0, byteToWrite.length-1);
			this.writer.write(byteToWrite);
			super.reset();
		}
	}
	
	public void flush() throws IOException{
		if(super.getBuffer() != null){
			this.writer.write(ConverterToByte.convert(super.getBuffer()));
			super.reset();
		}
	}

}
