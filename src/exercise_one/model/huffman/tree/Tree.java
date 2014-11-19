package exercise_one.model.huffman.tree;

import java.util.Arrays;
import java.util.List;

import exercise_one.model.comparator.huffman.ComparatorTreeDepth;


public abstract class Tree  {

	private int frequency = 0;
	private int depth = 0;

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void increaseDepth(int number) {
		this.depth = this.depth + number;
	}

	public boolean isBigger(Tree t2){
		return this.depth > t2.getDepth();
	}

	public static List<Tree> sortByDepth(Tree ... trees) {
		List<Tree> returnValue = Arrays.asList(trees);
		returnValue.sort(new ComparatorTreeDepth());
		return returnValue;
	}
}
