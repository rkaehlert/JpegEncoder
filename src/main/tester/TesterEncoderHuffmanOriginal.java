package main.tester;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.converter.ConverterHuffmanTreeToPath;
import main.encoder.huffman.EncoderHuffmanTree;
import main.logger.LoggerMap;
import main.logger.LoggerText;
import main.model.huffman.tree.Tree;



public class TesterEncoderHuffmanOriginal {
	
	public static void main(String[] args){
		LoggerMap<Tree, String> loggerMap = new LoggerMap<Tree,String>();
		List<Object> characterSymbols = new ArrayList<Object>();
		List<Object> numberSymbols = new ArrayList<Object>();
		EncoderHuffmanTree encoder = new EncoderHuffmanTree();
		
		characterSymbols = Arrays.asList("a","a","a","b","c","c","c","d","d","d","d","e","f","f","f","f","f","g","z","z");
		numberSymbols = Arrays.asList(1,1,2,2,2,3,3,5,5,5,6,7,7,7);
		
		String encoded = encoder.encode(characterSymbols);
		loggerMap.log(ConverterHuffmanTreeToPath.convert(encoder.getTree()));
		TesterEncoderHuffmanOriginal.logSymbols(characterSymbols, encoded);
		
		encoded = encoder.encode(numberSymbols);
		loggerMap.log(ConverterHuffmanTreeToPath.convert(encoder.getTree()));
		TesterEncoderHuffmanOriginal.logSymbols(numberSymbols, encoded);
		
	}
	
	public static void logSymbols(Object symbols, String encoded){
		LoggerText.log("---------------codiert-----------------------");
		LoggerText.log(symbols.toString() + " wurde umgewandelt in: " + encoded);
		LoggerText.log("---------------------------------------------");
	}
	
}
