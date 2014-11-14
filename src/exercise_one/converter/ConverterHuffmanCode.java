package exercise_one.converter;

import java.util.List;
import java.util.Map;

import exercise_one.encoder.huffman.CollectionSymbol;
import exercise_one.model.huffman.tree.Tree;

public class ConverterHuffmanCode implements Converter<String> {

	public String convert(Tree tree, List<Object> input){
		String output = "";
		Map<Object, String> symbolMapping = new CollectionSymbol().set(tree);
		for(Object value : input){
			output = output + symbolMapping.get(value);
		}
		return output;
	}
	
}
