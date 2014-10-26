package exercise_one.file.stream;

import exercise_one.exception.NotYetImplementedException;

public enum EnumBitMask {

	ZERO(0x00),
	ONE(0x01);
	
	private int value;
	
	private EnumBitMask(int value){
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static EnumBitMask fromValue(int mask){
		try{
			EnumBitMask[] values = EnumBitMask.values();
			for(int index = 0; index < values.length; index++){
				if(values[index].getValue() == mask){
					return values[index];
				}
			}
			throw new NotYetImplementedException("enum with the specified value is not yet implemented");
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
