package main.logger;

public class LoggerTimer implements Logger{

	Long countOfInterrupt;
	Long time;
	Long duration;
	
    public LoggerTimer(){
    	this.time = 0L;
    	this.duration = 0L;
        this.countOfInterrupt = 0L;
    }
        
	public void start(){
		this.time = System.currentTimeMillis();
	}
	
	public void stop(){
		this.countOfInterrupt++;
		this.duration += System.currentTimeMillis() - time;
	}

    public void reset(){
    	this.time = null;
    	this.countOfInterrupt = 0L;
    	this.duration = 0L;
    }
        
	public void log() {
		System.out.println("-------------------------------------");
		System.out.println("Mittlere zeit: " + this.getArithmeticAverage() + " ms mit " + countOfInterrupt + " Durchlaeufen");
		System.out.println("-------------------------------------");
	}
	
	public Long getDuration() {
		return this.duration;
	}
	public void setDuration(Long duration) {
		this.duration = this.duration;
	}
	
	public Long getArithmeticAverage(){
		return this.duration / this.countOfInterrupt;
	}
	
}
