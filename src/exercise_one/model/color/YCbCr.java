package exercise_one.model.color;


public class YCbCr implements Colormodel {
	
	private double y = 0;
	private double cb = 0;
	private double cr = 0;
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getCb() {
		return cb;
	}
	public void setCb(double cb) {
		this.cb = cb;
	}
	public double getCr() {
		return cr;
	}
	public void setCr(double cr) {
		this.cr = cr;
	}
	@Override
	public String toString() {
		return "YCbCr [y=" + y + ", cb=" + cb + ", cr=" + cr + "]";
	}
	public YCbCr(double y, double cb, double cr) {
		super();
		this.y = y;
		this.cb = cb;
		this.cr = cr;
	}
	public YCbCr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
