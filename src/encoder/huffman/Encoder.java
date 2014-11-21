package encoder.huffman;

import java.util.List;

import converter.ConverterHuffmanCode;
import exception.huffmann.ExceptionEncoding;
import logger.LoggerError;
import model.huffman.tree.Tree;

public class Encoder {

	private CollectionTree values = new CollectionTree();
	private Tree tree;
	
	public String encode(List<Object> values){
		this.values.clear();
		this.values.addAll(values); 
		this.tree = TreeFactory.create(this.values);
		return new ConverterHuffmanCode().convert(this.tree, values);
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
