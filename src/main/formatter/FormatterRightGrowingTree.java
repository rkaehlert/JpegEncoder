package main.formatter;

import main.encoder.huffman.CollectionSymbol;
import main.exception.common.ExceptionInvalidParameter;
import main.exception.common.ExceptionNotYetImplemented;
import main.model.huffman.tree.Node;
import main.model.huffman.tree.Tree;

public class FormatterRightGrowingTree implements Formatter {

	@Override
	public String format() {
		try{
			throw new ExceptionNotYetImplemented();
		}catch(ExceptionNotYetImplemented e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Tree format(Tree root, int depth, CollectionSymbol symbol) throws ExceptionInvalidParameter {
		Tree returnValue = root;
		if(symbol.isEmpty() == false){
			String pathOfLastElement = symbol.lastEntry().getValue();
			Tree treeOfLastElement = symbol.lastEntry().getKey();
			Node node = (Node) root;
			if(pathOfLastElement.length() == depth){
				node.setLeft(treeOfLastElement);
				symbol.remove(treeOfLastElement);
				if(symbol.isEmpty() == false){
					pathOfLastElement = symbol.lastEntry().getValue();
					treeOfLastElement = symbol.lastEntry().getKey();
					if(pathOfLastElement.length() == depth){
						node.setRight(treeOfLastElement);
						symbol.remove(treeOfLastElement);
						return returnValue;
					}
				}
				node.setRight(new Node());
				this.format(node.getRight(), depth+1, symbol);
			}else{
				node.setLeft(new Node());
				this.format(node.getLeft(), depth+1, symbol);
				node.setRight(new Node());
				this.format(node.getRight(), depth+1, symbol);
			}
		}
		return returnValue;
	}
	
	public Tree format(CollectionSymbol symbol) throws ExceptionInvalidParameter {		
		return this.format(new Node(), 1, symbol);
	}
	
}
