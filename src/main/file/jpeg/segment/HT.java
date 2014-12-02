package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.file.jpeg.segment.DHT.EnumHTNumber;
import main.file.jpeg.segment.DHT.EnumHTType;
import main.file.stream.SimpleBitOutputStream;

public class HT implements Marker {
	private byte[] information;
	private LinkedHashMap<Integer, LinkedList<byte[]>> symbols;
	
	public HT(){
		this.information = new byte[3];
		this.setSymbols(new LinkedHashMap<Integer, LinkedList<byte[]>>());
	}
	
	public void addSymbol(byte[] code){
		if(this.validateLength(code.length) == true){
			Integer key = code.length;
			if(this.getSymbols().containsKey(key) == false){
				LinkedList<byte[]> lstBytes = new LinkedList<byte[]>(Arrays.asList(code));
				this.getSymbols().put(key, lstBytes);
			}else{
				if(this.getSymbols().get(key).contains(code) == false){
					this.getSymbols().get(key).add(code);
				}
			}
		}
	}
	
	private boolean validateLength(int length) {
		if(length > 16){
			return false;
		}
		return true;
	}

	public void setInformation(EnumHTNumber numberOfHt, EnumHTType typeOfHt){
    	byte[] ht = new byte[3];
    	ht[0] = BigInteger.valueOf(numberOfHt.getValue()).toByteArray()[0];
    	ht[1] = BigInteger.valueOf(typeOfHt.getValue()).toByteArray()[0];
    	ht[2] = 0x00;
    	this.information = ht;
	}

	public LinkedHashMap<Integer, LinkedList<byte[]>> getSymbols() {
		return symbols;
	}

	public void setSymbols(LinkedHashMap<Integer, LinkedList<byte[]>> symbols) {
		this.symbols = symbols;
	}

	@Override
	public void write(SimpleBitOutputStream out) throws FileNotFoundException,	IOException {
    	out.write(this.getInformation()[0], 4);
    	out.write(this.getInformation()[1], 1);
    	out.write(this.getInformation()[2], 3);
      	
    	int index = 0;
		for(Map.Entry<Integer, LinkedList<byte[]>> currentEntry : this.getSymbols().entrySet()){
			int difference = currentEntry.getKey()-index;
			index += difference;
			if(difference > 0){
				out.writeByteArray(new byte[difference-1]);
			}
			if(index == currentEntry.getKey()){
				out.writeByte((byte)currentEntry.getValue().size());
			}
    	}
		
		out.writeByteArray(new byte[16-index]);
		
		for(Map.Entry<Integer, LinkedList<byte[]>> currentEntry : this.getSymbols().entrySet()){
    		List<byte[]> value = currentEntry.getValue();
    		for(byte[] currentByte : value){
    			for(index = 8; index > currentByte.length; index--){
    				out.writeBit(0);
				}
    			for(index = 0; index < currentByte.length; index++){
    				if(currentByte[index] == 48){
        				out.writeBit(0);	
    				}else if(currentByte[index] == 49){
        				out.writeBit(1);	
    				}else{
    					System.out.println("STOIOOOOOOOOOOOP");
    				}
    			}
    		}
    	}
	}

	public byte[] getInformation() {
		return information;
	}

	public void setInformation(byte[] information) {
		this.information = information;
	}
	
}