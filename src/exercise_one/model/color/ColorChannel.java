package exercise_one.model.color;

public class ColorChannel <T> {

	T value = null;
	boolean isReduced;
	
	
	public ColorChannel(T value){
		this.value = value;
		this.isReduced = false;
	}

	public boolean isReduced(){
		return this.isReduced;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setReduced(boolean isReduced) {
		this.isReduced = isReduced;
	}
	
	public void reset(){
		this.value = null;
	}
}
