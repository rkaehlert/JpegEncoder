package main.encoder.huffman;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.comparator.huffman.ComparatorPathLengthAndFrequency;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;

public class CollectionSymbol extends LinkedHashMap<Tree, String> implements Cloneable {

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
	 
	@Override
	public String get(Object obj){
		for(Map.Entry<Tree, String> currentEntry : super.entrySet()){
			Leaf leaf = (Leaf)currentEntry.getKey();
			if(obj.getClass().isArray()){
				if(Arrays.equals((Object[])leaf.getValue(), (Object[])obj)){
					return currentEntry.getValue();
				};
			}else{
				if(leaf.getValue().equals(obj)){
					return currentEntry.getValue();
				}
			}
		}
		return null;
	}
	
	public void setTreeValue(Object valueOfTree, Object valueToSet){
		for(Map.Entry<Tree, String> currentEntry : super.entrySet()){
			Leaf leaf = (Leaf)currentEntry.getKey();
			if(valueOfTree.getClass().isArray()){
				if(leaf.getValue().getClass().isArray()){
					if(Arrays.equals((Object[])leaf.getValue(), (Object[])valueOfTree)){
						leaf.setValue(valueToSet);
					};
				}
			}else{
				if(leaf.getValue().equals(valueOfTree)){
					leaf.setValue(valueToSet);
				}
			}
		}
	}
	
	public boolean containsKey(Object obj){
		if(this.get(obj) == null){
			return false;
		}else{
			return true;
		}
	}

}
