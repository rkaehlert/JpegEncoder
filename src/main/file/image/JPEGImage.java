package main.file.image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.calculator.CalculatorDelta;

import main.calculator.CalculatorDeltaCategory;
import main.calculator.UtilityCalculateBitLength;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.converter.ConverterHuffmanTreeLengthLimited;
import main.converter.ConverterHuffmanTreeToCollectionSymbol;
import main.converter.ConverterMatrixByQuantizationTable;
import main.converter.ConverterMatrixToZickZackSequence;
import main.converter.ConverterRGBToYCbCr;
import main.converter.ConverterRunLengthEncoding;
import main.converter.ConverterYCbCrToMatrixByColorchannel;
import main.converter.datatype.ConverterStringToInteger;
import main.converter.datatype.ConverterToBit;
import main.converter.datatype.ConverterToByte;
import main.converter.datatype.ConverterToDouble;
import main.encoder.huffman.CollectionSymbol;
import main.encoder.huffman.EncoderHuffmanTree;
import main.exception.common.ExceptionInvalidParameter;
import main.exception.image.ImageException;
import main.exception.image.UnsupportedImageFormatException;
import main.file.jpeg.segment.APP0;
import main.file.jpeg.segment.DHT;
import main.file.jpeg.segment.DQT;
import main.file.jpeg.segment.EOI;
import main.file.jpeg.segment.SOF0;
import main.file.jpeg.segment.SOI;
import main.file.jpeg.segment.SOS;
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumDestinationIdentifier;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.jpeg.segment.enums.EnumSubSampling;
import main.file.stream.SimpleBitWriter;
import main.filter.FilterMatrixByFirstElementOf8x8Block;
import main.formatter.FormatterRightGrowingTree;
import main.formatter.FormatterRunLengthEncodingByCategory;
import main.model.color.Colormodel;
import main.model.color.RGB;
import main.model.color.YCbCr;
import main.model.encoder.ModelEncoder;
import main.model.huffman.tree.Leaf;
import main.model.huffman.tree.Tree;
import main.model.matrix.Coordinate;
import main.model.quantization.JPEGQuantizationTable;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class JPEGImage extends Image implements Cloneable {

    private static final int ASCII_LINE_FEED = 10;

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private CollectionSymbol huffmanCodeTable = new CollectionSymbol();
    private ModelEncoder modelEncoder = new ModelEncoder();

    public JPEGImage() {
        width = 0;
        height = 0;
        maxColorValue = 0;
    }

    public JPEGImage(String src, int schrittweite, int fillmode) throws UnsupportedImageFormatException, ImageException, IOException {
        this();
        readPPMImageFromFile(src);
        addBorderBasedOnStepSize(schrittweite, fillmode);
    }

    private void readPPMImageFromFile(String sourceFile) throws UnsupportedImageFormatException, ImageException, IOException {
        File imageFile = new File(sourceFile);
        FileReader fr = null;
        boolean isLineBreak = false;
        int zeichen;
        int valueCounter = 0;
        int rowCounter = 0;
        int columnCounter = 0;
        int currentChannel = RED;
        RGB currentPixel = new RGB();

        try {
            fr = new FileReader(imageFile);

            if ((char) fr.read() == 'P' && (char) fr.read() == '3') {
                ArrayList<Character> charList = new ArrayList<>();

                while ((zeichen = fr.read()) != -1) {
                    if ((char) zeichen >= '0' && (char) zeichen <= '9') {
                        charList.add((char) zeichen);
                    }
                    else if ((char) zeichen == '#') {
                        while (!isLineBreak) {
                            zeichen = fr.read();
                            //TODO was wenn anderes OS? LINE_FEED auch 10?
                            isLineBreak = zeichen == ASCII_LINE_FEED;
                        }
                    }
                    else {
                        if (!charList.isEmpty()) {
                            if (valueCounter == 0) {
                                width = ConverterStringToInteger.convert(charList);
                            }
                            else if (valueCounter == 1) {
                                height = ConverterStringToInteger.convert(charList);
                            }
                            else if (valueCounter == 2) {
                                maxColorValue = ConverterStringToInteger.convert(charList);
                                if (!(maxColorValue > 0 && maxColorValue < 65536)) {
                                    throw new ImageException("der maximale farbwert darf nicht groesser als 65536 sein");
                                }
                            }
                            else {
                                if (currentChannel == RED) {
                                    currentPixel = new RGB();
                                    currentPixel.setRed(ConverterStringToInteger.convert(charList));
                                    currentChannel = GREEN;
                                }
                                else if (currentChannel == GREEN) {
                                    currentPixel.setGreen(ConverterStringToInteger.convert(charList));
                                    currentChannel = BLUE;
                                }
                                else if (currentChannel == BLUE) {
                                    currentPixel.setBlue(ConverterStringToInteger.convert(charList));
                                    pixel.put(new Coordinate(columnCounter, rowCounter), currentPixel);
                                    currentChannel = RED;
                                    columnCounter++;

                                    if (columnCounter == width) {
                                        rowCounter++;
                                        columnCounter = 0;
                                    }
                                }
                            }
                            valueCounter++;
                            charList.clear();
                        }
                    }
                }

                if ((width * height) != super.getPixel().size()) {
                    throw new ImageException("Es konnten nicht alle Pixel eingelesen werden. Möglicherweise ist die Datei beschädigt.");
                }
            }
            else {
                throw new UnsupportedImageFormatException();
            }
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Einlesen des Bildes.");
            throw ex;
        }
        finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            }
            catch (IOException e) {
                System.out.println("Fehler beim Schliessen des Streams.");
                throw e;
            }
        }
    }

    private void addBorderBasedOnStepSize(int schrittweite, int fillmode) {
        int columnCounter = 0;
        int rowCounter = 0;
        int randRechts = 0;
        int randUnten = 0;
        RGB bottomRightPixel = (RGB) pixel.lastEntry().getValue();

        randRechts = ((width % schrittweite) == 0) ? 0 : (schrittweite - (width % schrittweite));
        if (0 <= randRechts) {
            while (columnCounter < randRechts) {
                for (int i = 0; i < height; i++) {
                    if (fillmode == 1) {
                        pixel.put(new Coordinate(width + columnCounter, i), pixel.get(new Coordinate(width - 1, i)));
                    }
                    else {
                        pixel.put(new Coordinate(width + columnCounter, i), new RGB(fillmode, fillmode, fillmode));
                    }
                }
                columnCounter++;
            }
        }

        randUnten = ((height % schrittweite) == 0) ? 0 : (schrittweite - (height % schrittweite));
        if (0 <= randUnten) {
            while (rowCounter < randUnten) {
                for (int i = 0; i < width + randRechts; i++) {
                    if (i < width) {
                        if (fillmode == 1) {
                            pixel.put(new Coordinate(i, height + rowCounter), pixel.get(new Coordinate(i, height - 1)));
                        }
                        else {
                            pixel.put(new Coordinate(i, height + rowCounter), new RGB(fillmode, fillmode, fillmode));
                        }
                    }
                    else {
                        if (fillmode == 1) {
                            pixel.put(new Coordinate(i, height + rowCounter), bottomRightPixel);
                        }
                        else {
                            pixel.put(new Coordinate(i, height + rowCounter), new RGB(fillmode, fillmode, fillmode));
                        }

                    }
                }
                rowCounter++;
            }
            height += randUnten;
            width += randRechts;
        }
    }

    public void convertToYCbCr() {
        pixel = ConverterRGBToYCbCr.convert(pixel);
    }

    
    public void writeToFile(SimpleBitWriter out) throws IOException {
    	   	
    	this.writeData();
    	
        new SOI().write(out);

        APP0 app0 = new APP0();
        app0.write(out);

        DQT dqt = new DQT();
        
        double[][] jpegStdChrominance = ConverterToDouble.convert(JPEGQuantizationTable.JpegStdChrominance);
  		Array2DRowRealMatrix quantizationMatrixChrominance = new Array2DRowRealMatrix(jpegStdChrominance);
  		
  		double[][] jpegStdLuminance = ConverterToDouble.convert(JPEGQuantizationTable.JpegStdLuminance);
  		Array2DRowRealMatrix quantizationMatrixLuminance = new Array2DRowRealMatrix(jpegStdLuminance);
        
        dqt.addQT(EnumDestinationIdentifier.Y, ConverterToByte.convert(new ConverterMatrixToZickZackSequence().convert(quantizationMatrixLuminance)));
        dqt.addQT(EnumDestinationIdentifier.CBCR, ConverterToByte.convert(new ConverterMatrixToZickZackSequence().convert(quantizationMatrixChrominance)));
        dqt.write(out);
        
        
        SOF0 sof0 = new SOF0();
        sof0.setHeight(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(height, 2));
        sof0.setWidth(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(width, 2));
        sof0.addComponent(EnumComponentId.Y, 0, EnumSubSampling.FACTOR_2);
        sof0.addComponent(EnumComponentId.Cb, 1, EnumSubSampling.FACTOR_2);
        sof0.addComponent(EnumComponentId.Cr, 1, EnumSubSampling.FACTOR_2);
        sof0.write(out);
        DHT dht = new DHT();

        dht.addHT(EnumComponentId.Y, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(0));
        dht.addHT(EnumComponentId.Y, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(1));
        
        dht.addHT(EnumComponentId.Cb, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(2));
        dht.addHT(EnumComponentId.Cb, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(3));
//        
        dht.addHT(EnumComponentId.Cr, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(4));
        dht.addHT(EnumComponentId.Cr, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(5));
        dht.write(out);
        

        this.modelEncoder.printHuffmanTable();
        
    	SOS sos = new SOS();
        sos.addComponent(EnumComponentId.Y, 0, 0);
        sos.addComponent(EnumComponentId.Cb, 1, 1);
        sos.addComponent(EnumComponentId.Cr, 2, 2);
        sos.write(out);   
        
        //this.calculateBitsOfDC(this.modelEncoder.getLstHuffmanSymbol().get(0), this.modelEncoder.getLstDeltaDCCoefficientY());
    	this.calculateBitsOfAC(this.modelEncoder.getLstHuffmanSymbol().get(1), this.modelEncoder.getLstRunLengthEncodedZickZackY());
    	//this.calculateBitsOfDC(this.modelEncoder.getLstHuffmanSymbol().get(2), this.modelEncoder.getLstDeltaDCCoefficientCb());
    	this.calculateBitsOfAC(this.modelEncoder.getLstHuffmanSymbol().get(3), this.modelEncoder.getLstRunLengthEncodedZickZackCb());
    	//this.calculateBitsOfDC(this.modelEncoder.getLstHuffmanSymbol().get(4), this.modelEncoder.getLstDeltaDCCoefficientCr());
    	this.calculateBitsOfAC(this.modelEncoder.getLstHuffmanSymbol().get(5), this.modelEncoder.getLstRunLengthEncodedZickZackCr());
    	
//        out.write(255);
//
//        out.write(255);
    	
    	for(Integer index = 0; index < this.modelEncoder.lstDeltaDCCoefficientY.size(); index++){
        	Integer currentYDC = this.modelEncoder.getLstDeltaDCCoefficientY().get(index);
        	Integer category = UtilityCalculateBitLength.calculate(currentYDC);
    		List<Integer[]> currentYAC = this.modelEncoder.rleHuffmanCodesY.get(index);
    		String currentYDCCode = this.modelEncoder.getLstHuffmanSymbol().get(0).get(currentYDC);
    		out.writeValue(8,Integer.valueOf(currentYDCCode));
    		out.write(Integer.valueOf(currentYDC));
    		for(Integer[] ac : currentYAC){
    			for(int acIndex = 0; acIndex < ac.length; acIndex+=2){
    				Integer[] currentKey = new Integer[]{
    						ac[acIndex],
    						ac[acIndex+1]
    				};
    				String currentYACCode = this.modelEncoder.getLstHuffmanSymbol().get(1).get(currentKey);
    				out.write(currentYACCode);
    				out.write(ac[acIndex+1]);
    			}
    		}
            if(index != 0 && index % 4 == 0){
	            Integer currentCb = this.modelEncoder.lstDeltaDCCoefficientCb.get(0);
	            category = UtilityCalculateBitLength.calculate(currentCb);
	            String currentCBCode = this.modelEncoder.getLstHuffmanSymbol().get(2).get(category);
	            this.modelEncoder.lstDeltaDCCoefficientCb.remove(currentCb);
	            out.writeValue(8, Integer.valueOf(currentCBCode));
	            out.write(currentCb);
	            
	            Integer[] currentCbAC = this.modelEncoder.rleHuffmanCodesCb.get(index).get(0);
	            this.modelEncoder.rleHuffmanCodesCb.remove(0);
	            for(int acIndex = 0; acIndex < currentCbAC.length; acIndex+=2){
	            	Integer[] currentKey = new Integer[]{
	            			currentCbAC[acIndex],
	            			currentCbAC[acIndex+1]
    				};
    				String currentCbACCode = this.modelEncoder.getLstHuffmanSymbol().get(4).get(currentKey);
    				out.write(currentCbACCode);
    				out.write(currentCbAC[acIndex+1]);
	            }
	            
	            
	            Integer currentCr = this.modelEncoder.lstDeltaDCCoefficientCr.get(0);
	            category = UtilityCalculateBitLength.calculate(currentCr);
	            String currentCrCode = this.modelEncoder.getLstHuffmanSymbol().get(5).get(category);
	            this.modelEncoder.lstDeltaDCCoefficientCr.remove(currentCr);
	            out.writeValue(8, Integer.valueOf(currentCrCode));
	            out.write(currentCr);
	            
	            Integer[] currentCrAC = this.modelEncoder.rleHuffmanCodesCr.get(index).get(0);
	            this.modelEncoder.rleHuffmanCodesCr.remove(0);
	            for(int acIndex = 0; acIndex < currentCrAC.length; acIndex+=2){
	            	Integer[] currentKey = new Integer[]{
	            			currentCrAC[acIndex],
	            			currentCrAC[acIndex+1]
    				};
    				String currentCrACCode = this.modelEncoder.getLstHuffmanSymbol().get(6).get(currentKey);
    				out.write(currentCrACCode);
    				out.write(currentCrAC[acIndex+1]);
	            }        	
	        }
            index++;
        }
    	 for(Map.Entry<Tree, String> currentEntryOfYAC : this.modelEncoder.getLstHuffmanSymbol().get(0).entrySet()){
         	Leaf leaf = (Leaf)currentEntryOfYAC.getKey();
         	out.write(leaf.getValue().toString());
         }
        for(Map.Entry<Tree, String> currentEntry : this.modelEncoder.getLstHuffmanSymbol().get(1).entrySet()){
        	out.write(currentEntry.getValue());
        }
        for(Map.Entry<Tree, String> currentEntry : this.modelEncoder.getLstHuffmanSymbol().get(2).entrySet()){
        	Leaf leaf = (Leaf)currentEntry.getKey();
        	out.write(leaf.getValue().toString());
        }
        for(Map.Entry<Tree, String> currentEntry : this.modelEncoder.getLstHuffmanSymbol().get(3).entrySet()){
        	out.write(currentEntry.getValue());
        }
        for(Map.Entry<Tree, String> currentEntry : this.modelEncoder.getLstHuffmanSymbol().get(4).entrySet()){
        	Leaf leaf = (Leaf)currentEntry.getKey();
        	out.write(leaf.getValue().toString());
        }
        for(Map.Entry<Tree, String> currentEntry : this.modelEncoder.getLstHuffmanSymbol().get(5).entrySet()){
        	out.write(currentEntry.getValue());
        }
        
        new EOI().write(out);
        out.close();

    }
    
    public void writeData() {
       	
    	EncoderHuffmanTree encoder = new EncoderHuffmanTree();
    	
    	Array2DRowRealMatrix pixelYChannel = ConverterYCbCrToMatrixByColorchannel.convertY(this.pixel, this.height, this.width);
    	Array2DRowRealMatrix pixelCbChannel = ConverterYCbCrToMatrixByColorchannel.convertCb(this.pixel, this.height, this.width);
    	Array2DRowRealMatrix pixelCrChannel = ConverterYCbCrToMatrixByColorchannel.convertCr(this.pixel, this.height, this.width);
    	
    	List<Array2DRowRealMatrix> dctYChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelYChannel,"Y");
    	List<Array2DRowRealMatrix> dctCbChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCbChannel,"Cb");
    	List<Array2DRowRealMatrix> dctCrChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCrChannel,"Cr");
    	
    	List<Array2DRowRealMatrix> quantizedYChannel = this.createQuantizationTable(dctYChannel, JPEGQuantizationTable.JpegStdLuminance);
    	List<Array2DRowRealMatrix> quantizedCbChannel = this.createQuantizationTable(dctCbChannel, JPEGQuantizationTable.JpegStdChrominance);
    	List<Array2DRowRealMatrix> quantizedCrChannel = this.createQuantizationTable(dctCrChannel, JPEGQuantizationTable.JpegStdChrominance);
    	
    	List<Integer[]> lstZickZackSequenceY = ConverterMatrixToZickZackSequence.convert(quantizedYChannel);
    	List<Integer[]> lstZickZackSequenceCb = ConverterMatrixToZickZackSequence.convert(quantizedCbChannel);
    	List<Integer[]> lstZickZackSequenceCr = ConverterMatrixToZickZackSequence.convert(quantizedCrChannel);
    	
    	//ermitteln aller DC Koeffizienten
    	List<Integer> lstDCCoefficientY = FilterMatrixByFirstElementOf8x8Block.filter(lstZickZackSequenceY);
    	List<Integer> lstDCCoefficientCb = FilterMatrixByFirstElementOf8x8Block.filter(lstZickZackSequenceCb);
    	List<Integer> lstDCCoefficientCr = FilterMatrixByFirstElementOf8x8Block.filter(lstZickZackSequenceCr);
    	
    	this.modelEncoder.lstDeltaDCCoefficientY = CalculatorDelta.calculate(lstDCCoefficientY);
    	this.modelEncoder.lstDeltaDCCoefficientCb = CalculatorDelta.calculate(lstDCCoefficientCb);
    	this.modelEncoder.lstDeltaDCCoefficientCr = CalculatorDelta.calculate(lstDCCoefficientCr);
    	
        this.modelEncoder.lstDeltaDCCoefficientYCategory = CalculatorDeltaCategory.calculate(this.modelEncoder.lstDeltaDCCoefficientY);
    	this.modelEncoder.lstDeltaDCCoefficientCbCategory = CalculatorDeltaCategory.calculate(this.modelEncoder.lstDeltaDCCoefficientCb);
    	this.modelEncoder.lstDeltaDCCoefficientCrCategory = CalculatorDeltaCategory.calculate(this.modelEncoder.lstDeltaDCCoefficientCr);
        
    	encoder.encode(new LinkedList<Object>(this.modelEncoder.lstDeltaDCCoefficientY));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeY = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(this.modelEncoder.lstDeltaDCCoefficientCb));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCb = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(this.modelEncoder.lstDeltaDCCoefficientCr));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCr = encoder.getPathCollection();
    
    	//ermitteln aller AC Koeffizienten
    	this.modelEncoder.lstRunLengthEncodedZickZackY = ConverterRunLengthEncoding.convert(lstZickZackSequenceY);
    	this.modelEncoder.lstRunLengthEncodedZickZackCb = ConverterRunLengthEncoding.convert(lstZickZackSequenceCb);
    	this.modelEncoder.lstRunLengthEncodedZickZackCr = ConverterRunLengthEncoding.convert(lstZickZackSequenceCr);
    	
    	this.modelEncoder.rleHuffmanCodesY = FormatterRunLengthEncodingByCategory.format(this.modelEncoder.lstRunLengthEncodedZickZackY);
    	this.modelEncoder.rleHuffmanCodesCb = FormatterRunLengthEncodingByCategory.format(this.modelEncoder.lstRunLengthEncodedZickZackCb);
    	this.modelEncoder.rleHuffmanCodesCr = FormatterRunLengthEncodingByCategory.format(this.modelEncoder.lstRunLengthEncodedZickZackCr);
    	
    	//erstellen der huffman baeume zu den ac koeffizienten
    	
    	if(this.modelEncoder.lstRunLengthEncodedZickZackY.size() != lstDCCoefficientY.size()){
    		throw new ExceptionInvalidParameter("die groesse der dc und ac koeffizienten sind nicht gleich");
    	}
    	
    	List<Integer[]> combinedRleHuffmanCodesY = new LinkedList<Integer[]>();
    	List<Integer[]> combinedRleHuffmanCodesCb = new LinkedList<Integer[]>();
    	List<Integer[]> combinedRleHuffmanCodesCr = new LinkedList<Integer[]>();
    	
    	for(List<Integer[]> temp : this.modelEncoder.rleHuffmanCodesY ){
    		combinedRleHuffmanCodesY.addAll(temp);
    	}
    	for(List<Integer[]> temp : this.modelEncoder.rleHuffmanCodesCb ){
    		combinedRleHuffmanCodesCb.addAll(temp);
    	}
    	for(List<Integer[]> temp : this.modelEncoder.rleHuffmanCodesCr ){
    		combinedRleHuffmanCodesCr.addAll(temp);
    	}
    	
    	encoder.encodeJPEG(combinedRleHuffmanCodesY);
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfACTreeY = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encodeJPEG(combinedRleHuffmanCodesCb);
    	CollectionSymbol collectionSymbolOfACTreeCb = encoder.getPathCollection();
    	encoder.getTree();
    	encoder = new EncoderHuffmanTree();
    	encoder.encodeJPEG(combinedRleHuffmanCodesCr);
    	CollectionSymbol collectionSymbolOfACTreeCr = encoder.getPathCollection();
    	encoder.getTree();
  
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfDCTreeY);
    	
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfACTreeY);
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfDCTreeCb);
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfACTreeCb);
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfDCTreeCr);
    	this.getModelEncoder().getLstHuffmanSymbol().add(collectionSymbolOfACTreeCr);
	}

	private CollectionSymbol calculateBitsOfDC(CollectionSymbol collectionSymbolOfDCTree, List<Integer> delta){
		Map<Integer, String> buffer = new LinkedHashMap<Integer,String>();
		for(Integer currentDelta : delta){
	    	if(collectionSymbolOfDCTree.containsKey(currentDelta) == false){
				throw new ExceptionInvalidParameter("der delta " + currentDelta + " ist nicht in der symboltabelle");
			}	    	
	    	String bitValue = collectionSymbolOfDCTree.get(currentDelta) + ConverterToBit.convert(currentDelta); 
	    	buffer.put(currentDelta, bitValue);
		}
		for(Map.Entry<Integer,String> currentEntry : buffer.entrySet()){
			collectionSymbolOfDCTree.setTreeValue(currentEntry.getKey(), currentEntry.getValue());
		}
		return collectionSymbolOfDCTree;
    }
    
	private CollectionSymbol calculateBitsOfAC(CollectionSymbol collectionSymbolOfACTree, List<Integer[]> lstRunLengthEncodedZickZack){
		Map<Integer[], String> buffer = new LinkedHashMap<Integer[],String>();
    	Integer[] currentRunLengthPair = new Integer[2];
    	for(Integer[] currentRunLengthEncodedZickZack : lstRunLengthEncodedZickZack){
	    	for(int i = 0; i < currentRunLengthEncodedZickZack.length; i+=2){
	    		int category = UtilityCalculateBitLength.calculate(currentRunLengthEncodedZickZack[i+1]);
	    		currentRunLengthPair = new Integer[]{currentRunLengthEncodedZickZack[i], category};
				if(collectionSymbolOfACTree.containsKey(currentRunLengthPair) == false){
					throw new ExceptionInvalidParameter("der delta " + currentRunLengthPair + " ist nicht in der symboltabelle");
				}
				Integer[] key = new Integer[]{currentRunLengthPair[0],currentRunLengthPair[1]};
				String bitValue = collectionSymbolOfACTree.get(key) + ConverterToBit.convert(currentRunLengthEncodedZickZack[i+1]);
				buffer.put(key, bitValue);
	    	}
    	}
    	for(Map.Entry<Integer[],String> currentEntry : buffer.entrySet()){
    		collectionSymbolOfACTree.setTreeValue(currentEntry.getKey(), currentEntry.getValue());
		}
		return collectionSymbolOfACTree;
    }
	
//    private StringBuffer calculateBitsOfACBlock(CollectionSymbol collectionSymbolOfACTree, Integer[] lstRunLengthEncodedZickZack){
//    	StringBuffer output = new StringBuffer();
//    	Integer[] currentRunLengthPair = new Integer[2];
//    	for(int i = 0; i < lstRunLengthEncodedZickZack.length; i+=2){
//    		int category = UtilityCalculateBitLength.calculate(lstRunLengthEncodedZickZack[i+1]);
//    		currentRunLengthPair = new Integer[]{lstRunLengthEncodedZickZack[i], category};
//			if(collectionSymbolOfACTree.containsKey(currentRunLengthPair) == false){
//				throw new ExceptionInvalidParameter("der delta " + currentRunLengthPair + " ist nicht in der symboltabelle");
//			}
//			Integer[] key = new Integer[]{currentRunLengthPair[0],currentRunLengthPair[1]};
//			output.append(collectionSymbolOfACTree.get(key));
//			output.append(ConverterToBit.convert(lstRunLengthEncodedZickZack[i+1]));
//    	}
//		return output;
//    }
    
    public List<Array2DRowRealMatrix> createQuantizationTable(List<Array2DRowRealMatrix> dct8x8Matrix, int[][] quantizationTable){

        double[][] jpegStdChrominance = ConverterToDouble.convert(quantizationTable);
		Array2DRowRealMatrix quantizationMatrix = new Array2DRowRealMatrix(jpegStdChrominance);
        
		return ConverterMatrixByQuantizationTable.convert(quantizationMatrix, dct8x8Matrix);
    }


    public CollectionSymbol createHuffmanTree() {
        EncoderHuffmanTree encoder = new EncoderHuffmanTree();
        ArrayList<Object> liste = new ArrayList<>();
        
        for (Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()) {
            if(entry.getValue() instanceof YCbCr) {
                YCbCr pixel = (YCbCr)entry.getValue();
                liste.add(pixel.getY());
            }
        }
        
        encoder.encode(liste);       
        
        Tree lengthLimitedTree = ConverterHuffmanTreeLengthLimited.convert(encoder.getTree(), 11, 15);
        
        CollectionSymbol symbol = ConverterHuffmanTreeToCollectionSymbol.convert(lengthLimitedTree);
        symbol = symbol.sort();
        
        Tree tree = new FormatterRightGrowingTree().format(symbol);
        return ConverterHuffmanTreeToCollectionSymbol.convert(tree);
        
    }

    public String getColormodel() {
        String colormodel = pixel.get(new Coordinate(0, 0)).getClass().getName();
        return colormodel.substring(colormodel.lastIndexOf(".") + 1, colormodel.length());
    }

	public ModelEncoder getModelEncoder() {
		return modelEncoder;
	}

	public void setModelEncoder(ModelEncoder modelEncoder) {
		this.modelEncoder = modelEncoder;
	}
}
