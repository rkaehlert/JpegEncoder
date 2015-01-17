package main.model.encoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import main.encoder.huffman.CollectionSymbol;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class ModelEncoder {

	private List<CollectionSymbol> lstHuffmanSymbol = new LinkedList<CollectionSymbol>();
	
	private List<ModelGroupedBlock> lstModelGroupedBlock = new ArrayList<ModelGroupedBlock>();

	public List<ModelGroupedBlock> getLstModelGroupedBlock() {
		return lstModelGroupedBlock;
	}

	public void setLstModelGroupedBlock(List<ModelGroupedBlock> lstModelGroupedBlock) {
		this.lstModelGroupedBlock = lstModelGroupedBlock;
	}

	public List<CollectionSymbol> getLstHuffmanSymbol() {
		return lstHuffmanSymbol;
	}

	public void setLstHuffmanSymbol(List<CollectionSymbol> lstHuffmanSymbol) {
		this.lstHuffmanSymbol = lstHuffmanSymbol;
	}
	
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