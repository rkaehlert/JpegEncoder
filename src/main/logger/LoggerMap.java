package main.logger;

import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class LoggerMap <T,V> implements Logger {

	public void log(Map<T,V> map){
		StringBuffer returnValue = new StringBuffer();
		returnValue.append("\n\n");
		for(Map.Entry<T, V> entry : map.entrySet()) {
			if(entry.getKey() instanceof Leaf){
				Leaf leaf = (Leaf)entry.getKey();
				if(leaf.getValue().getClass().isArray()){
					Object[] temp = (Object[])leaf.getValue();
					returnValue = returnValue.append("[");
					for(int index = 0; index < temp.length; index++){
						returnValue = returnValue.append(temp[index].toString() + ",");
					}
					returnValue = new StringBuffer(returnValue.substring(0, returnValue.length()-1));
					returnValue = returnValue.append("]");
					returnValue = returnValue.append(": " + entry.getValue().toString());	
				}else{
					returnValue = returnValue.append(String.format(entry.getKey().toString()) + ": " + entry.getValue().toString());
				}
			}
			returnValue = returnValue.append(" laenge: " + entry.getValue().toString().length() + "\n");
		}
		returnValue.append("\n\n");
		LoggerText.log(returnValue.toString());
	}

	public void log(CollectionSymbol collectionSymbol) {
		System.out.println("\n\n");
		for(Map.Entry<Tree, String> entry : collectionSymbol.entrySet()){
			Leaf leaf = (Leaf)entry.getKey();
			if(leaf.getValue() instanceof Integer[]){
				Integer[] temp = (Integer[])leaf.getValue();
				System.out.println("(" + temp[0].toString() + "," + temp[1].toString() + ")" + ":" + entry.getValue());
			}else{
				System.out.println(leaf.getValue() + ":" + entry.getValue());
			}
		}
		System.out.println("\n\n");
	}
	
}
