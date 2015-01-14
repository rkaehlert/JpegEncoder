package main.file.resource;

public class UtilityResourcePath {

    public static String getPath(String name) {
        ClassLoader classLoader = UtilityResourcePath.class.getClassLoader();
        return classLoader.getResource("main/resources/"+name).getPath();
    }
	
}
