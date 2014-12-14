package main.file.jpeg.segment.enums;

public enum EnumHTPrecision {
	EIGHT_BIT(0),
	SIXTEEN_BIT(1);
	
	private Integer value = null;
	
	private EnumHTPrecision(Integer value){
		this.setValue(value);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}