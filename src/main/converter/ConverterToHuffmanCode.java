package main.converter;

import java.util.List;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Tree;

public class ConverterToHuffmanCode implements Converter<String> {

	public String convert(Tree tree, List<Object> input){
		String output = "";
		CollectionSymbol symbolMapping = new CollectionSymbol();
		symbolMapping.set(tree);
		for(Object value : input){
			output = output + symbolMapping.get(value);
		}
		return output;
	}
	
}
