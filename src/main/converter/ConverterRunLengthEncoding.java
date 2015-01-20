package main.converter;

import java.util.LinkedList;
import java.util.List;

public class ConverterRunLengthEncoding {

	public static List<Integer[]> convert(List<Integer[]> lstZickZackSequenceY) {
		LinkedList<Integer[]> output = new LinkedList<Integer[]>();
		for(Integer[] current8x8Block : lstZickZackSequenceY){
			int counter = 0;
			LinkedList<Integer> runLengthEncodedBlock = new LinkedList<Integer>();
			for(int index = 1; index < current8x8Block.length; index++){
				if(current8x8Block[index] == 0){
					if(counter == 15){
						boolean eob = true;
						for(int eobIndex = index; eobIndex < current8x8Block.length; eobIndex++){
							if(current8x8Block[eobIndex] != 0){
								eob = false;
							}
						}			
						if(eob == true){
							runLengthEncodedBlock.addLast(0);
							runLengthEncodedBlock.addLast(0);
							counter = 0;
							break;
						}else{
							runLengthEncodedBlock.addLast(counter);
							runLengthEncodedBlock.addLast(current8x8Block[index]);
							counter = 0;
						}
					}else{
						counter++;
					}
				}else{
					runLengthEncodedBlock.addLast(counter);
					runLengthEncodedBlock.addLast(current8x8Block[index]);
					counter = 0;
				}
			}
			if(counter != 0){
				runLengthEncodedBlock.addLast(0);
				runLengthEncodedBlock.addLast(0);
			}
			output.addLast(runLengthEncodedBlock.toArray(new Integer[runLengthEncodedBlock.size()]));
		}
		return output;
	}

}
