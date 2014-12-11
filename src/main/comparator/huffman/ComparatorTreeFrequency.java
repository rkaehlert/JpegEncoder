package main.comparator.huffman;

import java.util.Comparator;

import main.model.huffman.tree.Tree;

public class ComparatorTreeFrequency implements Comparator<Tree> {

	@Override
	public int compare(Tree o1, Tree o2) {
		if(o1.getFrequency() > o2.getFrequency()){
			return 1;
		}else if(o1.getFrequency() < o2.getFrequency()){
			return -1;
		}else{
			return 0;
		}
	}


}
