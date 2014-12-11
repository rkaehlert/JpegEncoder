package main.comparator.huffman;

import java.util.Comparator;
import java.util.Map;

import main.model.huffman.tree.Tree;

public class ComparatorPathLengthAndFrequency implements Comparator<Map.Entry<Tree, String>> {

	@Override
	public int compare(Map.Entry<Tree, String> o1, Map.Entry<Tree, String> o2) {
		if(o1.getValue().length() > o2.getValue().length()){
			return -1;
		}
		if(o1.getValue().length() == o2.getValue().length() && o1.getKey().getFrequency() < o2.getKey().getFrequency()){
			return -1;
		}
		return 1;
	}

}
