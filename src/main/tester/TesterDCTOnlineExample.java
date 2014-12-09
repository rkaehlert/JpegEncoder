package main.tester;

import main.logger.LoggerArray;
/*
 **************************************************************************
 *
 *   Forward Discrete Cosine Transform (FDCT)
 *
 **************************************************************************
 */

public class TesterDCTOnlineExample {

  public static void main(String[] args) {
    final int N = 8;  // Block size
    int     nrows, ncols, m, n, x, y, u, v, img[][];
    double  in[][], dct[][], sum, au, av;
    double  n1=Math.sqrt(1.0/N), n2=Math.sqrt(2.0/N);

//    if (args.length != 4) {
//      System.out.println("Usage: Dct <nrows> <ncols> <in_img> <dct_file>");
//      System.exit(0);
//    }
//    nrows = Integer.parseInt(args[0]);
//    ncols = Integer.parseInt(args[1]);
//    if (nrows%N!=0 || ncols%N!=0) {
//      System.out.println("Nrows and ncols should be multiples of 8");
//      System.exit(0);
//    }
    
    
    nrows = 8;
    ncols = 8;
    
    img = new int[][] {
				{154,123,123,123,123,123,123,136},
				{192,180,136,154,154,154,136,110},
				{254,198,154,154,180,154,123,123},
				{239,180,136,180,180,166,123,123},
				{180,154,136,167,166,149,136,136},
				{128,136,123,136,154,180,198,154},
				{123,105,110,149,136,136,180,166},
				{110,136,123,123,123,136,154,136}
    };
    
    in = new double[nrows][ncols];
    dct = new double[nrows][ncols];

    // For each NxN block[m,n]
    for (m=0; m<nrows; m+=N) {
      for (n=0; n<ncols; n+=N) {

        // For each pixel[u,v] in block[m,n]
        for (u=m; u<m+N; u++) {
          au = (u==m)? n1: n2;
          for (v=n; v<n+N; v++) {
            av = (v==n)? n1: n2;

            // Sum up all pixels in the block
            for (x=m, sum=0; x<m+N; x++) {
              for (y=n; y<n+N; y++) {
                in[x][y] = img[x][y] - 128.0;  // Subtract by 128
                sum += in[x][y] * Math.cos((2*(x-m)+1)*(u-m)*Math.PI/(2*N)) *
                                  Math.cos((2*(y-n)+1)*(v-n)*Math.PI/(2*N));
              }
            }
	    dct[u][v] = au * av * sum;

          } // for v
        } // for u

      }  // for n
    }  // for m

    LoggerArray.log(dct);
//    ArrayIO.writeDoubleArray(args[3], dct, nrows, ncols);
  }

}  // End class Dct
