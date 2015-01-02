package main.model.encoder;

import java.util.LinkedList;
import java.util.List;

import main.encoder.huffman.CollectionSymbol;

public class ModelEncoder {


	private List<CollectionSymbol> lstHuffmanSymbol = new LinkedList<CollectionSymbol>();

	public List<CollectionSymbol> getLstHuffmanSymbol() {
		return lstHuffmanSymbol;
	}

	public void setLstHuffmanSymbol(List<CollectionSymbol> lstHuffmanSymbol) {
		this.lstHuffmanSymbol = lstHuffmanSymbol;
	}
	
}
