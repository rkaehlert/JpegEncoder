package main.model.encoder;

import main.calculator.UtilityCalculateBitLength;
import main.converter.datatype.ConverterToBit;
import main.exception.common.ExceptionNotYetImplemented;

public class ModelAC {

	private String code;
	private Integer[] key;
	private Integer value;
	
	public ModelAC(){
		
	}
	
	public ModelAC(String code, Integer[] key, Integer value){
		this.code = code;
		this.key = key;
		this.setValue(value);
	}
	
	private int calculateCategory() throws ExceptionNotYetImplemented{
		return UtilityCalculateBitLength.calculate(key[1]);
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer[] getKey() {
		return key;
	}

	public void setKey(Integer[] key) {
		this.key = key;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.code);
		if(value != 0){		
			buffer.append(ConverterToBit.convert(this.value));
		}
		return buffer.toString();
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	
}
