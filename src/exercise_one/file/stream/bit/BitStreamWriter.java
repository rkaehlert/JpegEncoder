package exercise_one.file.stream.bit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BitStreamWriter extends FileOutputStream{

	public BitStreamWriter(File file) throws FileNotFoundException {
		super(file);
	}
	
}
