package logger;

public class LoggerError implements Logger {


	public static void log(String text){
		System.err.println("Fehler: " + text);
	}
	
}
