package main.file.jpeg.segment;

import main.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Marker {
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException;
}
