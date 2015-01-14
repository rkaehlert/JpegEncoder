package exercise_one;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import main.converter.ConverterRunLengthEncoding;
import main.encoder.huffman.EncoderHuffmanTree;
import main.file.image.JPEGImage;
import main.formatter.FormatterRunLengthEncodingByCategory;
import main.model.color.Colormodel;
import main.model.color.YCbCr;
import main.model.matrix.Coordinate;

import org.junit.Before;
import org.junit.Test;


public class TestJPEGImage {

	public TreeMap<Coordinate, Colormodel> pixel = new TreeMap<Coordinate, Colormodel>();
	public JPEGImage image = new JPEGImage();
	
	double[][] outputY = {
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
			{35,45,55,65,75,85,95,105,115,125,135,145,155,165,175,185},
	};
	double[][] outputCb = {
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			{10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160},
			
	};
	double[][] outputCr = {
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},
			{5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80},

	};
	
	double[][] output8x8 = {
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40},
			{5,10,15,20,25,30,35,40}
	};
	
	
	@Before
	public void setup(){
		image.setHeight(16);
		image.setWidth(16);
		pixel.put(new Coordinate(0,0), new YCbCr(35, 10, 5));
		pixel.put(new Coordinate(0,1), new YCbCr(45, 20, 10));
		pixel.put(new Coordinate(0,2), new YCbCr(55, 30, 15));
		pixel.put(new Coordinate(0,3), new YCbCr(65, 40, 20));
		pixel.put(new Coordinate(0,4), new YCbCr(75, 50, 25));
		pixel.put(new Coordinate(0,5), new YCbCr(85, 60, 30));
		pixel.put(new Coordinate(0,6), new YCbCr(95, 70, 35));
		pixel.put(new Coordinate(0,7), new YCbCr(105, 80, 40));
		pixel.put(new Coordinate(0,8), new YCbCr(115, 90, 45));
		pixel.put(new Coordinate(0,9), new YCbCr(125, 100, 50));
		pixel.put(new Coordinate(0,10), new YCbCr(135, 110, 55));
		pixel.put(new Coordinate(0,11), new YCbCr(145, 120, 60));
		pixel.put(new Coordinate(0,12), new YCbCr(155, 130, 65));
		pixel.put(new Coordinate(0,13), new YCbCr(165, 140, 70));
		pixel.put(new Coordinate(0,14), new YCbCr(175, 150, 75));
		pixel.put(new Coordinate(0,15), new YCbCr(185, 160, 80));
		pixel.put(new Coordinate(1,0), new YCbCr(35, 10, 5));
		pixel.put(new Coordinate(1,1), new YCbCr(45, 20, 10));
		pixel.put(new Coordinate(1,2), new YCbCr(55, 30, 15));
		pixel.put(new Coordinate(1,3), new YCbCr(65, 40, 20));
		pixel.put(new Coordinate(1,4), new YCbCr(75, 50, 25));
		pixel.put(new Coordinate(1,5), new YCbCr(85, 60, 30));
		pixel.put(new Coordinate(1,6), new YCbCr(95, 70, 35));
		pixel.put(new Coordinate(1,7), new YCbCr(105, 80, 40));
		pixel.put(new Coordinate(1,8), new YCbCr(115, 90, 45));
		pixel.put(new Coordinate(1,9), new YCbCr(125, 100, 50));
		pixel.put(new Coordinate(1,10), new YCbCr(135, 110, 55));
		pixel.put(new Coordinate(1,11), new YCbCr(145, 120, 60));
		pixel.put(new Coordinate(1,12), new YCbCr(155, 130, 65));
		pixel.put(new Coordinate(1,13), new YCbCr(165, 140, 70));
		pixel.put(new Coordinate(1,14), new YCbCr(175, 150, 75));
		pixel.put(new Coordinate(1,15), new YCbCr(185, 160, 80));
		image.setPixel(pixel);
	}

	@Test
	public void testQuantization(){

		Integer[] input = new Integer[]{
				0,0,13,0,0,14,15,16,17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,18,19,10,20,13,53,43,56,54,0,0,0,0,0,32,76,87,32,435
		};
		List<Integer[]> temp = new ArrayList<Integer[]>();
		temp.add(input);
		
    	List<Integer[]> lstRunLengthEncodedZickZackCr = ConverterRunLengthEncoding.convert(temp);
    	
    	List<Integer[]> rleHuffmanCodesY = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackCr);
    	
    	EncoderHuffmanTree encoder = new EncoderHuffmanTree();
    	encoder.encodeJPEG(rleHuffmanCodesY);
    	encoder.getTree();
    	//new LoggerMap<Tree, String>().log(encoder.getPathCollection());
    }
}
