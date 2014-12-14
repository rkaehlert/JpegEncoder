package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.file.stream.SimpleBitOutputStream;

public class QT implements Marker {
	
	
	private EnumDestinationIdentifier tq;
	private EnumHTPrecision pq;
	private byte[] q;
	
	public enum EnumHTPrecision {
		EIGHT_BIT(0),
		SIXTEEN_BIT(1);
		
		private Integer value = null;
		
		private EnumHTPrecision(Integer value){
			this.setValue(value);
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
	
	public QT(EnumDestinationIdentifier destinationIdentifier, byte[] quantizationTable){
		this.setTq(destinationIdentifier);
		this.setPq(EnumHTPrecision.EIGHT_BIT);
		this.q = quantizationTable;
	}
	
	@Override
	public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
		out.write(this.getPq().getValue().byteValue(), 4);
		out.write(this.getTq().getValue().byteValue(), 4);
		out.writeByteArray(this.q);
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
