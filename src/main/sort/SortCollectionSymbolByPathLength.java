package main.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;

public class SortCollectionSymbolByPathLength {

	public static Map<Integer, List<String>> sort(CollectionSymbol collectionSymbol){
		Map<Integer, List<String>> returnValue = new HashMap<Integer, List<String>>();
		for(String currentValue : collectionSymbol.values()){
			int length = currentValue.length();
			if(returnValue.containsKey(length) == false){
				returnValue.put(length, new ArrayList<String>());
			}
			returnValue.get(length).add(currentValue);
		}
		return returnValue;
	}
	
}
