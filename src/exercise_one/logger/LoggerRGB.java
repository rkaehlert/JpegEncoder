package exercise_one.logger;

import exercise_one.model.color.RGB;

public class LoggerRGB implements Logger {
	
	public void log(RGB rgb){
		System.out.print(rgb.toString());
	}

}
