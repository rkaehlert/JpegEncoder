package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.jpeg.marker.EnumMarker;
import main.file.stream.BitStream;

public class SOI implements Marker{

    @Override
    public void write(BitStream out) throws FileNotFoundException, IOException {
        out.write(EnumMarker.SOI.getValue());
    }	
}
