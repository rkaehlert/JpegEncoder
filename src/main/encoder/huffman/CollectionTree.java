package main.encoder.huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.logger.LoggerText;
import main.model.comparator.huffman.ComparatorTreeFrequency;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;

public class CollectionTree extends ArrayList<Tree> {

	Comparator<Tree> comparatorFrequency = new ComparatorTreeFrequency();

	public void addItem(Object value){
		Boolean found = false;
		for(Tree tree : this){
			if(((Leaf)tree).getValue() == value){
				int frequency = tree.getFrequency()+1;
				tree.setFrequency(frequency);
				found = true;
				LoggerText.log("nummer " + value + " existiert bereits. setze die neue haeufigkeit auf " + tree.getFrequency());
				break;
			}
		}
		
		if(!found){
			Leaf leaf = new Leaf(value,1);
			this.add(leaf);
			LoggerText.log("fuege neues blatt hinzu " + value);
		}
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
