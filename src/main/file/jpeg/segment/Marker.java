package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.stream.BitStream;

public interface Marker {
    public void write(BitStream out) throws FileNotFoundException, IOException;
}
