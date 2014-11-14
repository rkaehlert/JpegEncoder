package exercise_one.converter;

import java.util.List;

public class ConverterStringToInteger implements Converter<Integer> {
	
	public static Integer convert(List<Character> lstCharacter){
		 StringBuilder builder = new StringBuilder(lstCharacter.size());
        for (Character ch : lstCharacter)
        {
            builder.append(ch);
        }
        return Integer.parseInt(builder.toString());
	}
	
}
