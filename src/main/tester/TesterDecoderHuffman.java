package main.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.converter.ConverterHuffmanTreeToCollectionSymbol;
import main.converter.ConverterToHuffmanCode;
import main.decoder.huffman.DecoderHuffmanTree;
import main.encoder.huffman.EncoderHuffmanTree;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class TesterDecoderHuffman {

	public static void main(String[] args){
		LoggerMap<Tree, String> loggerMap = new LoggerMap<Tree,String>();
		DecoderHuffmanTree decoder = new DecoderHuffmanTree();
		List<Object> characterSymbols = new ArrayList<Object>();
		List<Object> numberSymbols = new ArrayList<Object>();
		EncoderHuffmanTree encoder = new EncoderHuffmanTree();
		
		characterSymbols = Arrays.asList("a","a","a","b","c","c","c","d","d","d","d","e","f","f","f","f","f","g","z","z","a","h","h","b","u","z","o","o","e","n","n","m","e","e","a","a","g","g","z","z");
		numberSymbols = Arrays.asList(1,1,2,2,2,3,3,5,5,5,6,7,7,7);
		
		encoder.encode(characterSymbols);
		String encoded = new ConverterToHuffmanCode().convert(encoder.getTree(), characterSymbols);

		loggerMap.log(ConverterHuffmanTreeToCollectionSymbol.convert(encoder.getTree()));
		TesterEncoderHuffmanOriginal.logSymbols(characterSymbols, encoded);
		
		String decoded = decoder.decode(encoder.getTree(), encoded);
		TesterEncoderHuffmanOriginal.logSymbols(encoded, decoded);
		
		encoder.encode(numberSymbols);
		encoded = new ConverterToHuffmanCode().convert(encoder.getTree(), numberSymbols);
		loggerMap.log(ConverterHuffmanTreeToCollectionSymbol.convert(encoder.getTree()));
		TesterEncoderHuffmanOriginal.logSymbols(characterSymbols, encoded);
		
		decoded = decoder.decode(encoder.getTree(), encoded);
		TesterEncoderHuffmanOriginal.logSymbols(encoded, decoded);
	}
}