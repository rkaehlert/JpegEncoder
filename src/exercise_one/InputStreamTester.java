package exercise_one;

import java.io.File;
import java.io.IOException;

import exercise_one.file.stream.EnumBitMask;
import exercise_one.file.stream.bit.BufferedOutputStream;
import exercise_one.file.stream.bit.StreamWriter;
import exercise_one.logger.LoggerTimer;

public class InputStreamTester {

	public static void main(String[] args){
		File fileInput = new File("C:\\Users\\xSmorpheusSx\\Desktop\\IMG_20140817_132708.jpg");
		File fileOutputSingleFile = new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputFile");
		File fileOutputSingleBit = new File("C:\\Users\\xSmorpheusSx\\Desktop\\outputSingleByte");
		try (
                        //StreamReader reader = new StreamReader(fileInput);
			 StreamWriter writerSingleFile = new StreamWriter(fileOutputSingleFile);
			 StreamWriter writerSingleBit = new StreamWriter(fileOutputSingleBit)) {
			 //BufferedInputStream inputStream = new BufferedInputStream(reader);
			 BufferedOutputStream streamSingleFile = new BufferedOutputStream(writerSingleFile);
			 BufferedOutputStream streamSingleByte = new BufferedOutputStream(writerSingleBit);
			
			//reading a file
			//inputStream.read();
			//ConverterToByte.convert(inputStream.getBuffer());
			System.out.println("1");
			//writing a file
			//streamSingleFile.setBuffer(inputStream.getBuffer());
//			streamSingleFile.write();
//			streamSingleFile.flush();
			System.out.println("2");
			//writing a file
//			byte[] bytes = {97,98};
//			streamSingleByte.setBuffer(BitUtilityAppend.append(BigInteger.valueOf(0), bytes));
//			streamSingleByte.write();
//			streamSingleByte.flush();
			System.out.println("3");
			//writing a single bit
			
			LoggerTimer loggerTimer = new LoggerTimer();
			loggerTimer.start();
			
			for(int index = 0; index < 5000000; index++){
				streamSingleByte.append(EnumBitMask.ONE);
				streamSingleByte.append(EnumBitMask.ZERO);
//				if(index % 100000 == 0){
//					System.out.println(index);
//				}
			}				
			
			loggerTimer.stop();
			loggerTimer.log();
			
			streamSingleByte.write();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}