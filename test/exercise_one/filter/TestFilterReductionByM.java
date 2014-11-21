package exercise_one.filter;

import filter.Filter;
import static org.junit.Assert.assertEquals;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import exercise_one.exception.InvalidParameterException;
import model.color.Colormodel;
import model.color.YCbCr;
import model.matrix.Coordinate;

public class TestFilterReductionByM {
	
	@Before
	public void init() {
	}
	
	@Test
	public void testFilterBySingleStep() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(1,1);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(1,1);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		assertEquals(result.size(), pixel.size());
		for(Coordinate coordinate : result.keySet()){
			pixel.remove(coordinate);
		}
		assertEquals(pixel.size(), 0);
	}
	
	@Test
	public void testFilterByMultipleRowStep() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(5,1);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(5,1);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		assertEquals(result.size(), pixel.size());
		for(Coordinate coordinate : result.keySet()){
			pixel.remove(coordinate);
		}
		assertEquals(pixel.size(), 0);
	}
	
	@Test
	public void testFilterByMultipleColStep() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(1,5);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(1,5);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		assertEquals(result.size(), pixel.size());
		for(Coordinate coordinate : result.keySet()){
			pixel.remove(coordinate);
		}
		assertEquals(pixel.size(), 0);
	}
	
	@Test
	public void testFilterByDifferentStep() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(3,7);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(3,7);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		assertEquals(result.size(), pixel.size());
		for(Coordinate coordinate : result.keySet()){
			pixel.remove(coordinate);
		}
		assertEquals(pixel.size(), 0);
	}
	
	@Test
	public void testFilterBySameStep() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(10,10);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(10,10);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		assertEquals(result.size(), pixel.size());
		for(Coordinate coordinate : result.keySet()){
			pixel.remove(coordinate);
		}
		assertEquals(pixel.size(), 0);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testFilterNullParameterException() throws InvalidParameterException{
		new FilterReductionByM().filter(null);	
	}
	
	@Test
	public void testFilterModuloRange() throws InvalidParameterException {
		Filter filter = new FilterReductionByM(7,11);
		TreeMap<Coordinate, Colormodel> pixel = this.fillCoordinateMapByRange(7,11);
		TreeMap<Coordinate, Colormodel> result = filter.filter(pixel);
		int highestXValue = 0;
		int highestYValue = 0;
		for(Coordinate coordinate : result.keySet()){
			if(coordinate.getX() > highestXValue){
				highestXValue = coordinate.getX();
			}
			if(coordinate.getY() > highestYValue){
				highestYValue = coordinate.getY();
			}
		}
		assertEquals(highestXValue, 22);
		assertEquals(highestYValue, 28);
	}
	
	private TreeMap<Coordinate, Colormodel> fillCoordinateMapByRange(int rowStep, int colStep){
		TreeMap<Coordinate, Colormodel> returnValue = new TreeMap<Coordinate, Colormodel>();
		int color = 1;
		for(int rowindex = 0; rowindex < 30; rowindex+=rowStep){
			for(int colIndex = 0; colIndex < 30; colIndex+=colStep){
				returnValue.put(new Coordinate(colIndex, rowindex), new YCbCr(color,color,color));
				color++;
			}
			color = 1;
		}
		return returnValue;
	}
	
}
