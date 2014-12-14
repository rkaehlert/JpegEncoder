package main.file.jpeg.segment;

public enum EnumDestinationIdentifier {
	Y(0),
	CBCR(1);
	
	private Integer value = null;
	
	private EnumDestinationIdentifier(Integer value){
		this.setValue(value);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}

