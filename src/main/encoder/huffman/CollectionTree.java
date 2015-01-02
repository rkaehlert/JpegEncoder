package main.encoder.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import main.comparator.huffman.ComparatorTreeFrequency;
import main.logger.LoggerText;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class CollectionTree extends ArrayList<Tree> {

	Comparator<Tree> comparatorFrequency = new ComparatorTreeFrequency();

	public void addItem(Object value){
		Boolean found = false;
		for(Tree tree : this){
			Leaf leaf = ((Leaf)tree);
			boolean isLeafEqual = leaf.getValue().equals(value);
			if(value.getClass().isArray() == true){
				isLeafEqual = Arrays.equals((Object[])leaf.getValue(), (Object[])value);
			}
			if(isLeafEqual == true){
				int frequency = tree.getFrequency()+1;
				tree.setFrequency(frequency);
				found = true;
				break;
			}
		}
		
		if(!found){
			Leaf leaf = new Leaf(value,1);
			this.add(leaf);
		}
	}
	
	public Tree getLastElement(){
		return (Tree)super.toArray()[super.size()];
	}
	
	public void addAll(List<Object> values){
		for(Object value : values){
			this.addItem(value);
		}
	}
	
	public void print(){
		LoggerText.log("aktueller baum (groesse: " + this.size() + "): ");
	}
	
	public List<Tree> findLowestFrequenciesByMaximum(int max){
		this.sort(comparatorFrequency);
		return this.subList(0, max);
	}
	
}
