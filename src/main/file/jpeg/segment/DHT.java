package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.file.jpeg.marker.EnumMarker;
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.stream.BitStream;
import main.model.encoder.ModelHT;
import main.sort.SortCollectionSymbolByPathLength;

public class DHT implements Marker {

	private byte[] length;
	private List<HT> lstHT;

	public DHT(){
		this.length = new byte[2];
		this.lstHT = new LinkedList<HT>();
	}
	
    @Override
    public void write(BitStream out) throws FileNotFoundException, IOException {
    	out.write(EnumMarker.DHT.getValue());
    	out.write(this.length);
    	
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

	public void addHT(EnumComponentId number, EnumHTType type, CollectionSymbol collectionSymbol) {
		
		Map<Integer, List<ModelHT>> sortedByPathLength =  SortCollectionSymbolByPathLength.sort(collectionSymbol);
		boolean first = true;
		int index = 0;
		
		HT ht = null;
		for(Map.Entry<Integer, List<ModelHT>> currentEntry : sortedByPathLength.entrySet()){
			if(index % 16 == 0){
				ht = new HT();
				ht.setInformation(number, type);
				if(first == true){
					this.lstHT.add(ht);
					first = false;
				}
			}
			for(ModelHT currentValue : currentEntry.getValue()){
				ht.addSymbol(currentEntry.getKey(), currentValue);
			}
			index++;
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
					count += ht.getSymbols().get(index).size();
				}
			}
			length += 17 + count;
		}
		this.length[1] = Integer.valueOf(length).byteValue();
	}
	
}
