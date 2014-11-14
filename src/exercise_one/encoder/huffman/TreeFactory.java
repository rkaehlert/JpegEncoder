package exercise_one.encoder.huffman;

import java.util.List;

import exercise_one.exception.huffmann.ExceptionEqualIndexPosition;
import exercise_one.exception.huffmann.ExceptionInvalidTreeSize;
import exercise_one.logger.LoggerText;
import exercise_one.model.huffman.tree.Node;
import exercise_one.model.huffman.tree.Tree;

public class TreeFactory {
	
	public static Tree create(CollectionTree collection){
		try{
			if(collection.size() <= 0){
				throw new ExceptionInvalidTreeSize("fehler: liste hat keine elemente");
			}	
		}catch(ExceptionInvalidTreeSize e){
			collection.print();
			return null;
		}

		if(collection.size() > 1){
			List<Tree> treesWithlowestFrequency = collection.findLowestFrequenciesByMaximum(2);
			Tree treeOneWithLowestFrequency = treesWithlowestFrequency.get(0);
			Tree treeTwoWithLowestFrequency = treesWithlowestFrequency.get(1);
			int indexOfTreeOne = collection.indexOf(treeOneWithLowestFrequency);
			int indexOfTreeTwo = collection.indexOf(treeTwoWithLowestFrequency);
			
			try{
				if(indexOfTreeOne == indexOfTreeTwo){
					throw new ExceptionEqualIndexPosition("ein baum kann nicht aus zwei gleichen index werten aufgebaut werden");
				}	
			}catch(ExceptionEqualIndexPosition e){
				return null;
			}
			
			LoggerText.log("die kleinsten gefundenen haeufigkeiten sind an position: " + indexOfTreeOne + " und " + indexOfTreeTwo);
			
			int frequency = treeOneWithLowestFrequency.getFrequency() + treeTwoWithLowestFrequency.getFrequency();
			
			//zuerst kommt treeTwo weil sonst der baum nach links wachsen wuerde. in aufgabe b soll er aber nach rechts wachsen
			Node node = new Node(treeTwoWithLowestFrequency, treeOneWithLowestFrequency, frequency);
			
			LoggerText.log("haeufigkeit des neuen knoten welcher durch die beiden knoten mit niedrigsten haeufigkeiten entstanden ist: " + frequency);
			collection.set(indexOfTreeOne, node);
			collection.remove(indexOfTreeTwo);
			
			collection.print();
			return create(collection);
		}
		
		collection.print();
		LoggerText.log("baum ist erstellt");
		return collection.get(0);
	}
	
}
