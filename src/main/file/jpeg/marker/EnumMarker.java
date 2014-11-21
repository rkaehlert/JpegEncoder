package main.file.jpeg.marker;

public enum EnumMarker {

    SOI((byte)255,(byte)216),
    EOI((byte)255,(byte)217),
    APP0((byte)255,(byte)224),
    SOF0((byte)255,(byte)192),
    DHT((byte)255,(byte)194);

    private byte[] value;

    EnumMarker(byte highByte, byte lowByte) {
        value = new byte[2];
        this.value[0] = highByte;
        this.value[1] = lowByte;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

}
