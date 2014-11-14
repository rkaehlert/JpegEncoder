package exercise_one.encoder.huffman;

import java.util.HashMap;
import java.util.Map;

import exercise_one.model.huffman.tree.Leaf;
import exercise_one.model.huffman.tree.Node;
import exercise_one.model.huffman.tree.Tree;

public class CollectionSymbol extends HashMap<Integer, String> {

	public Map<Integer, String> set(Tree root){
		this.clear();
		this.set(root, "");
		return this;
	}
	
	private void set(Tree tree, String path){
		if(tree instanceof Leaf){
			this.put(((Leaf)tree).getNumber(), path);
		}
		if(tree instanceof Node){
			this.set(((Node)tree).getLeft(), path + "0");
			this.set(((Node)tree).getRight(), path + "1");
		}
	}
	
}
