package exercise_one.file.jpeg.marker;


public enum EnumMarker {

	SOI(0xFFD8),
	EOI(0xFFD9),
	APP0(0xFFe0),
	SOF0(0xFFc0),
	DHT(0xFFc4);
	
	private int value;
	
	EnumMarker(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
