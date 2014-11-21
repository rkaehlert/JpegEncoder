package main.file.jpeg.segment;

import main.file.jpeg.marker.EnumMarker;
import main.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SOI implements Marker{

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.SOI.getValue());
    }	
}
