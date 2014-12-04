package main.tester;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.converter.ConverterHuffmanTreeLengthLimited;
import main.converter.ConverterHuffmanTreeToPath;
import main.encoder.huffman.EncoderHuffmanTree;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;



public class TesterEncoderHuffmanMaxLength {
	
	private static final int COUNT = 5;
	
	public static void main(String[] args){
		LoggerMap<Tree, String> loggerMap = new LoggerMap<Tree,String>();
		List<Object> characterSymbols = new ArrayList<Object>();
		List<Object> numberSymbols = new ArrayList<Object>();
		EncoderHuffmanTree encoder = new EncoderHuffmanTree();
		
		characterSymbols = Arrays.asList("40", "43", "4", "15", "48", "23", "9", "25", "25", "33", "35", "0", "0", "7", "12", "30", "38", "34", "28", "43", "47", "12", "17", "47", "13", "47", "32", "45", "23", "48", "41", "34", "0", "26", "19", "42", "15", "9", "6", "2", "32", "35", "42", "0", "7", "46", "42", "12", "5", "2", "16", "34", "4", "31", "37", "24", "0", "17", "20", "16", "38", "9", "10", "26", "19", "35", "46", "36", "41", "46", "48", "45", "12", "47", "4", "16", "38", "42", "14", "15", "6", "31", "42", "11", "42", "1", "36", "16", "22", "28", "0", "17", "15", "14", "49", "42", "45", "1", "39", "13");
		//characterSymbols = Arrays.asList("a","a","a","b","c","c","c","d","d","d","d","e","f","f","f","f","f","g","z","z","a","h","h","b","u","z","o","o","e","e","e","a","a","g","g","z","z");
		
		numberSymbols = Arrays.asList(1,1,2,2,2,3,3,5,5,5,6,7,7,7);
		
		encoder.encode(characterSymbols);
		loggerMap.log(ConverterHuffmanTreeToPath.convert(encoder.getTree()));
		Tree maxLengthOptimizedTree = ConverterHuffmanTreeLengthLimited.convert(encoder.getTree(), COUNT);
		
				
		loggerMap.log(ConverterHuffmanTreeToPath.convert(maxLengthOptimizedTree));
		
		encoder.encode(numberSymbols);		
		maxLengthOptimizedTree = ConverterHuffmanTreeLengthLimited.convert(encoder.getTree(), 2);
		loggerMap.log(ConverterHuffmanTreeToPath.convert(maxLengthOptimizedTree));
	}
	
}
