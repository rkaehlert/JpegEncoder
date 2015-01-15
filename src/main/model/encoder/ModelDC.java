package main.model.encoder;

import main.calculator.UtilityCalculateBitLength;
import main.converter.datatype.ConverterToBit;

public class ModelDC {

	private String code;
	private int delta;
	
	public ModelDC(){
		
	}
	
	public ModelDC(int delta){
		this.delta = delta;
	}
	
	public int calculateCategory() {
		return UtilityCalculateBitLength.calculate(this.delta);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	@Override
	public String toString(){
		return this.code + ConverterToBit.convert(this.delta);
	}
	
}
