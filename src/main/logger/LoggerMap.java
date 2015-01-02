package main.logger;

import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class LoggerMap <T,V> implements Logger {

	public void log(Map<T,V> map){
		String returnValue = "\ninhalt der map: \n\n";
		for(Map.Entry<T, V> entry : map.entrySet()) {
			if(entry.getKey() instanceof Leaf){
				Leaf leaf = (Leaf)entry.getKey();
				if(leaf.getValue().getClass().isArray()){
					Object[] temp = (Object[])leaf.getValue();
					returnValue = returnValue.concat("[");
					for(int index = 0; index < temp.length; index++){
						returnValue = returnValue.concat(temp[index].toString() + ",");
					}
					returnValue = returnValue.substring(0, returnValue.length()-1);
					returnValue = returnValue.concat("]");
					returnValue = returnValue.concat(": " + entry.getValue().toString());	
				}else{
					returnValue = returnValue.concat(String.format(entry.getKey().toString()) + ": " + entry.getValue().toString());
				}
			}
			returnValue = returnValue.concat(" laenge: " + entry.getValue().toString().length() + "\n");
		}
		LoggerText.log(returnValue);
	}

	public void log(CollectionSymbol collectionSymbol) {
		for(Map.Entry<Tree, String> entry : collectionSymbol.entrySet()){
			Leaf leaf = (Leaf)entry.getKey();
			System.out.println(leaf.getValue() + ":" + entry.getValue());
		}
	}
	
}
