package main.validator;

import main.datahandler.DataHandler;
import main.datahandler.DataHandlerCompareNaturalNumber;

public class ValidatorBiggerOrEquals implements Validator {

	@Override
	public boolean validate(DataHandler datahandler) {
		DataHandlerCompareNaturalNumber datahandlerCompare = (DataHandlerCompareNaturalNumber)datahandler;
		if(datahandlerCompare.getValueOne() >= datahandlerCompare.getValueTwo()){
			return true;
		}
		return false;
	}

}
