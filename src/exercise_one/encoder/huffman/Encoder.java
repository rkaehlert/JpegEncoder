package exercise_one.encoder.huffman;

import java.util.List;

import exercise_one.converter.ConverterHuffmanCode;
import exercise_one.exception.huffmann.ExceptionEncoding;
import exercise_one.logger.LoggerError;
import exercise_one.model.huffman.tree.Tree;

public class Encoder {

	private CollectionTree values = new CollectionTree();
	private Tree tree;
	
	public String encode(List<Integer> values){
		this.values.add(values); 
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
