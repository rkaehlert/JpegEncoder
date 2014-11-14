package exercise_one.encoder.huffman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import exercise_one.logger.LoggerText;
import exercise_one.model.comparator.huffman.ComparatorTreeFrequency;
import exercise_one.model.huffman.tree.Leaf;
import exercise_one.model.huffman.tree.Tree;

public class CollectionTree extends ArrayList<Tree> {

	Comparator<Tree> comparatorFrequency = new ComparatorTreeFrequency();
	
	public void add(Integer number){
		Boolean found = false;
		for(Tree tree : this){
			if(((Leaf)tree).getNumber() == number){
				int frequency = tree.getFrequency()+1;
				tree.setFrequency(frequency);
				found = true;
				LoggerText.log("nummer " + number + " existiert bereits. setze die neue haeufigkeit auf " + tree.getFrequency());
				break;
			}
		}
		
		if(!found){
			Leaf leaf = new Leaf(number,1);
			this.add(leaf);
			LoggerText.log("fuege neues blatt hinzu " + number);
		}
		this.sort(comparatorFrequency);
	}
	
	public void add(List<Integer> values){
		for(Integer value : values){
			this.add(value);
		}
	}
	
	public void print(){
		LoggerText.log("aktueller baum (groesse: " + this.size() + "): ");
	}
	
	public List<Tree> findLowestFrequenciesByMaximum(int max){
		return this.subList(0, max);
	}
	
}
