package exercise_one.logger;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.RGB;

public class LoggerRGB implements Logger {

	@Override
	public void log() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
	}
	
	public void log(RGB rgb){
		System.out.print(rgb.toString());
	}

}
