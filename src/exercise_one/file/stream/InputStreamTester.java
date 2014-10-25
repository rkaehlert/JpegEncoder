package exercise_one.file.stream;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import exercise_one.converter.ConverterToByte;
import exercise_one.file.stream.bit.StreamReader;
import exercise_one.file.stream.bit.StreamWriter;
import exercise_one.file.stream.bit.BufferedInputStream;
import exercise_one.file.stream.bit.BufferedOutputStream;
import exercise_one.model.stream.bit.utility.BitUtilityAppend;

public class InputStreamTester {

	public static void main(String[] args){
		File fileInput = new File("C:\\Users\\xSmorpheusSx\\Desktop\\1257989_1_DSC_0237-2.jpg");
		File fileOutputSingleFile = new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputFile");
		File fileOutputSingleBit = new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputSingleByte");
		try (StreamReader reader = new StreamReader(fileInput);
			 StreamWriter writerSingleFile = new StreamWriter(fileOutputSingleFile);
			 StreamWriter writerSingleBit = new StreamWriter(fileOutputSingleBit)) {
			 BufferedInputStream inputStream = new BufferedInputStream(reader);
			 BufferedOutputStream streamSingleFile = new BufferedOutputStream(writerSingleFile);
			 BufferedOutputStream streamSingleByte = new BufferedOutputStream(writerSingleBit);
			
			//reading a file
			inputStream.read();
			ConverterToByte.convert(inputStream.getBuffer());
			
			//writing a file
			streamSingleFile.setBuffer(inputStream.getBuffer());
			streamSingleFile.write();
			streamSingleFile.flush();
			
			//writing a file
			byte[] bytes = {97,98};
			streamSingleByte.setBuffer(BitUtilityAppend.append(BigInteger.valueOf(0), bytes));
			streamSingleByte.write();
			streamSingleByte.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
