package main.tester;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.file.resource.UtilityResourcePath;

public class TesterJpegEncoder2 {

	public static void main(String[] args){
		try {
			FileOutputStream stream = new FileOutputStream(new File("C:\\Users\\Robin\\Desktop\\image.jpeg"));
			FileInputStream fis = new FileInputStream(new File(UtilityResourcePath.getPath("test_112.ppm")));
			BufferedImage bimg = ImageIO.read(fis);
			JpegEncoder encoder = new JpegEncoder(bimg, 12, stream);
			encoder.Compress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
