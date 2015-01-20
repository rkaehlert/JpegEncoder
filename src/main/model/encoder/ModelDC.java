package main.model.encoder;

import main.calculator.UtilityCalculateBitLength;
import main.converter.datatype.ConverterToBit;

public class ModelDC {

	int id = 0;
	
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
		StringBuffer output = new StringBuffer();
		output.append(this.code);
		if(delta != 0){
			output.append(ConverterToBit.convert(this.delta));
		}
		return output.toString();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
