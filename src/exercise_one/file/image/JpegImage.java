package exercise_one.file.image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exercise_one.converter.ConverterRGBToYCbCr;
import exercise_one.converter.ConverterStringToInteger;
import exercise_one.converter.ConverterToByte;
import exercise_one.exception.image.ImageException;
import exercise_one.exception.image.UnsupportedImageFormatException;
import exercise_one.file.jpeg.segment.APP0;
import exercise_one.file.jpeg.segment.EOI;
import exercise_one.file.jpeg.segment.SOF0;
import exercise_one.file.jpeg.segment.SOI;
import exercise_one.file.stream.SimpleBitOutputStream;
import exercise_one.model.color.RGB;
import exercise_one.model.matrix.Coordinate;

public class JpegImage extends Image implements Cloneable {

    private static final int ASCII_LINE_FEED = 10;

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public JpegImage() {
        width = 0;
        height = 0;
        maxColorValue = 0;
    }

    public JpegImage(String src, int schrittweite, int fillmode) throws UnsupportedImageFormatException, ImageException, IOException {
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

    public void writeToFile(SimpleBitOutputStream out) throws IOException {
        new SOI().write(out);
        APP0 app0 = new APP0();
        app0.write(out);
        SOF0 sof0 = new SOF0();
        sof0.setHeight(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(height,2));
        sof0.setWidth(ConverterToByte.convertPositiveIntToByteWithExactByteNumber(width,2));
        sof0.write(out);
        new EOI().write(out);
        out.close();
    }

    public String getColormodel() {
        String colormodel = pixel.get(new Coordinate(0, 0)).getClass().getName();
        return colormodel.substring(colormodel.lastIndexOf(".") + 1, colormodel.length());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
