package exercise_one.file.jpeg.segment;

public class Component {
	
	public enum EnumQuantizationTable {
		
	}
	
	public enum EnumSubSampling {
		
		NONE(0x22),
		FACTOR_2(0x11);
		
		private int value; 
		
		EnumSubSampling(int value){
			this.value = value;
		}
		
	}

	public enum EnumId {
		Y(1),
		CB(2),
		CR(3);
		
		int value;
		
		EnumId(int value){
			this.value = value;
		}
	}

	private byte[] value;
	
	public Component(){
		this.value = new byte[3];
	}
	
	public void setIdComponent(EnumId id){
		value[0] = (byte)id.value;
	}
	
	public void setSubSamplingFactor(EnumSubSampling factor){
		value[1] = (byte)factor.value;
	}
}
