package main.logger;

public class LoggerBit {

	public static void log(String value) {
		char[] code = value.toCharArray();
		for(int index = 0; index < value.length(); index++){
			if(index != 0 && (index % 8) == 0){
				System.out.print("\n");
			}
			System.out.print(code[index]);
		}
	}

}
