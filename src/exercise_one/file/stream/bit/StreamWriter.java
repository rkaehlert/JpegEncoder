package exercise_one.file.stream.bit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class StreamWriter extends FileOutputStream{

	public StreamWriter(File file) throws FileNotFoundException {
		super(file);
	}
	
}
