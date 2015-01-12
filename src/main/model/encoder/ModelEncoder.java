package main.model.encoder;

import java.util.LinkedList;
import java.util.List;

import main.encoder.huffman.CollectionSymbol;
import main.logger.LoggerMap;
import main.model.huffman.tree.Tree;

public class ModelEncoder {

	public List<Integer> lstDeltaDCCoefficientY;
	public List<Integer> lstDeltaDCCoefficientCb;
	public List<Integer> lstDeltaDCCoefficientCr;
        
        public List<Integer> lstDeltaDCCoefficientYCategory;
	public List<Integer> lstDeltaDCCoefficientCbCategory;
	public List<Integer> lstDeltaDCCoefficientCrCategory;
  	
	public List<Integer[]> lstRunLengthEncodedZickZackY;
	public List<Integer[]> lstRunLengthEncodedZickZackCb;
	public List<Integer[]> lstRunLengthEncodedZickZackCr;
	
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

	public List<Integer> getLstDeltaDCCoefficientY() {
		return lstDeltaDCCoefficientY;
	}

	public void setLstDeltaDCCoefficientY(List<Integer> lstDeltaDCCoefficientY) {
		this.lstDeltaDCCoefficientY = lstDeltaDCCoefficientY;
	}

	public List<Integer> getLstDeltaDCCoefficientCb() {
		return lstDeltaDCCoefficientCb;
	}

	public void setLstDeltaDCCoefficientCb(List<Integer> lstDeltaDCCoefficientCb) {
		this.lstDeltaDCCoefficientCb = lstDeltaDCCoefficientCb;
	}

	public List<Integer> getLstDeltaDCCoefficientCr() {
		return lstDeltaDCCoefficientCr;
	}

	public void setLstDeltaDCCoefficientCr(List<Integer> lstDeltaDCCoefficientCr) {
		this.lstDeltaDCCoefficientCr = lstDeltaDCCoefficientCr;
	}

	public List<Integer[]> getLstRunLengthEncodedZickZackY() {
		return lstRunLengthEncodedZickZackY;
	}

	public void setLstRunLengthEncodedZickZackY(
			List<Integer[]> lstRunLengthEncodedZickZackY) {
		this.lstRunLengthEncodedZickZackY = lstRunLengthEncodedZickZackY;
	}

	public List<Integer[]> getLstRunLengthEncodedZickZackCb() {
		return lstRunLengthEncodedZickZackCb;
	}

	public void setLstRunLengthEncodedZickZackCb(
			List<Integer[]> lstRunLengthEncodedZickZackCb) {
		this.lstRunLengthEncodedZickZackCb = lstRunLengthEncodedZickZackCb;
	}

	public List<Integer[]> getLstRunLengthEncodedZickZackCr() {
		return lstRunLengthEncodedZickZackCr;
	}

	public void setLstRunLengthEncodedZickZackCr(
			List<Integer[]> lstRunLengthEncodedZickZackCr) {
		this.lstRunLengthEncodedZickZackCr = lstRunLengthEncodedZickZackCr;
	}
	
}
