package main.file.image.resource;

import java.io.IOException;

import main.exception.image.ImageException;
import main.exception.image.UnsupportedImageFormatException;
import main.file.image.JPEGImage;

public class ImageResourceReader {

    public String getFilePath(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource("main/resources/"+name).getPath();
    }

	public JPEGImage read(String string, int fillmode) {
		try {
			return new JPEGImage(this.getFilePath("test.ppm"), 20, fillmode);
		} catch (UnsupportedImageFormatException | ImageException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
