package exercise_one.logger;

public class LoggerTimer implements Logger{

	Long time = null;
	Long duration = null;
	
	public void start(){
		this.time = System.currentTimeMillis();
	}
	
	public void stop(){
		duration = System.currentTimeMillis() - time;
	}

	@Override
	public void log() {
		System.out.println(System.lineSeparator() + "-------------------------------------");
		System.out.println("Gesamtlaufzeit: " + duration + " ms");
		System.out.println("-------------------------------------");
	}
}
