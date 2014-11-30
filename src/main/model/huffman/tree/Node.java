package main.model.huffman.tree;




public class Node extends Tree {

	private Tree left;
	private Tree right;
	
	public Node(Tree t1, Tree t2, int frequency){
		super.setFrequency(frequency);
		this.left = t1;
		this.right = t2;
	}
	
	public Node(){
		this(null,null,0);
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

	@Override
	public int compareTo(Tree o) {
		return 0;
	}	
	
}
