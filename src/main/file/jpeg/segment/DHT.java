package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.file.jpeg.marker.EnumMarker;
import main.file.stream.SimpleBitOutputStream;
import main.model.huffman.tree.Tree;

public class DHT implements Marker {

	private byte[] length;
	private List<HT> lstHT;

	public DHT(){
		this.length = new byte[2];
		this.setLstHT(new ArrayList<HT>());
	}

	public enum EnumHTNumber {
		NUMBER_ONE(0),
		NUMBER_TWO(1),
		NUMBER_THREE(2);
		
		private Integer value = null;
		
		private EnumHTNumber(Integer value){
			this.setValue(value);
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
	
	public enum EnumHTType {
		AC(1),
		DC(0);
		
		private Integer value = null;
		
		private EnumHTType(Integer value){
			this.setValue(value);
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
	
    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
    	out.writeByteArray(EnumMarker.DHT.getValue());
    	for(HT currentHT : this.lstHT){
    		currentHT.write(out);        	
    	}
    }

	public byte[] getLength() {
		return length;
	}

	public void setLength(byte[] length) {
		this.length = length;
	}

	public List<HT> getLstHT() {
		return lstHT;
	}

	public void setLstHT(List<HT> lstHT) {
		this.lstHT = lstHT;
	}

	public void addHT(EnumHTNumber number, EnumHTType type, CollectionSymbol collectionSymbol) {
		CollectionSymbol reducedSymbolCollection = new CollectionSymbol();
		for(Map.Entry<Tree, String> currentEntry : collectionSymbol.entrySet()){
			reducedSymbolCollection.put(currentEntry.getKey(), currentEntry.getValue());
			if(reducedSymbolCollection.size() % 16 == 0){
				this.lstHT.add(new HT(number,type, reducedSymbolCollection));
				reducedSymbolCollection.clear();
			}
		}
		if(reducedSymbolCollection.size() > 0){
			this.lstHT.add(new HT(number,type, reducedSymbolCollection));
		}
	}
		
}
