package main.converter;

import main.encoder.huffman.CollectionSymbol;
import main.model.huffman.tree.Tree;

public class ConverterHuffmanTreeToCollectionSymbol implements Converter<CollectionSymbol> {
	
	public static CollectionSymbol convert(Tree tree){
		CollectionSymbol collectionSymbol = new CollectionSymbol();
		collectionSymbol.set(tree);
		return collectionSymbol;
	}

}
