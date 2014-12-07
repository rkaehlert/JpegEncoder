package main.tester;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.logger.LoggerTimer;

public class TesterDCTArai {
    public static void main(String[] args) throws InterruptedException {
        int[][] imagePart = new int[8][8];
        double[][] erg = new double[8][8];
        
        ConverterDiscreteCosinusTransformationArai converter = new ConverterDiscreteCosinusTransformationArai();
        ConverterDiscreteCosinusTransformation converter2 = new ConverterDiscreteCosinusTransformation();
        LoggerTimer logger = new LoggerTimer();
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                imagePart[i][j] = i*j;
            }
        }
        
        logger.start();
        imagePart = converter.convert(imagePart);
        logger.stop();
        logger.log();
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print(imagePart[i][j]+" ");
            }
            System.out.println();
        }
        
        logger.start();
        erg = converter2.convert(imagePart,8,8);
        logger.stop();
        logger.log();
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print(erg[i][j]+" ");
            }
            System.out.println();
        }
    }
}
