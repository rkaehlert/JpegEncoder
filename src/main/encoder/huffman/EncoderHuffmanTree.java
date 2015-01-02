package main.encoder.huffman;

import java.util.ArrayList;
import java.util.List;

import main.converter.ConverterHuffmanTreeToCollectionSymbol;
import main.exception.huffmann.ExceptionEncoding;
import main.logger.LoggerError;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class EncoderHuffmanTree {

	private CollectionTree values = new CollectionTree();
	private Tree tree;
	
	public void encode(List<Object> values){
		this.values.clear();
		this.values.addAll(values); 
		this.tree = TreeFactory.create(this.values);
	    LoggerMap<Tree,String> loggerMap = new LoggerMap<Tree,String>();
	    loggerMap.log(this.getPathCollection());
	}
	
	public CollectionSymbol getPathCollection(){
		return ConverterHuffmanTreeToCollectionSymbol.convert(this.tree);
	}
	
	public void encodeJPEG(List<Integer[]> values){
		List<Object> input = new ArrayList<Object>();
		for(int i = 0; i < values.size(); i++){
			input.add(values.get(i));
		}
		this.encode(input);
	}

	public Tree getTree() {
		try{
			if(tree == null){
				throw new ExceptionEncoding("es ist noch kein baum generiert");
			}
		}catch(ExceptionEncoding e){
			LoggerError.log(e.getMessage());
		}
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

}
