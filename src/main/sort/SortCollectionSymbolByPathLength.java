package main.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.calculator.UtilityCalculateBitLength;
import main.converter.datatype.ConverterToBit;
import main.encoder.huffman.CollectionSymbol;
import main.model.encoder.ModelHT;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class SortCollectionSymbolByPathLength {

	public static Map<Integer, List<ModelHT>> sort(CollectionSymbol collectionSymbol){
		Map<Integer, List<ModelHT>> returnValue = new HashMap<Integer, List<ModelHT>>();
		for(Map.Entry<Tree, String> currentEntry : collectionSymbol.entrySet()){
			int length = currentEntry.getValue().length();
			if(returnValue.containsKey(length) == false){
				returnValue.put(length, new LinkedList<ModelHT>());
			}
			Leaf leaf = (Leaf)currentEntry.getKey();
			
			if(leaf.getValue() instanceof Integer){
				String value = calculateValue(ConverterToBit.convert(Integer.valueOf(leaf.getValue().toString())));
				ModelHT modelHT = new ModelHT(value);
				returnValue.get(length).add(modelHT);
			}else if(leaf.getValue() instanceof Integer[]){
				String index0 = ConverterToBit.convert(((Integer[])leaf.getValue())[0]);
				String index1 = ConverterToBit.convert(((Integer[])leaf.getValue())[1]);
				
//				String first = ((Integer[])leaf.getValue())[0].toString();
//				String second = ((Integer[])leaf.getValue())[1].toString();
//				int hexAsIntValue = Integer.parseInt(first+second,16);
				
				String value = calculateValue(index0, index1);
				ModelHT modelHT = new ModelHT(value, currentEntry.getValue());
				returnValue.get(length).add(modelHT);
			}else{
				throw new IllegalArgumentException();
			}
		}
		for(Map.Entry<Integer, List<ModelHT>> currentEntry : returnValue.entrySet()){
			List<ModelHT> codes = currentEntry.getValue();
			Collections.sort(codes, new ComparatorHuffmanTableCodeLength());
		}
		return returnValue;
	}
	
	private static String calculateValue(String index0, String index1){
		StringBuffer buffer = new StringBuffer();
		if(index0.length() > 4 || index1.length() > 4){
			throw new IllegalArgumentException("laenge ist groesser 4");
		}
		if(index0.length() != 4){
			int bitsToAppend = 4 - index0.length();
			for(int currentBit = 0; currentBit < bitsToAppend; currentBit++){
				buffer.append("0");
			}
		}
		buffer.append(index0);
		if(index1.length() != 4){
			int bitsToAppend = 4 - index1.length();
			for(int currentBit = 0; currentBit < bitsToAppend; currentBit++){
				buffer.append("0");
			}
		}
		buffer.append(index1);
		
		return buffer.toString();
	}		
	
	private static String calculateValue(String index0){
		StringBuffer buffer = new StringBuffer();
		if(index0.length() > 4){
			throw new IllegalArgumentException("laenge ist groesser 4");
		}
		if(index0.length() != 8){
			int bitsToAppend = 8 - index0.length();
			for(int currentBit = 0; currentBit < bitsToAppend; currentBit++){
				buffer.append("0");
			}
		}
		buffer.append(index0);
		return buffer.toString();
	}		
}
