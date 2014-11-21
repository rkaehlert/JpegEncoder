package exercise_one.model.huffman.tree;

import java.util.List;


public class Node extends Tree {

	private Tree left;
	private Tree right;
	
	public Node(Tree t1, Tree t2, int frequency){
		super.setFrequency(frequency);
		List<Tree> trees = Tree.sortByDepth(t1,t2);
		this.left = trees.get(0);
		this.right = trees.get(1);
		/*this.left = t1;
		this.right = t2;*/
		if(this.right != null){
			if(this.right.getDepth() == 0){
				super.increaseDepth(1);
			}
			
			super.increaseDepth(this.right.getDepth());
		}
	}
	
	public Tree getLeft() {
		return left;
	}
	public void setLeft(Tree left) {
		this.left = left;
	}
	public Tree getRight() {
		return right;
	}
	public void setRight(Tree right) {
		this.right = right;
	}	
	
}
