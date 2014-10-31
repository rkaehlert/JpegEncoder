package exercise_one.file.jpeg.segment;

import exercise_one.file.jpeg.marker.EnumMarker;
import exercise_one.file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SOI implements Marker{

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.SOI.getValue());
    }	
}
