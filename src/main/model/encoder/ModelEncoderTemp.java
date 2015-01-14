package main.model.encoder;

import java.util.LinkedList;
import java.util.List;

import main.encoder.huffman.CollectionSymbol;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class ModelEncoderTemp {
  	
	private List<CollectionSymbol> lstHuffmanSymbol = new LinkedList<CollectionSymbol>();

	public List<CollectionSymbol> getLstHuffmanSymbol() {
		return lstHuffmanSymbol;
	}

	public void setLstHuffmanSymbol(List<CollectionSymbol> lstHuffmanSymbol) {
		this.lstHuffmanSymbol = lstHuffmanSymbol;
	}
	
	public List<List<Integer[]>> rleHuffmanCodesY;
	public List<List<Integer[]>> rleHuffmanCodesCb;
	public List<List<Integer[]>> rleHuffmanCodesCr;
	
	public void printHuffmanTable(){
		int index = 0;		
		for(CollectionSymbol currentCollectionSymbol : lstHuffmanSymbol){
			System.out.println("------------  beginn tabelle " + index + " ----------------");
			new LoggerMap<Tree, String>().log(currentCollectionSymbol);
			System.out.println("------------  ende tabelle " + index + " ----------------");
			index++;
		}
	}
	
}
