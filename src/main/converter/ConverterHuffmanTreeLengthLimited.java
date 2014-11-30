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
	
	public static Tree convert(Tree tree, int limit){
		List<Tree> nodesToBeRemoved = UtilityTreeManipulation.trimByMaxDepth(limit, (Node)tree, new ValidatorBiggerOrEquals());
		List<Object> symbolsToBeRemoved = new ArrayList<Object>(); 
		for(Tree currentTree : nodesToBeRemoved){
			List<Leaf> lstLeafsOfCurrentNode = UtilityTreeManipulation.getLeafsByNode(currentTree);
			for(Leaf leaf : lstLeafsOfCurrentNode){
				symbolsToBeRemoved.add(leaf.getValue());	
			}
		}
		Tree t1 = tree;
		t1.removeAll(nodesToBeRemoved);
		
		EncoderHuffmanTree encoder = new EncoderHuffmanTree();
		encoder.encode(symbolsToBeRemoved);
		
		Tree t2 = encoder.getTree();
		int appendingLevel = limit - t2.getHeight() - 1;
		if(appendingLevel < 0){
			appendingLevel = 0;
		}
		List<Tree> leafsOnLimitLevel = UtilityTreeManipulation.trimByMaxDepth(appendingLevel, (Node)t1, new ValidatorEquals());
		Tree yp = leafsOnLimitLevel.get(0);
		for(Tree currentTree : leafsOnLimitLevel){
			if(currentTree.getFrequency() > yp.getFrequency()){
				yp = currentTree;
			}
		}
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
	
}
