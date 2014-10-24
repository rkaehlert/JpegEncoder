package exercise_one.logger;

public class LoggerTimer implements Logger{

	Long time;
	Long duration;
	
        public LoggerTimer()
        {
            time = 0L;
            duration = 0L;
        }
	public void start(){
		this.time = System.currentTimeMillis();
	}
	
	public void stop(){
		duration += System.currentTimeMillis() - time;
	}

        public void reset()
        {
            time = null;
            duration = 0L;
        }
        
	@Override
	public void log() {
		System.out.println(System.lineSeparator() + "-------------------------------------");
		System.out.println("Gesamtlaufzeit: " + duration + " ms");
		System.out.println("-------------------------------------");
	}
}
