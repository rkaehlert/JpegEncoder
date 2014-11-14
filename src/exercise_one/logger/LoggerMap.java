package exercise_one.logger;

import java.util.Map;

public class LoggerMap <T,V> implements Logger {

	public void log(Map<T,V> map){
		String returnValue = "\ninhalt der map: \n\n";
		for(Map.Entry<T, V> entry : map.entrySet()) {
			returnValue = returnValue.concat(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		System.out.println(returnValue);
	}
	
}
