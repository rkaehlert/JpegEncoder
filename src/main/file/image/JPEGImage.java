package main.file.image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.calculator.CalculatorDelta;
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
import main.file.jpeg.segment.enums.EnumComponentId;
import main.file.jpeg.segment.enums.EnumDestinationIdentifier;
import main.file.jpeg.segment.enums.EnumHTType;
import main.file.jpeg.segment.enums.EnumSubSampling;
import main.file.stream.SimpleBitWriter;
import main.filter.FilterMatrixByFirstElementOf8x8Block;
import main.formatter.FormatterRightGrowingTree;
import main.formatter.FormatterRunLengthEncodingByCategory;
import main.logger.LoggerMap;
import main.model.color.ColorChannel;
import main.model.color.Colormodel;
import main.model.color.RGB;
import main.model.color.YCbCr;
import main.model.encoder.ModelEncoder;
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
    	   	
    	this.writeData(out);
    	
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

       	//CollectionSymbol collectionSymbol = this.createHuffmanTree();

        dht.addHT(EnumComponentId.Y, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(0));
        dht.addHT(EnumComponentId.Y, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(1));
        dht.addHT(EnumComponentId.Cb, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(2));
//        dht.addHT(EnumComponentId.Cb, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(4));
//        
//        dht.addHT(EnumComponentId.Cr, EnumHTType.DC, this.modelEncoder.getLstHuffmanSymbol().get(3));
//        dht.addHT(EnumComponentId.Cr, EnumHTType.AC, this.modelEncoder.getLstHuffmanSymbol().get(5));
        dht.write(out);
//
//    	SOS sos = new SOS();
//        sos.addComponent(EnumComponentId.Y, EnumHTType.AC, EnumHTType.DC);
//
//        sos.write(out);
//        
        new EOI().write(out);
        out.close();

    }
    
    private void writeData(SimpleBitWriter out) {
       	
    	EncoderHuffmanTree encoder = new EncoderHuffmanTree();
    	Map<ColorChannel<Integer>, List<Array2DRowRealMatrix>> collectionDCT = new HashMap<ColorChannel<Integer>, List<Array2DRowRealMatrix>>(); 
    	
    	Array2DRowRealMatrix pixelYChannel = ConverterYCbCrToMatrixByColorchannel.convertY(this.pixel);
    	Array2DRowRealMatrix pixelCbChannel = ConverterYCbCrToMatrixByColorchannel.convertCb(this.pixel);
    	Array2DRowRealMatrix pixelCrChannel = ConverterYCbCrToMatrixByColorchannel.convertCr(this.pixel);
    	
    	List<Array2DRowRealMatrix> dctYChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelYChannel);
    	List<Array2DRowRealMatrix> dctCbChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCbChannel);
    	List<Array2DRowRealMatrix> dctCrChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCrChannel);
    	
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
    	
    	List<Integer> lstDeltaDCCoefficientY = CalculatorDelta.calculate(lstDCCoefficientY);
    	List<Integer> lstDeltaDCCoefficientCb = CalculatorDelta.calculate(lstDCCoefficientCb);
    	List<Integer> lstDeltaDCCoefficientCr = CalculatorDelta.calculate(lstDCCoefficientCr);
    	
    	if(lstDeltaDCCoefficientY.size() != lstDCCoefficientY.size()){
    		throw new ExceptionInvalidParameter("die groesse der delta liste der dc koeffizienten muss gleich der groesse der dc koeffizienten sein");
    	}
    	
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoefficientY));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeY = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoefficientCb));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCb = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoefficientCr));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCr = encoder.getPathCollection();
    
    	//ermitteln aller AC Koeffizienten
    	
    	List<Integer[]> lstRunLengthEncodedZickZackY = ConverterRunLengthEncoding.convert(lstZickZackSequenceY);
    	List<Integer[]> lstRunLengthEncodedZickZackCb = ConverterRunLengthEncoding.convert(lstZickZackSequenceCb);
    	List<Integer[]> lstRunLengthEncodedZickZackCr = ConverterRunLengthEncoding.convert(lstZickZackSequenceCr);
    	
    	List<Integer[]> rleHuffmanCodesY = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackY);
    	List<Integer[]> rleHuffmanCodesCb = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackCb);
    	List<Integer[]> rleHuffmanCodesCr = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackCr);
    	
    	//erstellen der huffman baeume zu den ac koeffizienten
    	
    	if(lstRunLengthEncodedZickZackY.size() != lstDCCoefficientY.size()){
    		throw new ExceptionInvalidParameter("die groesse der dc und ac koeffizienten sind nicht gleich");
    	}
    	
    	encoder.encodeJPEG(rleHuffmanCodesY);
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfACTreeY = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encodeJPEG(rleHuffmanCodesCb);
    	CollectionSymbol collectionSymbolOfACTreeCb = encoder.getPathCollection();
    	encoder.getTree();
    	encoder = new EncoderHuffmanTree();
    	encoder.encodeJPEG(rleHuffmanCodesCr);
    	CollectionSymbol collectionSymbolOfACTreeCr = encoder.getPathCollection();
    	encoder.getTree();
  
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfDC(collectionSymbolOfDCTreeY, lstDeltaDCCoefficientY));
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfAC(collectionSymbolOfACTreeY, lstRunLengthEncodedZickZackY));
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfDC(collectionSymbolOfDCTreeCb, lstDeltaDCCoefficientCb));
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfAC(collectionSymbolOfACTreeCb, lstRunLengthEncodedZickZackCb));
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfDC(collectionSymbolOfDCTreeCr, lstDeltaDCCoefficientCr));
    	this.modelEncoder.getLstHuffmanSymbol().add(this.calculateBitsOfAC(collectionSymbolOfACTreeCr, lstRunLengthEncodedZickZackCr));
    	
//    	StringBuffer bits = new StringBuffer();
//    	
//    	//bauen des ausgabe array fuer jeden block
//    	for(int i = 0; i < lstDeltaDCCoefficientY.size(); i++){
//    		bits.append(this.calculateBitsOfDCBlock(collectionSymbolOfDCTreeY, lstDeltaDCCoefficientY.get(i)));
//    		bits.append(this.calculateBitsOfACBlock(collectionSymbolOfACTreeY, lstRunLengthEncodedZickZackY.get(i)));
//    		if(i != 0 && (i%4) == 0){
//    			bits.append(this.calculateBitsOfDCBlock(collectionSymbolOfDCTreeCb, lstDeltaDCCoefficientCb.get(i)));
//    			bits.append(this.calculateBitsOfACBlock(collectionSymbolOfACTreeCb, lstRunLengthEncodedZickZackCb.get(i)));
//    			bits.append(this.calculateBitsOfDCBlock(collectionSymbolOfDCTreeCr, lstDeltaDCCoefficientCr.get(i)));
//    			bits.append(this.calculateBitsOfACBlock(collectionSymbolOfACTreeCr, lstRunLengthEncodedZickZackCr.get(i)));
//    		}
//    	}
//    	
//    	try {
//			out.writeBit(bits.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

        LoggerMap<Tree,String> loggerMap = new LoggerMap<Tree,String>();
        loggerMap.log(ConverterHuffmanTreeToCollectionSymbol.convert(encoder.getTree()));
        
        
        Tree lengthLimitedTree = ConverterHuffmanTreeLengthLimited.convert(encoder.getTree(), 11, 15);

        loggerMap = new LoggerMap<Tree,String>();
        loggerMap.log(ConverterHuffmanTreeToCollectionSymbol.convert(lengthLimitedTree));
        
        CollectionSymbol symbol = ConverterHuffmanTreeToCollectionSymbol.convert(lengthLimitedTree);
        symbol = symbol.sort();
        
        Tree tree = new FormatterRightGrowingTree().format(symbol);
        loggerMap.log(ConverterHuffmanTreeToCollectionSymbol.convert(tree));
        return ConverterHuffmanTreeToCollectionSymbol.convert(tree);
        
    }

    public String getColormodel() {
        String colormodel = pixel.get(new Coordinate(0, 0)).getClass().getName();
        return colormodel.substring(colormodel.lastIndexOf(".") + 1, colormodel.length());
    }
}
