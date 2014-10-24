package exercise_one.file.stream;

import java.io.File;
import java.io.IOException;

import exercise_one.file.stream.bit.BitStreamWriter;

public class InputStreamTester {

	public static void main(String[] args){
		try {
			File file = new File("C:\\Users\\xSmorpheusSx\\Desktop\\test");
			BitStreamWriter stream = new BitStreamWriter(file);
			int offset = 0;
/*			for(int index = 0; index < 200000000; index++){
				if((48+offset) > 57){
					offset = 0;
				}
				stream.append(BigInteger.valueOf(48+offset));
				offset++;
			}*/
			stream.flush();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
