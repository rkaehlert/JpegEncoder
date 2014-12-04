package main.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.converter.ConverterHuffmanTreeToPath;
import main.encoder.huffman.CollectionSymbol;
import main.encoder.huffman.EncoderHuffmanTree;
import main.exception.common.ExceptionInvalidParameter;
import main.formatter.FormatterRightGrowingTree;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class TesterEncoderHuffmanRightGrowing {
	
	public static void main(String[] args){
		try {
			
			LoggerMap<Tree, String> loggerMap = new LoggerMap<Tree,String>();
			List<Object> characterSymbols = new ArrayList<Object>();
			List<Object> numberSymbols = new ArrayList<Object>();
			EncoderHuffmanTree encoder = new EncoderHuffmanTree();
			
			characterSymbols = Arrays.asList("a","a","a","b","c","c","c","d","d","d","d","e","f","f","f","f","f","g","z","z");
			numberSymbols = Arrays.asList(1,1,2,2,2,3,3,5,5,5,6,7,7,7);
			
			encoder.encode(characterSymbols);
			CollectionSymbol collectionSymbol = ConverterHuffmanTreeToPath.convert(encoder.getTree());
			collectionSymbol = collectionSymbol.sort();
			
			Tree tree = new FormatterRightGrowingTree().format(collectionSymbol);
			loggerMap.log(new CollectionSymbol().set(tree)); 
			
			encoder.encode(numberSymbols);
			collectionSymbol.set(encoder.getTree());
			collectionSymbol = collectionSymbol.sort();
			
			tree = new FormatterRightGrowingTree().format(collectionSymbol);
			loggerMap.log(new CollectionSymbol().set(tree)); 
			
		} catch (ExceptionInvalidParameter e) {
			e.printStackTrace();
		}

	}

}
