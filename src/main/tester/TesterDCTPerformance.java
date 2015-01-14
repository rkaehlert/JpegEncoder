package main.tester;

import java.util.ArrayList;
import java.util.List;

import main.comparator.ComparatorMatrixEquals;
import main.converter.ConverterDiscreteCosinusTransformation;
import main.converter.ConverterDiscreteCosinusTransformationArai;
import main.converter.ConverterSeparateCosinusTransformation;
import main.logger.LoggerMatrix;
import main.logger.LoggerTimer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class TesterDCTPerformance {

    public static final int SIZE = 256;
    public static final long BENCHMARK_TIME = 10000;
    
    public static void main(String[] args) {
        double[][] m = new double[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                m[x][y] = (x + y * 8) % 256;
            }
        }

        List<Array2DRowRealMatrix> transformedDCT = new ArrayList<Array2DRowRealMatrix>();
        List<Array2DRowRealMatrix> transformedSeparate = new ArrayList<Array2DRowRealMatrix>();
        List<Array2DRowRealMatrix> transformedArai = new ArrayList<Array2DRowRealMatrix>();

        LoggerTimer timer = new LoggerTimer();
        Long end = System.currentTimeMillis() + BENCHMARK_TIME;
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(m);
        ConverterDiscreteCosinusTransformation dct = new ConverterDiscreteCosinusTransformation();
        while (System.currentTimeMillis() < end) {
            timer.start();
            transformedDCT = dct.convert(matrix);
            timer.stop();
       }
        timer.log();
        timer.reset();
        ConverterSeparateCosinusTransformation dctSeparate = new ConverterSeparateCosinusTransformation();
        end = System.currentTimeMillis() + BENCHMARK_TIME;
        while (System.currentTimeMillis() < end) {
            timer.start();
            transformedSeparate = dctSeparate.convert(matrix);
            timer.stop();
        }
        timer.log();
        timer.reset();
        ConverterDiscreteCosinusTransformationArai dctArai = new ConverterDiscreteCosinusTransformationArai();
        end = System.currentTimeMillis() + BENCHMARK_TIME;
        while (System.currentTimeMillis() < end) {
            timer.start();
            transformedArai = dctArai.convert(matrix,"Y");
            timer.stop();
        }
        timer.log();

        boolean equal = true;
        equal = ComparatorMatrixEquals.compare(transformedDCT, transformedSeparate, 0.01);
        equal = ComparatorMatrixEquals.compare(transformedDCT, transformedArai, 0.01);
        equal = ComparatorMatrixEquals.compare(transformedArai, transformedSeparate, 0.01);
        
        if(equal == true) System.out.println("Alle Konvertierungen gleich.");
        else System.out.println("Nicht Konvertierungen gleich.");
    }

}
