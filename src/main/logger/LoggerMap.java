package main.logger;

import java.util.Map;

public class LoggerMap <T,V> implements Logger {

	public void log(Map<T,V> map){
		String returnValue = "\ninhalt der map: \n\n";
		for(Map.Entry<T, V> entry : map.entrySet()) {
			returnValue = returnValue.concat(String.format("%03d", Integer.valueOf(entry.getKey().toString())) + ": " + entry.getValue().toString());
			returnValue = returnValue.concat(" laenge: " + entry.getValue().toString().length() + "\n");
		}
		LoggerText.log(returnValue);
	}
	
}
