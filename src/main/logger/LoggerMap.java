package main.logger;

import java.util.Map;

public class LoggerMap <T,V> implements Logger {

	public void log(Map<T,V> map){
		String returnValue = "\ninhalt der map: \n\n";
		for(Map.Entry<T, V> entry : map.entrySet()) {
			returnValue = returnValue.concat(entry.getKey().toString() + ": " + entry.getValue().toString() + "\n");
		}
		LoggerText.log(returnValue);
	}
	
}
