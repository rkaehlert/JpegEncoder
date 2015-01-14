package main.utility.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.datahandler.DataHandler;
import main.datahandler.DataHandlerCompareNaturalNumber;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;
import main.validator.Validator;

public class UtilityTreeManipulation {


	public static Tree replaceLeafWithMaximumDepth(Tree tree, Tree root){
		if(tree instanceof Leaf){
			Node node = new Node(tree, null, tree.getFrequency());
			((Node)root).setRight(node);
			return tree;
		}else if(tree instanceof Node){
			return UtilityTreeManipulation.replaceLeafWithMaximumDepth(((Node) tree).getRight(), tree);
		}
		return null;
	}


	private static List<Tree> trimByMaxDepth(int depth, int limit, Node root, List<Tree> returnValue, Validator validator){
		DataHandler datahandler = (DataHandler)new DataHandlerCompareNaturalNumber(depth, limit);
		if(validator.validate(datahandler)){
			returnValue.add(root);
		}else{
			if(root.getLeft() != null && root.getLeft() instanceof Node){
				trimByMaxDepth(depth+1, limit, (Node)root.getLeft(), returnValue, validator);
			}
			if(root.getRight() != null && root.getRight() instanceof Node){
				trimByMaxDepth(depth+1, limit, (Node)root.getRight(), returnValue, validator);
			}
		}
		return returnValue;
	}
	
	public static List<Tree> trimByMaxDepth(int limit, Node root, Validator validator){
		return UtilityTreeManipulation.trimByMaxDepth(0, limit, root, new ArrayList<Tree>(), validator);
	}
	
	private static Tree getParentOfTree(Tree root, Tree treeToSearchFor, Tree returnValue){
		Node currentRootNode = (Node)root;
		if(currentRootNode.getLeft() != null && currentRootNode.getLeft().equals(treeToSearchFor)){
			returnValue = root;
			return returnValue;
		}else if (currentRootNode.getRight() != null && currentRootNode.getRight().equals(treeToSearchFor)){
			returnValue = root;
			return returnValue;
		}
		if(currentRootNode.getLeft() != null && currentRootNode.getLeft() instanceof Node){
			returnValue = UtilityTreeManipulation.getParentOfTree(currentRootNode.getLeft(), treeToSearchFor, returnValue);
		}
		if(currentRootNode.getRight() != null && currentRootNode.getRight() instanceof Node){
			returnValue = UtilityTreeManipulation.getParentOfTree(currentRootNode.getRight(), treeToSearchFor, returnValue);	
		}
		if(returnValue == null){
			returnValue = root;
		}
		return returnValue;
	}
	
	public static Tree getParentOfTree(Tree root, Tree treeToSearchFor){
		return UtilityTreeManipulation.getParentOfTree(root, treeToSearchFor, null);
	}
	

	public static Tree insertNewNodeBefore(Tree root, Tree treeToAppendTo){
		Node searchedTree = (Node)UtilityTreeManipulation.getParentOfTree(root, treeToAppendTo);
		if(searchedTree.getLeft().equals(treeToAppendTo)){
			Node node = new Node(searchedTree.getLeft(), new Node(), searchedTree.getLeft().getFrequency());
			searchedTree.setLeft(node);
			return searchedTree.getLeft();
		}else if(searchedTree.getRight().equals(treeToAppendTo)){
			Node node = new Node(searchedTree.getRight(), new Node(), searchedTree.getRight().getFrequency());
			searchedTree.setRight(node);
			return searchedTree.getRight();
		}
		return root;
	}
	
	public static Tree insertBefore(Tree root, Tree treeToAppendTo, Tree valueForInsertionTree){
		Node tree = (Node)UtilityTreeManipulation.insertNewNodeBefore(root, treeToAppendTo);
		tree.setRight(valueForInsertionTree);
		return root;
	}


	public static List<Leaf> getLeafsByNode(Tree tree){
		if(tree instanceof Leaf){
			Leaf[] leaf = new Leaf[]{(Leaf)tree};
			return Arrays.asList(leaf);
		}else{
			return UtilityTreeManipulation.getLeafsByNode((Node)tree, new ArrayList<Leaf>());
		}
	}
	
	public static List<Leaf> getLeafsByNode(Node node, List<Leaf> returnValue){
		if(node.getLeft() instanceof Leaf){
			returnValue.add((Leaf)node.getLeft());
		}
		if(node.getRight() instanceof Leaf){
			returnValue.add((Leaf)node.getRight());
		}
		if(node.getLeft() instanceof Node){
			UtilityTreeManipulation.getLeafsByNode((Node)node.getLeft(), returnValue);	
		}
		if(node.getRight() instanceof Node){
			UtilityTreeManipulation.getLeafsByNode((Node)node.getRight(), returnValue);	
		}
		return returnValue;
	}
	
}
