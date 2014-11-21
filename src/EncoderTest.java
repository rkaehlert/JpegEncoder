

import java.util.ArrayList;
import java.util.List;

import decoder.huffman.Decoder;
import encoder.huffman.CollectionSymbol;
import encoder.huffman.Encoder;
import logger.LoggerMap;
import logger.LoggerText;



public class EncoderTest {

	public static void main(String[] args){
		LoggerMap<Object, String> loggerMap = new LoggerMap<Object,String>();
		Decoder decoder = new Decoder();
		List<Object> values = new ArrayList<Object>();
		Encoder encoder = new Encoder();
		
		values.add("a");
		values.add("a");
		values.add("a");
		values.add("b");
		values.add("c");
		values.add("c");
		values.add("c");
		values.add("d");
		values.add("d");
		values.add("e");
		values.add("f");
		values.add("f");
		values.add("z");
				
		String encoded = encoder.encode(values);
		LoggerText.log("---------------codiert-----------------------");
		LoggerText.log(values.toString() + " wurde umgewandelt in: " + encoded);
		LoggerText.log("---------------------------------------------");
		
		String decoded = decoder.decode(encoder.getTree(), encoded);
		
		LoggerText.log("---------------decodiert---------------------");
		LoggerText.log(encoded + " wurde umgewandelt in: " + decoded);
		LoggerText.log("---------------------------------------------");
		
		loggerMap.log(new CollectionSymbol().set(encoder.getTree()));
		
		values.clear();

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
		
		encoded = encoder.encode(values);
		LoggerText.log("---------------codiert-----------------------");
		LoggerText.log(values.toString() + " wurde umgewandelt in: " + encoded);
		LoggerText.log("---------------------------------------------");
		
		decoded = decoder.decode(encoder.getTree(), encoded);
		
		LoggerText.log("---------------decodiert---------------------");
		LoggerText.log(encoded + " wurde umgewandelt in: " + decoded);
		LoggerText.log("---------------------------------------------");
		
		loggerMap.log(new CollectionSymbol().set(encoder.getTree()));
		
	}
	
}
