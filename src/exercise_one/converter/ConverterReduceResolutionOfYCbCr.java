package exercise_one.converter;

import java.util.TreeMap;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.ColorChannel;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;


public class ConverterReduceResolutionOfYCbCr implements Converter<YCbCr> {

	private int yStep = 0;
	private int cbStep = 0;
	private int crStep = 0;
	
	public ConverterReduceResolutionOfYCbCr(int yStep, int cbStep, int crStep) {
		this.yStep = yStep;
		this.cbStep = cbStep;
		this.crStep = crStep;
	}
	
	@Override
	public YCbCr convert() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public TreeMap<Coordinate, Colormodel> convert(TreeMap<Coordinate, Colormodel> pixel){
		for(Colormodel colormodel : pixel.values()){
			YCbCr yCbCr = (YCbCr)colormodel;
			yCbCr.setY(new ColorChannel<Double>(yCbCr.getY()-yStep));
			yCbCr.setCb(new ColorChannel<Double>(yCbCr.getCb()-cbStep));
			yCbCr.setCr(new ColorChannel<Double>(yCbCr.getCr()-crStep));
		}
		return pixel;
	}

}
