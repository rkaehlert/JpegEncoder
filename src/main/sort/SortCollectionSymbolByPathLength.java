package main.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class SortCollectionSymbolByPathLength {

	public static Map<Integer, List<Integer>> sort(CollectionSymbol collectionSymbol){
		Map<Integer, List<Integer>> returnValue = new HashMap<Integer, List<Integer>>();
		for(Map.Entry<Tree, String> currentEntry : collectionSymbol.entrySet()){
			int length = currentEntry.getValue().length();
			if(returnValue.containsKey(length) == false){
				returnValue.put(length, new ArrayList<Integer>());
			}
			Leaf leaf = (Leaf)currentEntry.getKey();
			returnValue.get(length).add(Double.valueOf(leaf.getValue().toString()).intValue());
		}
		return returnValue;
	}
	
}
