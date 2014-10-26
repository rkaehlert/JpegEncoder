package exercise_one.file.stream.bit;

import java.io.IOException;

import exercise_one.converter.ConverterBigInteger;
import exercise_one.file.stream.Stream;

public class BufferedInputStream extends Stream {
	
	private	StreamReader reader;
	
	public BufferedInputStream(StreamReader reader){
		this.reader = reader;
	}
		
	public void read() throws IOException{
		int currentValue = 0;
		while((currentValue = this.reader.read()) != -1){
			super.append(ConverterBigInteger.convert(currentValue));
		}
	}

}
