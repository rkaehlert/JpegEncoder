package main.converter;

import java.util.LinkedList;
import java.util.List;

public class ConverterRunLengthEncoding {

	public static List<Integer[]> convert(List<Integer[]> lstZickZackSequenceY) {
		List<Integer[]> output = new LinkedList<Integer[]>();
		for(Integer[] current8x8Block : lstZickZackSequenceY){
			int counter = 0;
			List<Integer> runLengthEncodedBlock = new LinkedList<Integer>();
			for(int index = 1; index < current8x8Block.length; index++){
				if(current8x8Block[index] == 0){
					if(counter == 15){
						runLengthEncodedBlock.add(counter);
						runLengthEncodedBlock.add(current8x8Block[index]);
						counter = 0;
					}else{
						counter++;
					}
				}else{
					runLengthEncodedBlock.add(counter);
					runLengthEncodedBlock.add(current8x8Block[index]);
					counter = 0;
				}
			}
			if(counter != 0){
				runLengthEncodedBlock.add(0);
				runLengthEncodedBlock.add(0);
			}
			output.add(runLengthEncodedBlock.toArray(new Integer[runLengthEncodedBlock.size()]));
		}
		return output;
	}

}
