package main.file.jpeg.segment;

import java.io.FileNotFoundException;
import java.io.IOException;
import main.file.stream.SimpleBitOutputStream;


public class DHT implements Marker {

	int length;
	byte ht;

	public DHT(){
		
	}

    @Override
    public void write(SimpleBitOutputStream out) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		
}
