package main.converter.datatype;

public class ConverterToBit {

	public static String convert(Integer value){
		if(value >= 0){
			return Integer.toBinaryString(value);
		}else{
			StringBuffer output = new StringBuffer();
			char[] bits = Integer.toBinaryString(Math.abs(value)).toCharArray();
			for(int i = 0; i < bits.length; i++){
				if(bits[i] == '0'){
					output.append('1');
				}else{
					output.append('0');
				}
			}
			return output.toString();
		}
	}
	
}
