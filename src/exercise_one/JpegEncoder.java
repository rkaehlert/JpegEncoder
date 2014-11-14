package exercise_one;

import java.io.IOException;
import java.util.TreeMap;

import exercise_one.exception.image.ImageException;
import exercise_one.exception.image.UnsupportedImageFormatException;
import exercise_one.file.image.JpegImage;
import exercise_one.file.stream.SimpleBitOutputStream;
import exercise_one.filter.FilterReductionByMiddleValue;
import exercise_one.filter.FilterReductionByStep;
import exercise_one.logger.LoggerColormodel;
import exercise_one.logger.LoggerTimer;
import exercise_one.model.color.Colormodel;
import exercise_one.model.matrix.Coordinate;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class JpegEncoder {

    private static final int PARAMETER_INDEX_IMAGE_PATH = 0;
    static int fillMode;
    static int subsampleMode;
    static int subsampleRow;
    static int subsampleColumn;

    public static void main(String[] args) {
        try {
            //setParams();
            
            LoggerTimer timeLogger = new LoggerTimer();
            LoggerColormodel coordinateLogger = new LoggerColormodel();
            TreeMap<Coordinate, Colormodel> filteredPixel;

            timeLogger.start();
            JpegImage image = new JpegImage(args[PARAMETER_INDEX_IMAGE_PATH], 20, fillMode);
            image.convertToYCbCr();
            timeLogger.stop();
            coordinateLogger.log(image.getPixel(), image.getColormodel(), image.getWidth(), image.getHeight(), true);
            System.out.println("\n");
            timeLogger.start();
            image.filter(new FilterReductionByStep(3, 4));
            timeLogger.stop();
            coordinateLogger.log(image.getPixel(), image.getColormodel(), image.getWidth(), image.getHeight(), true);
            timeLogger.log();
            
            image.writeToFile(new SimpleBitOutputStream(new FileOutputStream("C:\\Users\\robin\\Desktop\\outputSingleByte.jpg")));
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
