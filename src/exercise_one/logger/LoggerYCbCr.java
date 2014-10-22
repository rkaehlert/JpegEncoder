package exercise_one.logger;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.YCbCr;

public class LoggerYCbCr implements Logger {

	@Override
	public void log() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
	}
	
	public void log(YCbCr ycbcr){
    	System.out.print(ycbcr.toString());
	}

}
