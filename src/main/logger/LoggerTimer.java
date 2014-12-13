package main.logger;

public class LoggerTimer implements Logger{

	Long counterInterruption;
	Long time;
	Long duration;
	
    public LoggerTimer(){
    	this.time = 0L;
    	this.duration = 0L;
        this.counterInterruption = 0L;
    }
        
	public void start(){
		this.time = System.currentTimeMillis();
	}
	
	public void stop(){
		this.counterInterruption++;
		this.duration += System.currentTimeMillis() - time;
	}

    public void reset(){
    	this.time = null;
    	this.counterInterruption = 0L;
    	this.duration = 0L;
    }
        
	public void log() {
		System.out.println(System.lineSeparator() + "-------------------------------------");
		System.out.println("Mittlere zeit: " + this.getArithmeticAverage() + " ms mit " + counterInterruption + " Durchlaeufen");
		System.out.println("-------------------------------------");
	}
	
	public Long getDuration() {
		return this.duration;
	}
	public void setDuration(Long duration) {
		this.duration = this.duration;
	}
	
	public Long getArithmeticAverage(){
		return this.duration / this.counterInterruption;
	}
	
}
