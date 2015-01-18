package main.file.image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.FormatterEscapeByByte;
import main.calculator.CalculatorCategoryByDelta;
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
import main.logger.LoggerMatrix;
import main.model.color.Colormodel;
import main.model.color.RGB;
import main.model.color.YCbCr;
import main.model.encoder.ModelAC;
import main.model.encoder.ModelBlock;
import main.model.encoder.ModelDC;
import main.model.encoder.ModelEncoder;
import main.model.encoder.ModelGroupedBlock;
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

        if(schrittweite != 0){
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
        
        double[][] jpegStdChrominance = ConverterToDouble.convert(JPEGQuantizationTable.DEFAULT_QT_CHROMINANCE);
  		Array2DRowRealMatrix quantizationMatrixChrominance = new Array2DRowRealMatrix(jpegStdChrominance);
  		
  		double[][] jpegStdLuminance = ConverterToDouble.convert(JPEGQuantizationTable.DEFAULT_QT_LUMINANCE);
  		Array2DRowRealMatrix quantizationMatrixLuminance = new Array2DRowRealMatrix(jpegStdLuminance);
        
        dqt.addQT(EnumDestinationIdentifier.Y, ConverterToByte.convert(new ConverterMatrixToZickZackSequence().convert(quantizationMatrixLuminance)));
        dqt.addQT(EnumDestinationIdentifier.CBCR, ConverterToByte.convert(new ConverterMatrixToZickZackSequence().convert(quantizationMatrixChrominance)));
        dqt.write(out);
        
        
        SOF0 sof0 = new SOF0();
        sof0.setHeight(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(height, 2));
        sof0.setWidth(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(width, 2));
        sof0.addComponent(EnumComponentId.Y, 0, EnumSubSampling.NONE);
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
        
    	SOS sos = new SOS();
        sos.addComponent(EnumComponentId.Y, 0, 0);
        sos.addComponent(EnumComponentId.Cb, 1, 1);
        sos.addComponent(EnumComponentId.Cr, 2, 2);
        sos.write(out);   
        
        StringBuffer output = new StringBuffer();
        for(ModelGroupedBlock modelGroupedBlock : this.modelEncoder.getLstModelGroupedBlock()){
            output.append(modelGroupedBlock.toString());
        }
        
        output = FormatterEscapeByByte.format("11111111", "1111111100000000", output);
        
        out.write(output.toString());
        new EOI().write(out);
        out.close();

    }
    
    public void writeData() {
    	LoggerMatrix logger = new LoggerMatrix();
    	
    	EncoderHuffmanTree encoder = new EncoderHuffmanTree();
    	
    	Array2DRowRealMatrix pixelYChannel = ConverterYCbCrToMatrixByColorchannel.convertY(this.pixel, this.width, this.height);
    	Array2DRowRealMatrix pixelCbChannel = ConverterYCbCrToMatrixByColorchannel.convertCb(this.pixel, this.width, this.height);
    	Array2DRowRealMatrix pixelCrChannel = ConverterYCbCrToMatrixByColorchannel.convertCr(this.pixel, this.width, this.height);
    	
    	List<Array2DRowRealMatrix> dctYChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelYChannel);
    	List<Array2DRowRealMatrix> dctCbChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCbChannel);
    	List<Array2DRowRealMatrix> dctCrChannel = new ConverterDiscreteCosinusTransformationArai().convert(pixelCrChannel);
    	
    	List<Array2DRowRealMatrix> quantizedYChannel = this.createQuantizationTable(dctYChannel, JPEGQuantizationTable.DEFAULT_QT_LUMINANCE);
    	List<Array2DRowRealMatrix> quantizedCbChannel = this.createQuantizationTable(dctCbChannel, JPEGQuantizationTable.DEFAULT_QT_CHROMINANCE);
    	List<Array2DRowRealMatrix> quantizedCrChannel = this.createQuantizationTable(dctCrChannel, JPEGQuantizationTable.DEFAULT_QT_CHROMINANCE);
    	
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
    	
    	List<Integer> lstDeltaDCCoeffizientYByCategory = CalculatorCategoryByDelta.calculate(lstDeltaDCCoefficientY);
    	List<Integer> lstDeltaDCCoeffizientCbByCategory = CalculatorCategoryByDelta.calculate(lstDeltaDCCoefficientCb);
    	List<Integer> lstDeltaDCCoeffizientCrByCategory = CalculatorCategoryByDelta.calculate(lstDeltaDCCoefficientCr);
    	
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoeffizientYByCategory));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeY = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoeffizientCbByCategory));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCb = encoder.getPathCollection();
    	encoder = new EncoderHuffmanTree();
    	encoder.encode(new LinkedList<Object>(lstDeltaDCCoeffizientCrByCategory));
    	encoder.getTree();
    	CollectionSymbol collectionSymbolOfDCTreeCr = encoder.getPathCollection();
    	
   
    	this.fillModelOfDC(collectionSymbolOfDCTreeY, collectionSymbolOfDCTreeCb, collectionSymbolOfDCTreeCr, lstDeltaDCCoefficientY, lstDeltaDCCoefficientCb, lstDeltaDCCoefficientCr);
    	
    	
    	//ermitteln aller AC Koeffizienten
    	
    	List<Integer[]> lstRunLengthEncodedZickZackY = ConverterRunLengthEncoding.convert(lstZickZackSequenceY);
    	List<Integer[]> lstRunLengthEncodedZickZackCb = ConverterRunLengthEncoding.convert(lstZickZackSequenceCb);
    	List<Integer[]> lstRunLengthEncodedZickZackCr = ConverterRunLengthEncoding.convert(lstZickZackSequenceCr);
    	
    	List<List<Integer[]>> rleZickZackByCategoryY = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackY);
    	List<List<Integer[]>> rleZickZackByCategoryCb = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackCb);
    	List<List<Integer[]>> rleZickZackByCategoryCr = FormatterRunLengthEncodingByCategory.format(lstRunLengthEncodedZickZackCr);
    	
    	//erstellen der huffman baeume zu den ac koeffizienten
    	
    	if(lstRunLengthEncodedZickZackY.size() != lstDCCoefficientY.size()){
    		throw new ExceptionInvalidParameter("die groesse der dc und ac koeffizienten sind nicht gleich");
    	}
    	
    	List<Integer[]> combinedRleHuffmanCodesY = new LinkedList<Integer[]>();
    	List<Integer[]> combinedRleHuffmanCodesCb = new LinkedList<Integer[]>();
    	List<Integer[]> combinedRleHuffmanCodesCr = new LinkedList<Integer[]>();
    	
    	for(List<Integer[]> temp : rleZickZackByCategoryY ){
    		combinedRleHuffmanCodesY.addAll(temp);
    	}
    	for(List<Integer[]> temp : rleZickZackByCategoryCb ){
    		combinedRleHuffmanCodesCb.addAll(temp);
    	}
    	for(List<Integer[]> temp : rleZickZackByCategoryCr ){
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
    	
    	this.fillModelOfAC(collectionSymbolOfACTreeY, collectionSymbolOfACTreeCb, collectionSymbolOfACTreeCr, lstRunLengthEncodedZickZackY, lstRunLengthEncodedZickZackCb, lstRunLengthEncodedZickZackCr);
  
    	this.modelEncoder.getLstHuffmanSymbol().add(0,collectionSymbolOfDCTreeY);
    	this.modelEncoder.getLstHuffmanSymbol().add(1,collectionSymbolOfACTreeY);
    	this.modelEncoder.getLstHuffmanSymbol().add(2,collectionSymbolOfDCTreeCb);
    	this.modelEncoder.getLstHuffmanSymbol().add(3,collectionSymbolOfACTreeCb);
    	this.modelEncoder.getLstHuffmanSymbol().add(4,collectionSymbolOfDCTreeCr);
    	this.modelEncoder.getLstHuffmanSymbol().add(5,collectionSymbolOfACTreeCr);
    
    }
    
	private void fillModelOfAC(CollectionSymbol collectionSymbolOfACTreeY,
			CollectionSymbol collectionSymbolOfACTreeCb,
			CollectionSymbol collectionSymbolOfACTreeCr,
			List<Integer[]> lstRunLengthEncodedZickZackY,
			List<Integer[]> lstRunLengthEncodedZickZackCb,
			List<Integer[]> lstRunLengthEncodedZickZackCr) {
		
//		System.out.println("test");
//		new LoggerMap<Tree, String>().log(collectionSymbolOfACTreeY);
//		new LoggerMap<Tree, String>().log(collectionSymbolOfACTreeCb);
//		new LoggerMap<Tree, String>().log(collectionSymbolOfACTreeCr);
//		System.out.println("test");
		
		for(int rleIndex = 0; rleIndex < lstRunLengthEncodedZickZackY.size(); rleIndex++){
			Integer[] key = new Integer[2];
			ModelGroupedBlock modelGroupedBlock = this.modelEncoder.getLstModelGroupedBlock().get(rleIndex/4);
			if(rleIndex == 0 || rleIndex % 4 == 0){
				Integer[] currentRLEBlockCb = lstRunLengthEncodedZickZackCb.get(rleIndex / 4);
				Integer[] currentRLEBlockCr = lstRunLengthEncodedZickZackCr.get(rleIndex / 4);				
				for(int blockIndexCb = 0; blockIndexCb < currentRLEBlockCb.length; blockIndexCb+=2){	
					ModelAC modelAC = new ModelAC();
					key[0] = currentRLEBlockCb[blockIndexCb];
					key[1] = UtilityCalculateBitLength.calculate(currentRLEBlockCb[blockIndexCb+1]);
					modelAC.setKey(key);
					modelAC.setCode(collectionSymbolOfACTreeCb.get(key));
					modelAC.setValue(currentRLEBlockCb[blockIndexCb+1]);
					modelGroupedBlock.getModelCb().getAc().add(modelAC);
				}
				for(int blockIndexCr = 0; blockIndexCr < currentRLEBlockCr.length; blockIndexCr+=2){
					ModelAC modelAC = new ModelAC();
					key[0] = currentRLEBlockCr[blockIndexCr];
					key[1] = UtilityCalculateBitLength.calculate(currentRLEBlockCr[blockIndexCr+1]);
					modelAC.setKey(key);
					modelAC.setCode(collectionSymbolOfACTreeCr.get(key));
					modelAC.setValue(currentRLEBlockCr[blockIndexCr+1]);
					modelGroupedBlock.getModelCr().getAc().add(modelAC);
				}
			}
			Integer[] currentRLEBlockY = lstRunLengthEncodedZickZackY.get(rleIndex);
			for(int blockIndexY = 0; blockIndexY < currentRLEBlockY.length; blockIndexY+=2){
				ModelAC modelAC = new ModelAC();
				key[0] = currentRLEBlockY[blockIndexY];
				key[1] = UtilityCalculateBitLength.calculate(currentRLEBlockY[blockIndexY+1]);
				modelAC.setKey(key);
				modelAC.setCode(collectionSymbolOfACTreeY.get(key));
				modelAC.setValue(currentRLEBlockY[blockIndexY+1]);
				modelGroupedBlock.getLstModelY()[rleIndex%4].getAc().add(modelAC);
			}
		}
	}
	
	private void fillModelOfDC(CollectionSymbol collectionSymbolOfDCTreeY,
			CollectionSymbol collectionSymbolOfDCTreeCb,
			CollectionSymbol collectionSymbolOfDCTreeCr,
			List<Integer> lstDeltaDCCoefficientY,
			List<Integer> lstDeltaDCCoefficientCb,
			List<Integer> lstDeltaDCCoefficientCr) {
		
		ModelGroupedBlock groupedBlock = null;
		
		for(int index = 0; index < lstDeltaDCCoefficientY.size(); index++){
			if(index == 0 || index % 4 == 0){
				ModelGroupedBlock modelGroupedBlock = new ModelGroupedBlock();
				ModelDC modelCbDC = new ModelDC(lstDeltaDCCoefficientCb.get(index / 4));
				modelCbDC.setCode(collectionSymbolOfDCTreeCb.get(modelCbDC.calculateCategory()));
				ModelDC modelCrDC = new ModelDC(lstDeltaDCCoefficientCr.get(index / 4));
				modelCrDC.setCode(collectionSymbolOfDCTreeCr.get(modelCrDC.calculateCategory()));
				ModelBlock modelBlockCb = new ModelBlock(modelCbDC);
				ModelBlock modelBlockCr = new ModelBlock(modelCrDC);
				modelGroupedBlock.setModelCb(modelBlockCb);
				modelGroupedBlock.setModelCr(modelBlockCr);
				this.modelEncoder.getLstModelGroupedBlock().add(modelGroupedBlock);
				groupedBlock = modelGroupedBlock;
			}
			ModelDC modelDC = new ModelDC();
			modelDC.setDelta(lstDeltaDCCoefficientY.get(index));
			modelDC.setCode(collectionSymbolOfDCTreeY.get(modelDC.calculateCategory()));
			ModelBlock modelBlockY = new ModelBlock(modelDC);
			groupedBlock.getLstModelY()[index % 4] = modelBlockY;
		}
		
	}
    
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
        
        Tree lengthLimitedTree = ConverterHuffmanTreeLengthLimited.convert(encoder.getTree(), 11, ConverterHuffmanTreeLengthLimited.LIMIT);
        
        CollectionSymbol symbol = ConverterHuffmanTreeToCollectionSymbol.convert(lengthLimitedTree);
        symbol = symbol.sort();
        
        Tree tree = new FormatterRightGrowingTree().format(symbol);
        return ConverterHuffmanTreeToCollectionSymbol.convert(tree);
        
    }

    public String getColormodel() {
        String colormodel = pixel.get(new Coordinate(0, 0)).getClass().getName();
        return colormodel.substring(colormodel.lastIndexOf(".") + 1, colormodel.length());
    }

}
