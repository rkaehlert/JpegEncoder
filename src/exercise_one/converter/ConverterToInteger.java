package exercise_one.converter;

import exercise_one.exception.NotYetImplementedException;

public class ConverterToInteger implements Converter<Integer> {
	
    @Override
    public Integer convert()
    {
        try
        {
            throw new NotYetImplementedException();
        }
        catch (NotYetImplementedException e)
        {
            e.printStackTrace();
        }
        return null;
    }
	
	
}
