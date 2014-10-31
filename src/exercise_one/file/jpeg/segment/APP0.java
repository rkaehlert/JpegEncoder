package exercise_one.file.jpeg.segment;

import exercise_one.file.jpeg.marker.EnumMarker;
import exercise_one.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class APP0 implements Marker {
    /*	 
     Marker: APP0 –0xff 0xe0
     Länge des Segments (highbyte, lowbyte), muss >= 16String 'JFIF'#0 (0x4a, 0x46, 0x49, 0x46, 0x00), kennzeichnet JFIF (JPEG File Interchange Format)
     majorrevisionnumber(1 byte), sollte 1 sein
     minorrevisionnumber(1 byte), sollte 0..2 sein (Schreiben Sie Version 1.1)
     Einheit Pixelgröße x/y (1 byte, Schreiben Sie 0)
     0 = Keine Einheit, gibt stattdessen Seitenverhältnis an
     1 = x/y-Dichte in dots/inch
     2 = x/y-Dichte in dots/cmx-Dichte (highbyte, lowbyte) sollte <> 0 (Schreiben Sie z.B. 0x0048)y-Dichte (highbyte, lowbyte) sollte <> 0 (Schreiben Sie z.B. 0x0048)
     Größe Vorschaubild x (1 byte) (Kein Vorschaubild 0)
     Größe Vorschaubild y (1 byte) (Kein Vorschaubild 0)nByte für Vorschaubild (RGB 24 bit), n = x*y*3 (Kein Vorschaubildn=0)
		
     ** Marker: APP0 (xFFE0) ***
     OFFSET: 0x00000002
     Length     = 16
     Identifier = [JFIF]
     version    = [1.2]
     density    = 900 x 900 DPI (dots per inch)
     thumbnail  = 0 x 0
     */

    public enum EnumJPEGResolutionUnit {

        NO_UNIT((byte) 0),
        DOTS_PER_INCH((byte) 1),
        DOTS_PER_CM((byte) 2);

        byte value;

        EnumJPEGResolutionUnit(byte value) {
            this.value = value;
        }

    }
    
    byte[] offset = new byte[2];
    byte[] length = new byte[2];
    byte[] identifier = new byte[]{0x4a, 0x46, 0x49, 0x46, 0x00};
    byte major_revision = 1;
    byte minor_revision = 2;
    EnumJPEGResolutionUnit unit;
    byte[] density_x = new byte[2];
    byte[] density_y = new byte[2];
    byte thumbnail_x = 0;
    byte thumbnail_y = 0;
    byte[] thumbnail_data;

    public APP0() {
        this.unit = EnumJPEGResolutionUnit.DOTS_PER_INCH;
        thumbnail_data = new byte[calculateThumbnailDimension()];
    }

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.APP0.getValue());
        out.writeByteArray(length);
        out.writeByteArray(identifier);
        out.writeByte(major_revision);
        out.writeByte(minor_revision);
        out.writeByte(EnumJPEGResolutionUnit.NO_UNIT.value);
        out.writeByteArray(density_x);
        out.writeByteArray(density_y);
        out.writeByte(thumbnail_x);
        out.writeByte(thumbnail_y);
        if(thumbnail_data.length != 0) {
            out.writeByteArray(thumbnail_data);
        }
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        if (new BigInteger(length).intValue() < 16) {
            throw new IllegalArgumentException("die laenge muss einen wert groesser oder gleich 16 haben");
        }
        this.length = length;
    }

    public EnumJPEGResolutionUnit getUnit() {
        return unit;
    }

    public void setUnit(EnumJPEGResolutionUnit unit) {
        this.unit = unit;
    }

    public byte[] getDensity_x() {
        return density_x;
    }

    public void setDensity_x(byte[] density_x) {
        if (new BigInteger(density_x).intValue() == 0) {
            throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
        }
        this.density_x = density_x;
    }

    public byte[] getDensity_y() {
        return density_y;
    }

    public void setDensity_y(byte[] density_y) {
        if (new BigInteger(density_y).intValue() == 0) {
            throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
        }
        this.density_y = density_y;
    }

    public int getThumbnail_x() {
        return thumbnail_x;
    }

    public void setThumbnail_x(byte thumbnail_x) {
        this.thumbnail_x = thumbnail_x;
    }

    public int getThumbnail_y() {
        return thumbnail_y;
    }

    public void setThumbnail_y(byte thumbnail_y) {
        this.thumbnail_y = thumbnail_y;
    }

    public int calculateThumbnailDimension() {
        return 3 * this.thumbnail_x * thumbnail_y;
    }

    public byte[] getIdentifier() {
        return identifier;
    }

    public void setIdentifier(byte[] identifier) {
        this.identifier = identifier;
    }

    public double getMajor_revision() {
        return major_revision;
    }
}
