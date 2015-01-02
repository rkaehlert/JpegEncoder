package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.jpeg.segment.enums.EnumDestinationIdentifier;
import main.file.jpeg.segment.enums.EnumHTPrecision;
import main.file.stream.BitStream;

public class QT implements Marker {
	
	
	private EnumDestinationIdentifier tq;
	private EnumHTPrecision pq;
	private byte[] q;
		
	public QT(EnumDestinationIdentifier destinationIdentifier, byte[] quantizationTable){
		this.setTq(destinationIdentifier);
		this.setPq(EnumHTPrecision.EIGHT_BIT);
		this.q = quantizationTable;
	}
	
	@Override
	public void write(BitStream out) throws FileNotFoundException, IOException {
		out.writeValue(4, this.getPq().getValue().byteValue());
		out.writeValue(4, this.getTq().getValue().byteValue());
		out.write(this.q);
	}

	public EnumDestinationIdentifier getTq() {
		return tq;
	}

	public void setTq(EnumDestinationIdentifier tq) {
		this.tq = tq;
	}

	public EnumHTPrecision getPq() {
		return pq;
	}

	public void setPq(EnumHTPrecision pq) {
		this.pq = pq;
	}

	public byte[] getQ() {
		return q;
	}

	public void setQ(byte[] q) {
		this.q = q;
	}
	
}
