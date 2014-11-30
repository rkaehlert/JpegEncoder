package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.file.jpeg.segment.DHT.EnumHTNumber;
import main.file.jpeg.segment.DHT.EnumHTType;
import main.file.stream.SimpleBitOutputStream;

public class HT implements Marker {
	private byte[] information;
	private Map<Integer, List<byte[]>> symbols;
	
	public HT(){
		this.information = new byte[3];
		this.setSymbols(new HashMap<Integer, List<byte[]>>());
	}
	
	public void addSymbol(byte[] code){
		if(this.validateLength(code.length) == true){
			Integer key = code.length;
			if(this.getSymbols().containsKey(key) == false){
				List<byte[]> lstBytes = new ArrayList<byte[]>(Arrays.asList(code));
				this.getSymbols().put(key, lstBytes);
			}else{
				this.getSymbols().get(key).add(code);
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

	public Map<Integer, List<byte[]>> getSymbols() {
		return symbols;
	}

	public void setSymbols(Map<Integer, List<byte[]>> symbols) {
		this.symbols = symbols;
	}

	@Override
	public void write(SimpleBitOutputStream out) throws FileNotFoundException,	IOException {
    	out.write(this.getInformation()[0], 4);
    	out.write(this.getInformation()[1], 1);
    	out.write(this.getInformation()[2], 3);
    	
		for(Map.Entry<Integer, List<byte[]>> currentEntry : this.getSymbols().entrySet()){
    		List<byte[]> value = currentEntry.getValue();
    		for(byte[] currentByte : value){
    			for(int index = 0; index < currentByte.length; index++){
        			out.write(currentByte[index], 1);	
    			}
    		}
    	}
		
		for(Map.Entry<Integer, List<byte[]>> currentEntry : this.getSymbols().entrySet()){
    		out.writeInt(currentEntry.getKey());
    	}
	}

	public byte[] getInformation() {
		return information;
	}

	public void setInformation(byte[] information) {
		this.information = information;
	}
}