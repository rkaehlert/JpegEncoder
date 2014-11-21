package logger;

import model.color.YCbCr;

public class LoggerYCbCr implements Logger {
	
	public void log(YCbCr ycbcr){
    	System.out.print(ycbcr.toString());
	}

}
