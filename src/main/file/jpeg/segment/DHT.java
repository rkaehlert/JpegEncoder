package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.file.jpeg.marker.EnumMarker;
import main.file.stream.SimpleBitOutputStream;
import main.sort.SortCollectionSymbolByPathLength;

public class DHT implements Marker {

	private byte[] length;
	private List<HT> lstHT;

	public DHT(){
		this.length = new byte[2];
		this.lstHT = new ArrayList<HT>();
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
    	out.writeByteArray(this.length);
    	
    	for(HT currentHT : this.lstHT){
    		currentHT.write(out);
    	}
    }

	public byte[] getLength() {
		return length;
	}

	public List<HT> getLstHT() {
		return lstHT;
	}

	public void addHT(EnumHTNumber number, EnumHTType type, CollectionSymbol collectionSymbol) {
		
		Map<Integer, List<String>> sortedByPathLength =  SortCollectionSymbolByPathLength.sort(collectionSymbol);
		boolean first = true;
		int index = 0;
		
		HT ht = null;
		for(Map.Entry<Integer, List<String>> currentEntry : sortedByPathLength.entrySet()){
			if(index % 16 == 0){
				ht = new HT();
				ht.setInformation(number, type);
				if(first == false){
					this.lstHT.add(ht);
				}
			}
			for(String currentValue : currentEntry.getValue()){
				ht.addSymbol(currentValue.getBytes());
			}
			first = false;
			index++;
		}
		if(ht.getSymbols().size() > 0){
			this.lstHT.add(ht);
		}
		this.setLength();
	}
	
	public void setLength(){
		this.length[0] = 0;
		int length = 2;
		for(HT ht : this.lstHT){
			int count = 0; 
			for(int index = 1; index <= 16; index++){
				if(ht.getSymbols().containsKey(index)){
					count += ht.getSymbols().get(index).size() * index;
				}
			}
			length += 17 + count;
		}
		this.length[1] = Integer.valueOf(length).byteValue();
	}
	
}
