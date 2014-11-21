package exercise_one.model.comparator.huffman;

import java.util.Comparator;

import exercise_one.model.huffman.tree.Tree;

public class ComparatorTreeDepth implements Comparator<Tree> {

	@Override
	public int compare(Tree o1, Tree o2) {
		if(o1 == null || o2 == null){
			return 1;
		}else if(o1.getDepth() <= o2.getDepth()){
			return -1;
		}else if(o1.getDepth() > o2.getDepth()){
			return 1;
		}
		return 0;
	}

}
