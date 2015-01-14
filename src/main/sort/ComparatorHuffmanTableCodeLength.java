package main.sort;

import java.util.Comparator;

import main.model.encoder.ModelHT;

public class ComparatorHuffmanTableCodeLength implements Comparator<ModelHT> {

	@Override
	public int compare(ModelHT o1, ModelHT o2) {
		return o1.getCode().compareTo(o2.getCode());
	}




}
