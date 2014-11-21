package main.file.jpeg.segment;

import main.file.jpeg.marker.EnumMarker;
import main.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

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
    private Component[] components;

    public SOF0() {
        length = new byte[2];
        precision = 8;
        height = new byte[]{0x00,0x01};
        width = new byte[]{0x00,0x01};
        component_count = 1;
        components = new Component[component_count];
        components[0] = new Component();
        components[0].setIdComponent(Component.EnumId.Y);
        components[0].setQuantisizeTableNum((byte) 1);
        components[0].setSubSamplingFactor(Component.EnumSubSampling.NONE);
        setLength();
    }

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.SOF0.getValue());
        out.writeByteArray(length);
        out.writeByte(precision);
        out.writeByteArray(height);
        out.writeByteArray(width);
        out.writeByte(component_count);
        for (Component comp : components) {
            out.writeByte(comp.getIdComponent());
            out.writeByte(comp.getSubSamplingFactor());
            out.writeByte(comp.getIdQuantisizeTableNum());
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

    private void setLength() {
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

    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        if (components.length == 1 || components.length == 3) {
            this.components = components;
            component_count = (byte)components.length;
            setLength();
        }
        else {
            throw new IllegalArgumentException("Nur ein oder drei Kanäle möglich");
        }
    }
}
