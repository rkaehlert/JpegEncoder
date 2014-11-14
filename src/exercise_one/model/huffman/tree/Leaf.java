package exercise_one.model.huffman.tree;

public class Leaf extends Tree {

	private int number;
	
	public Leaf(int number, int frequency){
		this.setNumber(number);
		super.setFrequency(frequency);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
