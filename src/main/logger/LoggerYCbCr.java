package main.logger;

import main.model.color.YCbCr;

public class LoggerYCbCr implements Logger {
	
	public void log(YCbCr ycbcr){
    	System.out.print(ycbcr.toString());
	}

}
