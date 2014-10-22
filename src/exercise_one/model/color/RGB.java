package exercise_one.model.color;


public class RGB implements Colormodel
{

    private ColorChannel<Integer> red;
    private ColorChannel<Integer> green;
    private ColorChannel<Integer> blue;

    public RGB()
    {
        this(0,0,0);
    }
    
    public RGB(int rot, int gruen, int blau)
    {
        super();
        this.red = new ColorChannel<Integer>(rot);
        this.green = new ColorChannel<Integer>(gruen);
        this.blue = new ColorChannel<Integer>(blau);
    }
       
    @Override
    public String toString()
    {
        return String.format("%03d", red.getValue()) + " " + String.format("%03d", green.getValue()) + " " + String.format("%03d", blue.getValue());
    }

	public ColorChannel<Integer> getRedChannel() {
		return red;
	}
	
	public int getRed() {
		return red.getValue();
	}

	public void setRed(ColorChannel<Integer> red) {
		this.red = red;
	}
	
	public void setRed(int red) {
		this.red = new ColorChannel<Integer>(red);
	}

	public ColorChannel<Integer> getGreenChannel() {
		return this.green;
	}
	
	public Integer getGreen() {
		return this.green.getValue();
	}

	public void setGreen(ColorChannel<Integer> green) {
		this.green = green;
	}
	
	public void setGreen(int green) {
		this.green = new ColorChannel<Integer>(green);
	}

	public ColorChannel<Integer> getBlueChannel() {
		return blue;
	}
	
	public Integer getBlue() {
		return this.blue.getValue();
	}
	
	public void setBlue(int blue) {
		this.blue = new ColorChannel<Integer>(blue);
	}

	public void setBlue(ColorChannel<Integer> blue) {
		this.blue = blue;
	}

}
