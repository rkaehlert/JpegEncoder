package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.file.jpeg.marker.EnumMarker;
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.stream.BitStream;

public class SOS implements Marker {
	
	byte[] length = new byte[2];
	List<byte[]> componentDetail = new ArrayList<byte[]>();
	
	@Override
	public void write(BitStream out) throws FileNotFoundException, IOException {
		out.write(EnumMarker.SOS.getValue());
		out.write(this.length);
		out.write(this.componentDetail.size());
		for(byte[] component : this.componentDetail){
			out.write(component[0]);
			out.writeValue(4, 0);
			out.writeValue(4, 0);
		}
		out.write(0);
		out.write(63);
		out.write(0);
	}

	public void addComponent(EnumComponentId componentId, EnumHTType enumHTTypeAc, EnumHTType enumHTTypeDc){
		byte[] component = new byte[3];
		component[0] = componentId.getValue().byteValue();
		component[1] = enumHTTypeAc.getValue().byteValue();
		component[2] = enumHTTypeDc.getValue().byteValue();
		this.componentDetail.add(component);
		this.setLength();
	}
	
	private void setLength(){
		this.length[0] = 0;
		this.length[1] = (byte)(6 + 2 * this.componentDetail.size());
	}
	
}