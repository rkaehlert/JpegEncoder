package exercise_one.model.color;

import java.text.DecimalFormat;

public class YCbCr implements Colormodel
{

    private Double y = 0.0;
    private Double cb = 0.0;
    private Double cr = 0.0;

    public Double getY()
    {
        return y;
    }

    public void setY(Double y)
    {
        this.y = y;
    }

    public Double getCb()
    {
        return cb;
    }

    public void setCb(Double cb)
    {
        this.cb = cb;
    }

    public Double getCr()
    {
        return cr;
    }

    public void setCr(Double cr)
    {
        this.cr = cr;
    }

    @Override
    public String toString()
    {
        DecimalFormat format = new DecimalFormat(" ###000.00;-###000.00");
        return format.format(y) + " " + format.format(cb) + " " + format.format(cr);
    }

    public YCbCr(Double y, Double cb, Double cr)
    {
        super();
        this.y = y;
        this.cb = cb;
        this.cr = cr;
    }

    public YCbCr()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public void add(Double y, Double cb, Double cr)
    {
        this.y += y;
        this.cb += cb;
        this.cr += cr;
    }

}
