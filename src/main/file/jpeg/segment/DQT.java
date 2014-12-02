package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.jpeg.marker.EnumMarker;
import main.file.stream.SimpleBitOutputStream;

public class DQT implements Marker {

	@Override
	public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
    	out.writeByteArray(EnumMarker.DQT.getValue());
	}

}
