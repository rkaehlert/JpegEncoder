package main.datahandler;

public class DataHandlerCompareNaturalNumber implements DataHandler {

	private Integer valueOne;
	private Integer valueTwo;
	
	public DataHandlerCompareNaturalNumber(Integer valueOne, Integer valueTwo){
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	public Integer getValueOne() {
		return valueOne;
	}
	public void setValueOne(Integer valueOne) {
		this.valueOne = valueOne;
	}
	public Integer getValueTwo() {
		return valueTwo;
	}
	public void setValueTwo(Integer valueTwo) {
		this.valueTwo = valueTwo;
	}
	
	
}
