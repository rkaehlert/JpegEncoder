package file.jpeg.segment;

import file.jpeg.marker.EnumMarker;
import file.stream.SimpleBitOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SOI implements Marker{

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        out.writeByteArray(EnumMarker.SOI.getValue());
    }	
}
