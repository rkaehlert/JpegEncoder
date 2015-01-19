package main.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.file.resource.UtilityResourcePath;

public class LoggerToFile implements Logger {

	public void log(StringBuffer text){
		ClassLoader classLoader = UtilityResourcePath.class.getClassLoader();
		String path = classLoader.getResource("main/resources/log.txt").getPath();
		BufferedWriter bufferedWriter = null;
		try {
			FileWriter fileWriter = new FileWriter(new File(path));
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(text.toString());
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
