package main.model.huffman.tree;

import java.util.Arrays;
import java.util.List;

import main.comparator.huffman.ComparatorTreeDepth;
import main.utility.tree.UtilityTreeManipulation;


public abstract class Tree implements Comparable<Tree> {

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
	
	public int getHeight(){
		return getHeightNew(this);
	}

	public int getHeightNew(final Tree node) {
		  if(node instanceof Node){
			  int left_height = ((Node)node).getLeft() instanceof Leaf ? getHeightNew(null) : getHeightNew(((Node)node).getLeft());
			  int right_height = ((Node)node).getRight() instanceof Leaf ?  getHeightNew(null) : getHeightNew(((Node)node).getRight());
			  return (left_height > right_height) ? left_height + 1 : right_height + 1;
		  }
		  return 0;
	}
	
	public void set(Tree toReplaceTree, Tree newValueForTree){
		Node searchedTree = (Node)UtilityTreeManipulation.getParentOfTree(this, toReplaceTree);
		if(searchedTree.getLeft().equals(toReplaceTree)){
			searchedTree.setLeft(newValueForTree);
		}else if(searchedTree.getRight().equals(toReplaceTree)){
			searchedTree.setRight(newValueForTree);
		}
	}
	
	public void removeAll(List<Tree> leafsRemoved) {
		for(Tree currentTreeToBeRemoved : leafsRemoved){
			Node tree = (Node)UtilityTreeManipulation.getParentOfTree(this, currentTreeToBeRemoved);
			if(tree != null && tree.getLeft() != null && tree.getLeft().equals(currentTreeToBeRemoved)){
				tree.setLeft(null);
			}else if(tree != null && tree.getRight() != null && tree.getRight().equals(currentTreeToBeRemoved)){
				tree.setRight(null);
			}
		}
	}
	
}
