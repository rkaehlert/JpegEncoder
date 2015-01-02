package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import main.file.jpeg.marker.EnumMarker;
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumSubSampling;
import main.file.stream.BitStream;

public class SOF0 implements Marker {

    /*
     Marker: SOF0 –Start ofFrame 0 –0xff 0xc0
     Länge (highbyte, lowbyte) 8 + Anzahl Komponenten*3
     JFIF benutzt entweder 1 Komponente (Y, Grauwertbilder) oder 3 Komponenten (YCbCr, Farbbilder)
     Genauigkeit Daten (1 Byte) in bits/sample, normalerweise 8 (12 und 16 in meisten Programmen nicht unterstützt)
     Bildgröße y (2 Byte, Hi-Lo), muss >0 sein
     Bildgröße x (2 Byte, Hi-Lo), muss >0 sein
     Anzahl Komponenten (1 Byte),
     Für jede Komponente 3 Byte
     ID Komponenten (1 = Y, 2 = Cb, 3 = Cr)
     Faktoren Unterabtastung (Bit 0-3 vertikal, 4-7 Horizontal) relativ zu größtem Sampling-Faktoren
     Keine Unterabtastung: 0x22
     Unterabtastung Faktor 2: 0x11
     Nummer der benutzten Quantisierungstabelle
     */
    private byte[] length;
    private byte precision;
    private byte[] width;
    private byte[] height;
    private byte component_count;
    private List<Component> components;

    public SOF0() {
        length = new byte[2];
        precision = 8;
        height = new byte[]{0x00,0x01};
        width = new byte[]{0x00,0x01};
        this.components = new ArrayList<Component>();
        calculateLength();
    }
    
    public void addComponent(EnumComponentId componentId, int quantizationTableNum, EnumSubSampling subSamplingFactor){
        Component component = new Component();
        component.setIdComponent(componentId);
        component.setQuantisizeTableNum((byte) quantizationTableNum);
        component.setSubSamplingFactor(subSamplingFactor);
        this.components.add(component);
        this.calculateLength();
        component_count = (byte)this.components.size();
    }
    
    @Override
    public void write(BitStream out) throws FileNotFoundException, IOException {
        out.write(EnumMarker.SOF0.getValue());
        out.write(length);
        out.write(precision);
        out.write(height);
        out.write(width);
        out.write(component_count);
        for (Component comp : components) {
            comp.write(out);
        }
    }

    public byte[] getLength() {
        return length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(byte precision) {
        this.precision = precision;
    }

    public byte[] getWidth() {
        return width;
    }

    public void setWidth(byte[] width) {
        if (new BigInteger(width).intValue() == 0) {
            throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
        }
        this.width = width;
    }

    public byte[] getHeight() {
        return height;
    }

    public void setHeight(byte[] height) {
        if (new BigInteger(height).intValue() == 0) {
            throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
        }
        this.height = height;
    }

    private void calculateLength() {
        int length_value = 8 + component_count * 3;

        if (length_value <= 256) {
            length[0] = 0x00;
            length[1] = BigInteger.valueOf(length_value).toByteArray()[0];
        }
        else {
            length = BigInteger.valueOf(length_value).toByteArray();
        }
    }

    public int getComponent_count() {
        return component_count;
    }

    public void setComponent_count(byte component_count) {
        this.component_count = component_count;
    }
    
    public void setLength(byte[] length) {
		this.length = length;
	}
}
