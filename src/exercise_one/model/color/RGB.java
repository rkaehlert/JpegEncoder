package exercise_one.model.color;


public class RGB implements Colormodel
{

    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public RGB()
    {
        red = 0;
        green = 0;
        blue = 0;
    }
    
    public RGB(int rot, int gruen, int blau)
    {
        super();
        this.red = rot;
        this.green = gruen;
        this.blue = blau;
    }

    public int getRed()
    {
        return red;
    }

    public void setRed(int rot)
    {
        this.red = rot;
    }

    public int getGreen()
    {
        return green;
    }

    public void setGreen(int gruen)
    {
        this.green = gruen;
    }

    public int getBlue()
    {
        return blue;
    }

    public void setBlue(int blau)
    {
        this.blue = blau;
    }

    @Override
    public String toString()
    {
        return " RGB [rot = " + red + ", gruen = " + green + ", blau = " + blue + "]";
    }

}
