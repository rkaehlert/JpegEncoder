package main.file.stream;

public interface BitStream {

	public void write(final int val);
    public void write(final byte[] bText);
    public void write(final String bText);
    public void writeBit(final int bit);
    public void writeValue(final int length, final int value);
    public void writeZeroFillBits();
    public void writeOneFillBits();
    public void close();
	
}
