package main.tester;

import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Node;

public class TesterTree {

	public static void main(String[] args) {
		Node tree = new Node();
		tree.setLeft(new Leaf("depp1", 20));
		tree.setRight(new Leaf("depp2", 20));
		
		Node tree2 = new Node();
		tree2.setLeft(new Leaf("depp3", 20));
		tree2.setRight(new Leaf("depp4", 20));
		
		Node tree3 = new Node();
		tree3.setLeft(new Leaf("depp5", 20));
		tree3.setRight(new Leaf("depp6", 20));
		
		Node root = new Node();
		root.setLeft(tree);
		root.setRight(tree2);
		((Node)root.getRight()).setLeft(new Leaf("depp3", 15));		
		((Node)root.getRight()).setRight(tree3);
		
		System.out.println(root.getHeight());
	}

}
