package exercise_one.model.color;

import java.text.DecimalFormat;

public class YCbCr implements Colormodel
{

    private ColorChannel<Double> y;
    private ColorChannel<Double> cb;
    private ColorChannel<Double> cr;    
    
    public YCbCr(){
    	super();
   	 	this.y = new ColorChannel<Double>(0.0);
    	this.cb = new ColorChannel<Double>(0.0);
    	this.cr = new ColorChannel<Double>(0.0);
    }
    
    public YCbCr(Double y, Double cb, Double cr){
    	super();
   	 	this.y = new ColorChannel<Double>(y);
    	this.cb = new ColorChannel<Double>(cb);
    	this.cr = new ColorChannel<Double>(cr);
    }

    @Override
    public String toString()
    {
        DecimalFormat format = new DecimalFormat(" ###000.00;-###000.00");
        String y = this.y.getValue() == null ? null : format.format(this.y.getValue());
        String cb = this.cb.getValue() == null ? null : format.format(this.cb.getValue());
        String cr = this.cr.getValue() == null ? null : format.format(this.cr.getValue());
        return y + " " + cb + " " + cr;
    }

    public YCbCr(ColorChannel<Double> y, ColorChannel<Double> cb, ColorChannel<Double> cr)
    {
        super();
        this.y = y;
        this.cb = cb;
        this.cr = cr;
    }

	public ColorChannel<Double> getYChannel() {
		return y;
	}
	
	public Double getY() {
		return y.getValue();
	}


	public void setY(ColorChannel<Double> y) {
		this.y = y;
	}


	public ColorChannel<Double> getCbChannel() {
		return cb;
	}
	
	public Double getCb() {
		return cb.getValue();
	}


	public void setCb(ColorChannel<Double> cb) {
		this.cb = cb;
	}


	public ColorChannel<Double> getCrChannel() {
		return cr;
	}
	
	public Double getCr() {
		return cr.getValue();
	}


	public void setCr(ColorChannel<Double> cr) {
		this.cr = cr;
	}
	
    public void add(Double y, Double cb, Double cr)
    {
    	if(y != null){
    		this.y.setValue(this.y.getValue() + y);
    	}
    	if(cb != null){
    		this.cb.setValue(this.cb.getValue() + cb);
    	}
    	if(cr != null){
    		this.cr.setValue(this.cr.getValue() + cr);
    	}
    }

}
