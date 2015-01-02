package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.jpeg.marker.EnumMarker;
import main.file.stream.BitStream;

public class EOI implements Marker {
    
    @Override
    public void write(BitStream out) throws FileNotFoundException, IOException {
        out.write(EnumMarker.EOI.getValue());
    }

    /*Marker: EOI –End ofImage –0xff 0xd9
     Tritt einmal am Ende des File auf*/
}
