package main.tester;



import java.io.File;
import java.io.IOException;

import main.exception.image.ImageException;
import main.exception.image.UnsupportedImageFormatException;
import main.file.image.JPEGImage;
import main.file.jpeg.segment.DHT;
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.resource.UtilityResourcePath;
import main.file.stream.SimpleBitWriter;
import main.logger.LoggerMap;
import main.model.huffman.tree.Leaf;

public class TesterSimpleBitStream {

    public static void main(String[] args) {
        try {
            SimpleBitWriter stream = new SimpleBitWriter(new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputSingleByte.jpg"));
            new TesterJpegEncoder().main(new String[0]);
            
            JPEGImage image = new JPEGImage(UtilityResourcePath.getPath("blue.ppm"), 8, 0);
            image.convertToYCbCr();
            
            image.writeData();
            
            DHT dht = new DHT();

            dht.addHT(EnumComponentId.Y, EnumHTType.DC, image.getModelEncoder().getLstHuffmanSymbol().get(0));
            dht.addHT(EnumComponentId.Y, EnumHTType.AC, image.getModelEncoder().getLstHuffmanSymbol().get(1));
            
            new LoggerMap<Leaf, String>().log(image.getModelEncoder().getLstHuffmanSymbol().get(0));
            new LoggerMap<Leaf, String>().log(image.getModelEncoder().getLstHuffmanSymbol().get(1)); 
            
            dht.write(stream);
            stream.write(255);
            stream.close();
//            LoggerTimer timer = new LoggerTimer();

//            timer.start();
//            for (long i = 0; i < 10000; i++) {
//                stream.writeInt(128);
//            }
//            timer.stop();
//            stream.close();
//            timer.log();
        }
        catch (IOException ex) {
        	ex.printStackTrace();
        } catch (UnsupportedImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
