package model.color;

import java.text.DecimalFormat;

public class YCbCr implements Colormodel {

    private ColorChannel<Double> y;
    private ColorChannel<Double> cb;
    private ColorChannel<Double> cr;

    public YCbCr() {
        this.y = new ColorChannel<Double>(null);
        this.cb = new ColorChannel<Double>(null);
        this.cr = new ColorChannel<Double>(null);
    }

    public YCbCr(Double y, Double cb, Double cr) {
        super();
        this.y = new ColorChannel<Double>(y);
        this.cb = new ColorChannel<Double>(cb);
        this.cr = new ColorChannel<Double>(cr);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("00.00;-00.00");
        String y = this.y.getValue() == null ? " null " : format.format(this.y.getValue());
        String cb = this.cb.getValue() == null ? " null " : format.format(this.cb.getValue());
        String cr = this.cr.getValue() == null ? " null " : format.format(this.cr.getValue());
        return "(" + y + " " + cb + " " + cr + ")";
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

    public void add(Double y, Double cb, Double cr) {
        if (this.getY() == null) {
            this.y.setValue(0.0);
        }
        this.y.setValue(this.y.getValue() + y);
        if (this.getCb() == null) {
            this.cb.setValue(0.0);
        }
        this.cb.setValue(this.cb.getValue() + cb);
        if (this.getCr() == null) {
            this.cr.setValue(0.0);
        }
        this.cr.setValue(this.cr.getValue() + cr);
    }

}
