package exercise_one.file.jpeg.segment;


public class SOF0 implements Marker{

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
	
	private int frame_header_length;
	private int precision;
	private int width;
	private int height;
	private int component_count;
	private Component[] components; 
	
	public SOF0(){
		this.precision = 8;
		this.component_count = 3;
		this.components = new Component[component_count];
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if(width <= 0){
			throw new IllegalArgumentException("ein wert groesser als 0 muss fuer die laenge definiert sein");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if(height <= 0){
			throw new IllegalArgumentException("ein wert groesser als 0 muss fuer die hoehe definiert sein");
		}
		this.height = height;
	}

	public int getFrame_header_length() {
		return frame_header_length;
	}

	public void setFrame_header_length(int frame_header_length) {
		this.frame_header_length = frame_header_length;
	}
	
	public int calculateLength(){
		return 8+this.component_count*3;
	}

	public int getComponent_count() {
		return component_count;
	}

	public void setComponent_count(int component_count) {
		this.component_count = component_count;
	}

	public Component[] getComponents() {
		return components;
	}

	public void setComponents(Component[] components) {
		this.components = components;
	}
}
