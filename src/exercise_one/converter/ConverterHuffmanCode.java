package exercise_one.converter;

import java.util.List;
import java.util.Map;

import exercise_one.encoder.huffman.CollectionSymbol;
import exercise_one.model.huffman.tree.Tree;

public class ConverterHuffmanCode implements Converter<String> {

	public String convert(Tree tree, List<Integer> input){
		String output = "";
		Map<Integer, String> symbolMapping = new CollectionSymbol().set(tree);
		for(Integer number : input){
			output = output + symbolMapping.get(number);
		}
		return output;
	}
	
}
