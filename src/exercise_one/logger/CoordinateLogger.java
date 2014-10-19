package exercise_one.logger;

import java.util.Map;
import java.util.TreeMap;

import exercise_one.exception.NotYetImplementedException;
import exercise_one.model.color.Colormodel;
import exercise_one.model.color.RGB;
import exercise_one.model.color.YCbCr;
import exercise_one.model.matrix.Coordinate;

public class CoordinateLogger implements Logger{
	
	public void log(TreeMap<Coordinate, Colormodel> pixel, int width, int height, boolean isLoggingEnabled){
		System.out.println("----------------------------------------------------------");
		System.out.println("Anzahl Eintraege: " + pixel.size());
		System.out.println("Anzahl Zeilen: " + height);
		System.out.println("Anzahl Spalten: " + width);
		System.out.println("----------------------------------------------------------");
		if(isLoggingEnabled){
			int last_y = 0;
			for(Map.Entry<Coordinate, Colormodel> entry : pixel.entrySet()){
				if(entry.getKey().getY() > last_y){
					System.out.print("\n");
					last_y = 0;
				}
				last_y = entry.getKey().getY();
				if(entry.getValue() instanceof RGB){
					//System.out.print("X");
					System.out.print("(" + ((RGB)entry.getValue()).getRed() + "," + ((RGB)entry.getValue()).getGreen() + "," + ((RGB)entry.getValue()).getBlue() + ")");
					//System.out.print("(" + entry.getKey().getX() + "," + entry.getKey().getY() + ")");
				}else if(entry.getValue() instanceof YCbCr){
					System.out.print("(" + entry.getKey().getX() + "," + entry.getKey().getY() + ")");
					//System.out.print("(" + ((YCbCr)entry.getValue()).getY() + "," + ((YCbCr)entry.getValue()).getCb() + "," + ((YCbCr)entry.getValue()).getCr() + ")");
				}else{
					System.out.print("X");
				}
			}
		}
	}

	@Override
	public void log() {
		try{
			throw new NotYetImplementedException();
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
	}
	
}
