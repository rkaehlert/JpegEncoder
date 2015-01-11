package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.stream.BitStream;

public class HT implements Marker {
	
	private byte[] information;
	private LinkedHashMap<Integer, LinkedList<String>> symbols;
	
	public HT(){
		this.information = new byte[3];
		this.setSymbols(new LinkedHashMap<Integer, LinkedList<String>>());
	}
	
	public void addSymbol(int length, String code){
		if(this.validateLength(length) == true){
			Integer key = length;
			if(this.getSymbols().containsKey(key) == false){
				LinkedList<String> lstBytes = new LinkedList<String>(Arrays.asList(code));
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

	public void setInformation(EnumComponentId numberOfHt, EnumHTType typeOfHt){
    	byte[] ht = new byte[3];
    	ht[0] = BigInteger.valueOf(typeOfHt.getValue()).toByteArray()[0];
    	ht[1] = BigInteger.valueOf(numberOfHt.getValue()).toByteArray()[0];
    	ht[2] = 0x00;
    	this.information = ht;
	}

	public LinkedHashMap<Integer, LinkedList<String>> getSymbols() {
		return symbols;
	}

	public void setSymbols(LinkedHashMap<Integer, LinkedList<String>> symbols) {
		this.symbols = symbols;
	}

	@Override
	public void write(BitStream out) throws FileNotFoundException,	IOException {
    	out.writeValue(3, this.getInformation()[2]);
    	out.writeValue(1, this.getInformation()[0]);
		out.writeValue(4, this.getInformation()[1]);
    	int index = 0;
		for(Map.Entry<Integer, LinkedList<String>> currentEntry : this.getSymbols().entrySet()){
			int difference = currentEntry.getKey()-index;
			index += difference;
			if(difference > 0){
				out.write(new byte[difference-1]);
			}
			if(index == currentEntry.getKey()){
				out.write((byte)currentEntry.getValue().size());
			}
			
    	}
		out.write(new byte[16-index]);
		for(Map.Entry<Integer, LinkedList<String>> currentEntry : this.getSymbols().entrySet()){
			LinkedList<String> value = currentEntry.getValue();
    		for(String currentByte : value){
    			
    			//out.write(Integer.parseInt(currentByte, 2));
    			
    			
    			out.writeValue(8, Integer.valueOf(currentByte));
//    			char[] bits = currentByte.toCharArray();
//    			for(int i = 0; i < bits.length; i++){
//    				if(bits[i] == '0'){
//    					out.writeBit(0);
//    				}else if(bits[i] == '1'){
//    					out.writeBit(1);
//    				}else{
//    					System.out.println("fehler");
//    				}
//    			}
//    			out.writeFillBits();
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
