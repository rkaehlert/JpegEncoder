package main.converter;

import main.exception.common.ExceptionInvalidParameter;


public class ConverterDiscreteCosinusTransformation implements Converter{

	private static final int BLOCK_SIZE = 8;
	
	public double[][] convert(int[][] image, int rows, int cols){

		if (rows%BLOCK_SIZE!=0 || cols%BLOCK_SIZE!=0) {
	      throw new ExceptionInvalidParameter("zeilen und spalten sollten vielfache von 8 sein");
	    }
		if(image.length != rows){
			throw new ExceptionInvalidParameter("die uebergebene laenge entspricht nicht der groesse des bildes");
		}
		
	    double[][] in = new double[rows][cols];
	    double[][] dct = new double[rows][cols];
	    
	    int x, y;
	    double sum, au, av;
	    double n1 = Math.sqrt(1.0/BLOCK_SIZE);
	    double n2 = Math.sqrt(2.0/BLOCK_SIZE);
	    
	    // For each NxN block[m,n]
	    for (int m=0; m<rows; m+=BLOCK_SIZE) {
	      for (int n=0; n<cols; n+=BLOCK_SIZE) {

	        // For each pixel[u,v] in block[m,n]
	        for (int u=m; u<m+BLOCK_SIZE; u++) {
	          au = (u==m)? n1: n2;
	          for (int v=n; v<n+BLOCK_SIZE; v++) {
	            av = (v==n)? n1: n2;

	            // Sum up all pixels in the block
	            for (x=m, sum=0; x<m+BLOCK_SIZE; x++) {
	              for (y=n; y<n+BLOCK_SIZE; y++) {
	                in[x][y] = image[x][y] - 128.0;  // Subtract by 128
	                sum += in[x][y] * Math.cos((2*(x-m)+1)*(u-m)*Math.PI/(2*BLOCK_SIZE)) *
	                                  Math.cos((2*(y-n)+1)*(v-n)*Math.PI/(2*BLOCK_SIZE));
	              }
	            }
		    dct[u][v] = au * av * sum;

	          } // for v
	        } // for u

	      }  // for n
	    }  // for m
//
//	    ArrayIO.writeDoubleArray(args[3], dct, rows, cols);
		
		return dct;
	}
	
}
