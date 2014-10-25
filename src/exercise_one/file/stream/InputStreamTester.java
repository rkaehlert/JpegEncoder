package exercise_one.file.stream;

import java.io.File;
import java.io.IOException;

import exercise_one.converter.ConverterToByte;
import exercise_one.file.stream.bit.BitStreamReader;
import exercise_one.file.stream.bit.BitStreamWriter;
import exercise_one.file.stream.bit.BufferedInputStream;
import exercise_one.file.stream.bit.BufferedOutputStream;

public class InputStreamTester {

	public static void main(String[] args){
		try {
			File file = new File("C:\\Users\\xSmorpheusSx\\Desktop\\1257989_1_DSC_0237-2.jpg");
			
			BitStreamReader reader = new BitStreamReader(file);
			BufferedInputStream inputStream = new BufferedInputStream(reader);
			inputStream.read();
			ConverterToByte.convert(inputStream.getBuffer());
			reader.close();
			
			file = new File("C:\\Users\\xSmorpheusSx\\Desktop\\test");
			
			BitStreamWriter writer = new BitStreamWriter(file);
			BufferedOutputStream stream = new BufferedOutputStream(writer);
			stream.setBuffer(inputStream.getBuffer());
			stream.write();
			stream.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
