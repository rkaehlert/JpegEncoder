package exercise_one.file.jpeg.segment;

import exercise_one.file.jpeg.marker.EnumMarker;
import exercise_one.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EOI implements Marker {
    
    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.EOI.getValue());
    }

    /*Marker: EOI –End ofImage –0xff 0xd9
     Tritt einmal am Ende des File auf*/
}
