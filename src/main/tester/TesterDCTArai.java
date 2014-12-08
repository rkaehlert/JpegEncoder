package main.tester;
import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.converter.ConverterInverseCosinusTransformation;
import main.logger.LoggerTimer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCTArai {
    public static void main(String[] args) throws InterruptedException {
        double[][] imagePart = new double[8][8];
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
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(imagePart);
        erg = converter2.convert(matrix,8,8).getData();
        logger.stop();
        logger.log();
        
        logger.start();
        Array2DRowRealMatrix output = new ConverterInverseCosinusTransformation().convert(new Array2DRowRealMatrix(erg),8,8);
        logger.stop();
        logger.log();
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print(erg[i][j]+" ");
            }
            System.out.println();
        }
        
        System.out.println(ComparatorMatrixEquals.compare(matrix, output, -0.1));
    }
}
