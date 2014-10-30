package exercise_one.file.jpeg.segment;

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

		NO_UNIT(0),
		DOTS_PER_INCH(1),
		DOTS_PER_CM(2);
		
		int value;
		
		EnumJPEGResolutionUnit(int value){
			this.value = value;
		}
		
	}
	
	int offset;
	int length;
	byte[] identifier; //JFIF
	double major_revision;
	double minor_revision;
	EnumJPEGResolutionUnit unit;
	int density_x;
	int density_y;
	int thumbnail_x;
	int thumbnail_y;
	
	public APP0(){
		 this.unit = EnumJPEGResolutionUnit.DOTS_PER_INCH;
		 this.identifier = new byte[]{0x4a, 0x46, 0x49, 0x46, 0x00};
		 this.major_revision = 1;
		 this.minor_revision = 1.1;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if(length < 16){
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

	public int getDensity_x() {
		return density_x;
	}

	public void setDensity_x(int density_x) {
		if(density_x == 0){
			throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
		}
		this.density_x = density_x;
	}

	public int getDensity_y() {
		return density_y;
	}

	public void setDensity_y(int density_y) {
		if(density_y == 0){
			throw new IllegalArgumentException("die dichte sollte nicht den wert 0 haben");
		}
		this.density_y = density_y;
	}

	public int getThumbnail_x() {
		return thumbnail_x;
	}

	public void setThumbnail_x(int thumbnail_x) {
		this.thumbnail_x = thumbnail_x;
	}

	public int getThumbnail_y() {
		return thumbnail_y;
	}

	public void setThumbnail_y(int thumbnail_y) {
		this.thumbnail_y = thumbnail_y;
	}
	
	public int calculateThumbnailDimension(){
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

	public void setMajor_revision(double major_revision) {
		this.major_revision = major_revision;
	}

	public double getMinor_revision() {
		return minor_revision;
	}

	public void setMinor_revision(double minor_revision) {
		this.minor_revision = minor_revision;
	}
	
}