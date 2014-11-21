package main.logger;

import main.model.matrix.Coordinate;

public class LoggerCoordinate implements Logger {
	
	public void log(Coordinate coordinate){
		System.out.print(coordinate.toString());
	}
}
