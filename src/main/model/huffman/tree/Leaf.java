package main.model.huffman.tree;

public class Leaf extends Tree {

	private Object value;
	
	public Leaf(Object value, int frequency){
		this.setValue(value);
		super.setFrequency(frequency);
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int compareTo(Tree o) {
		return 0;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
	
}
