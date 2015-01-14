package main.model.encoder;

public class ModelHT {

	private String value;
	private String code = "";
	
	public ModelHT(String value, String code){
		this.value = value;
		this.code = code;
	}
	
	public ModelHT(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
