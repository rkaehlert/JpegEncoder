package main.model.color;

import java.text.DecimalFormat;

public class YCbCr implements Colormodel {

    private ColorChannel<Integer> y;
    private ColorChannel<Integer> cb;
    private ColorChannel<Integer> cr;

    public YCbCr() {
        this.y = new ColorChannel<Integer>(null);
        this.cb = new ColorChannel<Integer>(null);
        this.cr = new ColorChannel<Integer>(null);
    }

    public YCbCr(Integer y, Integer cb, Integer cr) {
        super();
        this.y = new ColorChannel<Integer>(y);
        this.cb = new ColorChannel<Integer>(cb);
        this.cr = new ColorChannel<Integer>(cr);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("00.00;-00.00");
        String y = this.y.getValue() == null ? " null " : format.format(this.y.getValue());
        String cb = this.cb.getValue() == null ? " null " : format.format(this.cb.getValue());
        String cr = this.cr.getValue() == null ? " null " : format.format(this.cr.getValue());
        return "(" + y + " " + cb + " " + cr + ")";
    }

    public ColorChannel<Integer> getYChannel() {
        return y;
    }

    public Integer getY() {
        return y.getValue();
    }

    public void setY(ColorChannel<Integer> y) {
        this.y = y;
    }

    public ColorChannel<Integer> getCbChannel() {
        return cb;
    }

    public Integer getCb() {
        return cb.getValue();
    }

    public void setCb(ColorChannel<Integer> cb) {
        this.cb = cb;
    }

    public ColorChannel<Integer> getCrChannel() {
        return cr;
    }

    public Integer getCr() {
        return cr.getValue();
    }

    public void setCr(ColorChannel<Integer> cr) {
        this.cr = cr;
    }

    public void add(Integer y, Integer cb, Integer cr) {
        if (this.getY() == null) {
            this.y.setValue(0);
        }
        this.y.setValue(this.y.getValue() + y);
        if (this.getCb() == null) {
            this.cb.setValue(0);
        }
        this.cb.setValue(this.cb.getValue() + cb);
        if (this.getCr() == null) {
            this.cr.setValue(0);
        }
        this.cr.setValue(this.cr.getValue() + cr);
    }

}
