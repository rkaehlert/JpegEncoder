package main.logger;

public class LoggerArray implements Logger {

	public static void log(int[][] input){
		for(int indexRow = 0; indexRow < input.length; indexRow++){
			for(int indexCol = 0; indexCol < input[indexRow].length; indexCol++){
				System.out.print(input[indexRow][indexCol] + " ");
			}
			System.out.println("\n");
		}
	}
	
	public static void log(double[][] input){
		for(int indexRow = 0; indexRow < input.length; indexRow++){
			for(int indexCol = 0; indexCol < input[indexRow].length; indexCol++){
				System.out.print(input[indexRow][indexCol] + " ");
			}
			System.out.println("\n");
		}
	}
	
	public static void log8x8(double[][] input){
		for(int indexRow = 0; indexRow < input.length; indexRow++){
			for(int indexCol = 0; indexCol < input[indexRow].length; indexCol++){
				if(indexCol % 8 == 0){
					System.out.println("\n");
				}
				System.out.print(input[indexRow][indexCol] + "\t");
			}
		}
	}
	
	public static void logLengthxLength(double[][] input){
		for(int indexRow = 0; indexRow < input.length; indexRow++){
			for(int indexCol = 0; indexCol < input[indexRow].length; indexCol++){
				System.out.print(input[indexRow][indexCol] + "\t");
			}
			System.out.println("\n");
		}
	}
}
