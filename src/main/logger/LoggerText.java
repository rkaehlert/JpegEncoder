package main.logger;


public class LoggerText implements Logger {
	
	public static void log(String text){
		print(text);
	}
	
	public static void log(Integer number){
		print(String.valueOf(number));
	}
	
	private static void print(String text){
		System.out.println("Ausgabe: " + text);
	}

}
