package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.encoder.huffman.CollectionSymbol;
import main.file.jpeg.marker.EnumMarker;
import main.file.jpeg.segment.enums.EnumDestinationIdentifier;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.stream.SimpleBitOutputStream;
import main.sort.SortCollectionSymbolByPathLength;

public class DHT implements Marker {

	private byte[] length;
	private List<HT> lstHT;

	public DHT(){
		this.length = new byte[2];
		this.lstHT = new ArrayList<HT>();
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

	public void addHT(EnumDestinationIdentifier number, EnumHTType type, CollectionSymbol collectionSymbol) {
		
		Map<Integer, List<Integer>> sortedByPathLength =  SortCollectionSymbolByPathLength.sort(collectionSymbol);
		boolean first = true;
		int index = 0;
		
		HT ht = null;
		for(Map.Entry<Integer, List<Integer>> currentEntry : sortedByPathLength.entrySet()){
			if(index % 16 == 0){
				ht = new HT();
				ht.setInformation(number, type);
				if(first == true){
					this.lstHT.add(ht);
					first = false;
				}
			}
			for(Integer currentValue : currentEntry.getValue()){
				ht.addSymbol(currentEntry.getKey(), new byte[]{currentValue.byteValue()});
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
