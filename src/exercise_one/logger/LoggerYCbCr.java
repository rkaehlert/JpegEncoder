package exercise_one.logger;

import exercise_one.model.color.YCbCr;

public class LoggerYCbCr implements Logger {
	
	public void log(YCbCr ycbcr){
    	System.out.print(ycbcr.toString());
	}

}
