package exercise_one.file.stream.bit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BitStreamReader extends FileInputStream{

	public BitStreamReader(File file) throws FileNotFoundException {
		super(file);
	}
	
}
