package main.sort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.calculator.UtilityCalculateBitLength;
import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class SortCollectionSymbolByPathLength {

	public static Map<Integer, List<String>> sort(CollectionSymbol collectionSymbol){
		Map<Integer, List<String>> returnValue = new HashMap<Integer, List<String>>();
		for(Map.Entry<Tree, String> currentEntry : collectionSymbol.entrySet()){
			int length = currentEntry.getValue().length();
			if(returnValue.containsKey(length) == false){
				returnValue.put(length, new LinkedList<String>());
			}
			Leaf leaf = (Leaf)currentEntry.getKey();
			//returnValue.get(length).add(currentEntry.getValue());
			if(leaf.getValue() instanceof Integer){
				returnValue.get(length).add(UtilityCalculateBitLength.calculate(Integer.valueOf(leaf.getValue().toString())).toString());
			}else if(leaf.getValue() instanceof Integer[]){
				String first = ((Integer[])leaf.getValue())[0].toString();
				String second = ((Integer[])leaf.getValue())[1].toString();
				int hexAsIntValue = Integer.parseInt(first+second,16);
				returnValue.get(length).add(String.valueOf(hexAsIntValue));
			}else{
				returnValue.get(length).add(leaf.getValue().toString());
			}
		}
		return returnValue;
	}
	
}
