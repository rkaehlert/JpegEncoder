package main.tester;
import java.util.List;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.converter.ConverterImageTo8x8Block;
import main.logger.LoggerMatrix;
import main.logger.LoggerTimer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCTArai {
    public static void main(String[] args) throws InterruptedException {
       Array2DRowRealMatrix imagePart = new Array2DRowRealMatrix(8,8);
        double[][] erg = new double[8][8];
        
        ConverterDiscreteCosinusTransformationArai converter = new ConverterDiscreteCosinusTransformationArai();
        ConverterDiscreteCosinusTransformation converter2 = new ConverterDiscreteCosinusTransformation();
        LoggerTimer logger = new LoggerTimer();
        
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                imagePart.setEntry(i,j,i*j);
            }
        }
        
        logger.start();
        Array2DRowRealMatrix outputArai = converter.convert(imagePart);
        logger.stop();
        logger.log();
       
        LoggerMatrix.log(outputArai);
        
        logger.start();
        
       List<Array2DRowRealMatrix> output = new ConverterDiscreteCosinusTransformation().convert(imagePart);
        System.out.println("------------------------");
       LoggerMatrix.log(output);
        System.out.println(ComparatorMatrixEquals.compare(ConverterImageTo8x8Block.convert(outputArai), output, -0.1));
//        erg = converter2.convert(matrix,8,8).getData();
//        logger.stop();
//        logger.log();
//        
//        logger.start();
//        Array2DRowRealMatrix output = new ConverterInverseCosinusTransformation().convert(new Array2DRowRealMatrix(erg),8,8);
//        logger.stop();
//        logger.log();
//        
//        for (int i = 0; i <= 7; i++) {
//            for (int j = 0; j <= 7; j++) {
//                System.out.print(erg[i][j]+" ");
//            }
//            System.out.println();
//        }
        
    }
}
