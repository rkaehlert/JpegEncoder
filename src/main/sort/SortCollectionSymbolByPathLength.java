package main.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class SortCollectionSymbolByPathLength {

	public static Map<Integer, List<String>> sort(CollectionSymbol collectionSymbol){
		Map<Integer, List<String>> returnValue = new HashMap<Integer, List<String>>();
		for(Map.Entry<Tree, String> currentEntry : collectionSymbol.entrySet()){
			int length = currentEntry.getValue().length();
			if(returnValue.containsKey(length) == false){
				returnValue.put(length, new ArrayList<String>());
			}
			Leaf leaf = (Leaf)currentEntry.getKey();
			returnValue.get(length).add(leaf.getValue().toString());
		}
		return returnValue;
	}
	
}
