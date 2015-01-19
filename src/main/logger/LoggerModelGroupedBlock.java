package main.logger;

import main.model.encoder.ModelAC;
import main.model.encoder.ModelBlock;
import main.model.encoder.ModelGroupedBlock;

public class LoggerModelGroupedBlock implements Logger {

	public static void log(ModelGroupedBlock groupedBlock) {
			for(ModelBlock modelBlockY : groupedBlock.getLstModelY()){
				LoggerText.log("LOGGE Y KANAL:");
				LoggerText.log("\tDC:" + modelBlockY.getDc().toString());
				for(ModelAC modelAC : modelBlockY.getAc()){
					LoggerText.log("\tAC:" + modelAC.toString());
				}
			}
			LoggerText.log("LOGGE CB KANAL:");
			LoggerText.log("\tDC:" + groupedBlock.getModelCb().getDc().toString());
			for(ModelAC modelAC : groupedBlock.getModelCb().getAc()){
				LoggerText.log("\tAC:" + modelAC.toString());
			}
			LoggerText.log("LOGGE CR KANAL:");
			LoggerText.log("\tDC:" + groupedBlock.getModelCr().getDc().toString());
			for(ModelAC modelAC : groupedBlock.getModelCr().getAc()){
				LoggerText.log("\tAC:" + modelAC.toString());
			}
			
	}
	
}
