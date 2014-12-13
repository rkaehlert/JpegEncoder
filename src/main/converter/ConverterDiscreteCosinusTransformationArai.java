package main.converter;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ConverterDiscreteCosinusTransformationArai implements Converter {

    public List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix) {
        List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
        for (Array2DRowRealMatrix currentMatrix : ConverterImageTo8x8Block.convert(matrix)) {
            Array2DRowRealMatrix matrix8x8 = this.convert8x8(currentMatrix);
            output.add(matrix8x8);
        }
        return output;
    }

    public Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix image) {
        Array2DRowRealMatrix output = new Array2DRowRealMatrix(8, 8);

        for (int i = 0; i <= 7; i++) {
            output.setRow(i, this.calculateLine(image.getRow(i)));
        }

        for (int i = 0; i <= 7; i++) {
            output.setColumn(i, this.calculateLine(output.getColumn(i)));
        }

        return output;
    }

    private double[] calculateLine(double[] line) {
        double[] y = new double[8];

        double x0 = line[0];
        double x1 = line[1];
        double x2 = line[2];
        double x3 = line[3];
        double x4 = line[4];
        double x5 = line[5];
        double x6 = line[6];
        double x7 = line[7];

        double C1 = Math.cos((1.0 * Math.PI / 16.0));
        double C2 = Math.cos((2.0 * Math.PI / 16.0));
        double C3 = Math.cos((3.0 * Math.PI / 16.0));
        double C4 = Math.cos((4.0 * Math.PI / 16.0));
        double C5 = Math.cos((5.0 * Math.PI / 16.0));
        double C6 = Math.cos((6.0 * Math.PI / 16.0));
        double C7 = Math.cos((7.0 * Math.PI / 16.0));

        double s0 = 1.0 / (2.0 * Math.sqrt(2.0));
        double s1 = 1.0 / (4.0 * C1);
        double s2 = 1.0 / (4.0 * C2);
        double s3 = 1.0 / (4.0 * C3);
        double s4 = 1.0 / (4.0 * C4);
        double s5 = 1.0 / (4.0 * C5);
        double s6 = 1.0 / (4.0 * C6);
        double s7 = 1.0 / (4.0 * C7);

        double a1 = C4;
        double a2 = C2 - C6;
        double a3 = C4;
        double a4 = C6 + C2;
        double a5 = C6;

        double x01 = x0 + x7;
        double x11 = x1 + x6;
        double x21 = x2 + x5;
        double x31 = x3 + x4;
        double x41 = x3 - x4;
        double x51 = x2 - x5;
        double x61 = x1 - x6;
        double x71 = x0 - x7;

        double x02 = x01 + x31;
        double x12 = x11 + x21;
        double x22 = x11 - x21;
        double x32 = x01 - x31;
        double x42 = -x41 - x51;
        double x52 = x51 + x61;
        double x62 = x61 + x71;
        double x72 = x71;

        double x03 = x02 + x12;
        double x13 = x02 - x12;
        double x23 = x22 + x32;
        double x33 = x32;
        double x43 = x42;
        double x53 = x52;
        double x63 = x62;
        double x73 = x72;

        double x04 = x03;
        double x14 = x13;
        double x24 = x23 * a1;
        double x34 = x33;
        double x44 = -(x43 * a2) - (x43 + x63) * a5;
        double x54 = x53 * a3;
        double x64 = (x63 * a4) - (x43 + x63) * a5;
        double x74 = x73;

        double x05 = x04;
        double x15 = x14;
        double x25 = x24 + x34;
        double x35 = x34 - x24;
        double x45 = x44;
        double x55 = x54 + x74;
        double x65 = x64;
        double x75 = x74 - x54;

        double x06 = x05;
        double x16 = x15;
        double x26 = x25;
        double x36 = x35;
        double x46 = x45 + x75;
        double x56 = x55 + x65;
        double x66 = x55 - x65;
        double x76 = x75 - x45;

        y[0] = x06 * s0;
        y[4] = x16 * s4;
        y[2] = x26 * s2;
        y[6] = x36 * s6;
        y[5] = x46 * s5;
        y[1] = x56 * s1;
        y[7] = x66 * s7;
        y[3] = x76 * s3;

        return y;
    }
}
