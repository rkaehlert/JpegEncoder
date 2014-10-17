package exercise_one.converter;

import java.util.List;

import exercise_one.exception.NotYetImplementedException;

public class StringToInteger implements Converter<Integer> {
	
	public static Integer convert(List<Character> lstCharacter){
		 StringBuilder builder = new StringBuilder(lstCharacter.size());
        for (Character ch : lstCharacter)
        {
            builder.append(ch);
        }
        return Integer.parseInt(builder.toString());
	}

	@Override
	public Integer convert() {
		try{
			throw new NotYetImplementedException();	
		}catch(NotYetImplementedException e){
			e.printStackTrace();
		}
		return null;
	}
	
}