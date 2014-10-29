package exercise_one.file.stream.bit;

import java.io.IOException;
import java.math.BigInteger;

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
	}
	
	public void append(BigInteger appendValue) throws IOException{
		super.append(appendValue);
	}
	
	public void append(BigInteger appendValue[]) throws IOException{
		super.append(appendValue);
	}
	
	public void write() throws IOException{
		//stream has a 0 byte at first position. dont know why so i cut it off ^^
		//stream writer is only writing byte data. if data is shorter than 8 bit it is ignored
		byte[] byteToWrite = ArrayUtilityConcat.concat(super.getBuffer(), 1);
		this.writer.write(byteToWrite);
		super.reset();
	}

}
