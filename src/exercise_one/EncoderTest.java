package exercise_one;

import java.util.ArrayList;
import java.util.List;

import exercise_one.decoder.huffman.Decoder;
import exercise_one.encoder.huffman.CollectionSymbol;
import exercise_one.encoder.huffman.Encoder;
import exercise_one.logger.LoggerMap;
import exercise_one.logger.LoggerText;



public class EncoderTest {

	public static void main(String[] args){
		LoggerMap loggerMap = new LoggerMap();
		Decoder decoder = new Decoder();
		List<Integer> values = new ArrayList<Integer>();
		Encoder encoder = new Encoder();
		
		values.add(5);
		values.add(5);
		values.add(2);
		values.add(7);
		values.add(3);
		values.add(5);
		values.add(2);
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(6);
		values.add(7);
		values.add(7);
		
		String encoded = encoder.encode(values);
		LoggerText.log("---------------codiert-----------------------");
		LoggerText.log(values.toString() + " wurde umgewandelt in: " + encoded);
		LoggerText.log("---------------------------------------------");
		
		String decoded = decoder.decode(encoder.getTree(), encoded);
		
		LoggerText.log("---------------decodiert---------------------");
		LoggerText.log(encoded + " wurde umgewandelt in: " + decoded);
		LoggerText.log("---------------------------------------------");
		
		loggerMap.<Integer,String>log(new CollectionSymbol().set(encoder.getTree()));
	}
	
}
