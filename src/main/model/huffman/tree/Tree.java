package main.model.huffman.tree;

import java.util.Arrays;
import java.util.List;

import main.model.comparator.huffman.ComparatorTreeDepth;
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
		return this.getHeight(this, 0);
	}
	
	private int getHeight(Tree currentTree, int currentMaximumHeight){
		if(currentTree instanceof Node) {
			Node node = (Node)currentTree;
			int tempMaximumHeight = currentMaximumHeight+1;
			if(node.getLeft() instanceof Node){
				currentMaximumHeight = this.getHeight(node.getLeft(), tempMaximumHeight);
			}else if(node.getRight() instanceof Node){
				currentMaximumHeight = this.getHeight(node.getRight(), tempMaximumHeight);
			}
		}
		return currentMaximumHeight;
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
