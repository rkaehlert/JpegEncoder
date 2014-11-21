package encoder.huffman;

import java.util.HashMap;
import java.util.Map;

import model.huffman.tree.Leaf;
import model.huffman.tree.Node;
import model.huffman.tree.Tree;

public class CollectionSymbol extends HashMap<Object, String> {

	public Map<Object, String> set(Tree root){
		this.clear();
		this.set(root, "");
		return this;
	}
	
	private void set(Tree tree, String path){
		if(tree instanceof Leaf){
			this.put(((Leaf)tree).getValue(), path);
		}
		if(tree instanceof Node){
			this.set(((Node)tree).getLeft(), path + "0");
			this.set(((Node)tree).getRight(), path + "1");
		}
	}
	
}
