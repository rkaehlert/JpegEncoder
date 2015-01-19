package main;

import main.exception.common.ExceptionNotYetImplemented;
import main.formatter.Formatter;

public class FormatterEscapeByByte implements Formatter {

	public static StringBuffer format(String toEscape, String toReplace, StringBuffer input) {
		StringBuffer output = new StringBuffer();
        for(int index = 0; index < input.length(); index+=8){
        	if(index+8 <= input.length()){
	        	if(input.substring(index, index+8).equals(toEscape)){
	        		output.append(toReplace);
	        	}else{
	        		output.append(input.subSequence(index, index+8));
	        	}
        	}else{
        		output.append(input.subSequence(index, input.length()));
        	}
        }
	    return output;
	}

	@Override
	public String format() {
		try {
			throw new ExceptionNotYetImplemented();
		} catch (ExceptionNotYetImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

}
