package file.jpeg.segment;

import file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Marker {
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException;
}
