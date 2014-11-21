package model.huffman.tree;

import java.util.Arrays;
import java.util.List;

import model.comparator.huffman.ComparatorTreeDepth;


public abstract class Tree  {

	private int frequency = 0;
	private int depth = 0;

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void increaseDepth(int number) {
		this.depth = this.depth + number;
	}

	public boolean isBigger(Tree t2){
		return this.depth > t2.getDepth();
	}

	public static List<Tree> sortByDepth(Tree ... trees) {
		List<Tree> returnValue = Arrays.asList(trees);
		returnValue.sort(new ComparatorTreeDepth());
		return returnValue;
	}
	
	public static Tree replaceLeafWithMaximumDepth(Tree tree, Tree root){
		if(tree instanceof Leaf){
			Node node = new Node(tree, null, tree.getFrequency());
			((Node)root).setRight(node);
			return tree;
		}else if(tree instanceof Node){
			return Tree.replaceLeafWithMaximumDepth(((Node) tree).getRight(), tree);
		}
		return null;
	}
}
