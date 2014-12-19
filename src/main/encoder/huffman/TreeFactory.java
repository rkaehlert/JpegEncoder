package main.encoder.huffman;

import java.util.List;

import main.converter.ConverterHuffmanTreeLengthLimited;
import main.converter.ConverterHuffmanTreeToPath;
import main.exception.huffmann.ExceptionEqualIndexPosition;
import main.exception.huffmann.ExceptionInvalidTreeSize;
import main.formatter.FormatterRightGrowingTree;
import main.logger.LoggerText;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;
import main.utility.tree.UtilityTreeManipulation;

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
			
			//baum soll nach rechts wachsen. also wird der groessere baum in den rechten baum eingefuegt
			Node node = new Node(treeOneWithLowestFrequency, treeTwoWithLowestFrequency, frequency);
			
			LoggerText.log("haeufigkeit des neuen knoten welcher durch die beiden knoten mit niedrigsten haeufigkeiten entstanden ist: " + frequency);
			
			collection.set(indexOfTreeOne, node);
			collection.remove(indexOfTreeTwo);		

			collection.print();
			
			return create(collection);
		}
		
		Tree output = collection.get(0);			
		 
        Tree lengthLimitedTree = ConverterHuffmanTreeLengthLimited.convert(output, 11, 15);
        
        CollectionSymbol symbol = ConverterHuffmanTreeToPath.convert(lengthLimitedTree);
        symbol = symbol.sort();
        
        output = new FormatterRightGrowingTree().format(symbol);
		
		UtilityTreeManipulation.replaceLeafWithMaximumDepth(output, null);
        
		return output;
	}
	
}
