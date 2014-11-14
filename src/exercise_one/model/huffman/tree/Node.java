package exercise_one.model.huffman.tree;

public class Node extends Tree {

	private Tree left;
	private Tree right;
	
	public Node(Tree left, Tree right, int frequency){
		super.setFrequency(frequency);
		this.left = left;
		this.right = right;
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
