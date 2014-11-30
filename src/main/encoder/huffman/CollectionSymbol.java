package main.encoder.huffman;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.model.comparator.huffman.ComparatorPathLengthAndFrequency;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;

public class CollectionSymbol extends LinkedHashMap<Tree, String> {

	public CollectionSymbol(){
		super();
	}
	
	public Map<Tree, String> set(Tree root){
		this.clear();
		this.set(root, "");
		return this;
	}
	
	private void set(Tree tree, String path){
		if(tree instanceof Leaf){
			//this.put(((Leaf)tree).getValue(), path);
			super.put(tree, path);
		}
		if(tree instanceof Node){
			this.set(((Node)tree).getLeft(), path + "0");
			this.set(((Node)tree).getRight(), path + "1");
		}
	}
	
	public Map.Entry<Tree, String> lastEntry(){
		Map.Entry<Tree,String>[] entry = new Map.Entry[this.size()];
		entry = this.entrySet().toArray(entry);
		return entry[this.size()-1];
	}
	
	public CollectionSymbol sort(){
		CollectionSymbol returnValue = new CollectionSymbol();
		List<Map.Entry<Tree,String>> entries = new LinkedList<Map.Entry<Tree,String>>(this.entrySet());
		Collections.sort(entries, new ComparatorPathLengthAndFrequency());
		Map<Tree,String> sortedMap = new LinkedHashMap<Tree,String>();
        for(Map.Entry<Tree,String> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        returnValue.putAll(sortedMap);
		return returnValue;
	}
	 
	public String get(Object obj){
		for(Map.Entry<Tree, String> currentEntry : super.entrySet()){
			Leaf leaf = (Leaf)currentEntry.getKey();
			if(leaf.getValue().equals(obj)){
				return currentEntry.getValue();
			}
		}
		return null;
	}
	
}
