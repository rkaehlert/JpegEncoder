package main.file.jpeg.segment;

import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumSubSampling;

public class Component {

    private byte[] value;

    public Component() {
        this.value = new byte[3];
    }

    public void setIdComponent(EnumComponentId id) {
        value[0] = id.getValue().byteValue();
    }

    public void setSubSamplingFactor(EnumSubSampling factor) {
        value[1] = (byte) factor.getValue();
    }
    
    public void setQuantisizeTableNum(byte value)
    {
        this.value[2] = value;
    }
    
    public byte getIdComponent()
    {
        return value[0];
    }
    
    public byte getSubSamplingFactor()
    {
        return value[1];
    }
    
    public byte getIdQuantisizeTableNum()
    {
        return value[2];
    }
}
