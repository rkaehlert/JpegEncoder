package exercise_one.decoder.huffman;

import java.util.HashMap;
import java.util.Map;

import exercise_one.model.huffman.tree.Leaf;
import exercise_one.model.huffman.tree.Node;
import exercise_one.model.huffman.tree.Tree;

public class Decoder{
	
	private Map<Integer, String> paths = new HashMap<Integer, String>();
	
	public String decode(Tree tree, String code){
		String decodedValue = "";
		Tree result = tree;
		for(int index = 0; index < code.length(); index++){
			if(code.charAt(index) != '1' && code.charAt(index) != '0'){
				throw new IllegalArgumentException("nur 0 oder 1 sind als parameter zulaessig");
			}
			if(result instanceof Node){
				if(code.charAt(index) == '0'){
					result = ((Node)result).getLeft();
				}
				if(code.charAt(index) == '1'){
					result = ((Node)result).getRight();
				}
			}
			if(result instanceof Leaf){
				Object value = ((Leaf) result).getValue();
				decodedValue = decodedValue.concat(String.valueOf(value));
				result = tree;
			}
		}
		return decodedValue;			
	}
	
	

}
