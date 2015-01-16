package main.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import main.exception.image.ImageException;
import main.exception.image.UnsupportedImageFormatException;
import main.file.image.JPEGImage;
import main.file.resource.UtilityResourcePath;
import main.file.stream.SimpleBitWriter;
import main.filter.FilterReductionByStep;
import main.logger.LoggerColormodel;
import main.logger.LoggerTimer;
import main.model.color.Colormodel;
import main.model.matrix.Coordinate;

public class TesterJpegEncoder {

    private static final int PARAMETER_INDEX_IMAGE_PATH = 0;
    static int fillMode;
    static int subsampleMode;
    static int subsampleRow;
    static int subsampleColumn;

    public static int subsample = 2;
    
    public static void main(String[] args) {
        try {
            //setParams();
            TesterJpegEncoder jpe = new TesterJpegEncoder();

            LoggerTimer timeLogger = new LoggerTimer();
            LoggerColormodel coordinateLogger = new LoggerColormodel();
            TreeMap<Coordinate, Colormodel> filteredPixel;

            timeLogger.start();

            JPEGImage image = new JPEGImage(UtilityResourcePath.getPath("test_112.ppm"), 16, fillMode);

            image.convertToYCbCr();
            timeLogger.stop();
           // coordinateLogger.log(image.getPixel(), image.getColormodel(), image.getWidth(), image.getHeight(), true);
            System.out.println("\n");
            timeLogger.start();

            image.setReducedPixel(image.filter(new FilterReductionByStep(2, 2)));

            timeLogger.stop();
            //coordinateLogger.log(image.getPixel(), image.getColormodel(), image.getWidth(), image.getHeight(), true);
            timeLogger.log();

            //FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\xSmorpheusSx\\Desktop\\image.jpeg");

            image.writeToFile(new SimpleBitWriter(new File("C:\\Users\\xSmorpheusSx\\Desktop\\image.jpeg")));

        }
        catch (UnsupportedImageFormatException | ImageException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void setParams() {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Geben Sie bitte den FILLMODE ein (1 = white, 2 = black, 3 = border): ");
            String zeile = null;
            do {
                zeile = console.readLine();
                switch (zeile) {
                    case "1":
                        fillMode = 255;
                        break;
                    case "2":
                        fillMode = 0;
                        break;
                    case "3":
                        fillMode = 1;
                        break;
                    default:
                        System.out.print("Eingabe muss 1-3 sein (1 = white, 2 = black, 3 = border): ");
                }
            }
            while (!zeile.equals("1") && !zeile.equals("2") && !zeile.equals("3"));

            System.out.print("Geben Sie bitte den Subsample-Modus ein (1 = einfache Unterabtastung, 2 = Mittelwert): ");
            zeile = console.readLine();
            do {
                switch (zeile) {
                    case "1":
                        subsampleMode = 1;
                        break;
                    case "2":
                        subsampleMode = 2;
                        break;
                    default:
                        System.out.print("Eingabe muss 1-1 sein (1 = einfache Unterabtastung, 2 = Mittelwert): ");
                }
            }
            while (!zeile.equals("1") && !zeile.equals("2"));

            System.out.print("Geben Sie bitte die Zeilen für Subsampling ein: ");
            zeile = console.readLine();
            do {
                if (isInteger(zeile) == true) {
                    subsampleColumn = Integer.parseInt(zeile);
                }
                else {
                    System.out.print("Eingabe eine ganze Zahl sein: ");
                }
            }
            while (!isInteger(zeile));

            System.out.print("Geben Sie bitte die Spalten für Subsampling ein: ");
            zeile = console.readLine();
            do {
                if (isInteger(zeile) == true) {
                    subsampleColumn = Integer.parseInt(zeile);
                }
                else {
                    System.out.print("Eingabe eine ganze Zahl sein: ");
                }
            }
            while (!isInteger(zeile));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
