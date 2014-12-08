package main.encoder.huffman;

import java.util.List;

import main.converter.ConverterHuffmanCode;
import main.exception.huffmann.ExceptionEncoding;
import main.logger.LoggerError;
import main.model.huffman.tree.Tree;

public class EncoderHuffmanTree {

	private CollectionTree values = new CollectionTree();
	private Tree tree;
	
	public String encode(List<Object> values){
		this.values.clear();
		this.values.addAll(values); 
		this.tree = TreeFactory.create(this.values);
		//return new ConverterHuffmanCode().convert(this.tree, values);
		return "";
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
