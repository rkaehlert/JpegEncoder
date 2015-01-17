package main.converter;

import java.util.ArrayList;
import java.util.List;

import main.encoder.huffman.EncoderHuffmanTree;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;
import main.utility.tree.UtilityTreeManipulation;
import main.validator.ValidatorBiggerOrEquals;
import main.validator.ValidatorEquals;

public class ConverterHuffmanTreeLengthLimited implements Converter<Tree> {
	
	public static int LIMIT = 15;
	
	public static Tree convert(Tree tree, int limit){

		// STEP ONE -- remove from T all its subtrees with root nodes at level L
		
		List<Tree> nodesToBeRemoved = UtilityTreeManipulation.trimByMaxDepth(limit, (Node)tree, new ValidatorBiggerOrEquals());
		List<Object> symbolsToBeRemoved = new ArrayList<Object>();
		for(Tree currentTree : nodesToBeRemoved){
			List<Leaf> lstLeafsOfCurrentNode = UtilityTreeManipulation.getLeafsByNode(currentTree);
			for(Leaf leaf : lstLeafsOfCurrentNode){
				symbolsToBeRemoved.add(leaf.getValue());	
			}
		}
		
		if(nodesToBeRemoved.size() == 0){
			return tree;
		}
		Tree t1 = tree;
		t1.removeAll(nodesToBeRemoved);
		
		if(symbolsToBeRemoved.size() == 0){
			return tree;
		}
		
		// STEP TWO -- create a complete binary tree with the removed leaves of step one
		
		EncoderHuffmanTree encoder = new EncoderHuffmanTree();
		encoder.encode(symbolsToBeRemoved);

		// STEP THREE
		
		Tree t2 = encoder.getTree();
		int appendingLevel = limit - t2.getHeight() - 1;
		if(appendingLevel < 0){
			appendingLevel = 0;
		}
		List<Tree> leafsOnLimitLevel = UtilityTreeManipulation.trimByMaxDepth(appendingLevel, (Node)t1, new ValidatorEquals());
		Tree yp = leafsOnLimitLevel.get(0);
		for(Tree currentTree : leafsOnLimitLevel){
			if(currentTree.getFrequency() < yp.getFrequency()){
				yp = currentTree;
			}
		}
		
		//STEP FOUR -- insert the new created tree
		
		Tree t3 = t1;
		if(appendingLevel == 0){
			Node node = new Node(t3, new Node(), t3.getFrequency());
			node.setRight(t2);
			t3 = node;
		}else{
			t3 = UtilityTreeManipulation.insertBefore(t3, yp, t2);
		}
		return t3;
	}
	
	public static Tree convert(Tree tree, int limit, int maxReputation){
		while(tree.getHeight() >  limit){
			if(maxReputation  == 0){
				break;
			}
			tree = ConverterHuffmanTreeLengthLimited.convert(tree,  limit);
			maxReputation--;
		}
		return tree;
	}
	
}
