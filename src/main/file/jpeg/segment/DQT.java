package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.file.jpeg.marker.EnumMarker;
import main.file.jpeg.segment.enums.EnumDestinationIdentifier;
import main.file.stream.BitStream;

public class DQT implements Marker {

	private byte[] length = new byte[2];
	private List<QT> lstQT = new ArrayList<QT>();	
	
	public void addQT(EnumDestinationIdentifier destinationIdentifier, byte[] quantizationTable){
		this.lstQT.add(new QT(destinationIdentifier, quantizationTable));
    	this.setLength();
	}
	
	@Override
	public void write(BitStream out) throws FileNotFoundException, IOException {
    	out.write(EnumMarker.DQT.getValue());
    	out.write(this.length);
    	for(QT qt : this.lstQT){
    		qt.write(out);
    	}
	}
	
	public void setLength(){
		Integer output = 2;
		for(QT qt : this.lstQT){
			output += (65 + 64 * qt.getPq().getValue());
		}
		this.length[0] = 0;
		this.length[1] = output.byteValue();
	}

}
