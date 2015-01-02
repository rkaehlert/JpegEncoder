package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumSubSampling;
import main.file.stream.BitStream;

public class Component implements Marker {

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

	@Override
	public void write(BitStream out) throws FileNotFoundException,	IOException {
		out.write(this.getIdComponent());
        out.write(this.getSubSamplingFactor());
        out.write(this.getIdQuantisizeTableNum());
	}
}
