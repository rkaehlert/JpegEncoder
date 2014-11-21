package main.logger;

import main.model.color.RGB;

public class LoggerRGB implements Logger {
	
	public void log(RGB rgb){
		System.out.print(rgb.toString());
	}

}
