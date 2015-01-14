package main.model.encoder;

import java.util.ArrayList;
import java.util.List;

public class ModelGroupedBlock {

	private ModelBlock[] lstModelY = new ModelBlock[4];
	private ModelBlock modelCb;
	private ModelBlock modelCr;
	
	public ModelGroupedBlock(){
		this.modelCb = new ModelBlock();
		this.modelCr = new ModelBlock();
	}
	
	public ModelBlock[] getLstModelY() {
		return lstModelY;
	}
	public void setLstModelY(ModelBlock[] lstModelY) {
		this.lstModelY = lstModelY;
	}
	public ModelBlock getModelCb() {
		return modelCb;
	}
	public void setModelCb(ModelBlock modelCb) {
		this.modelCb = modelCb;
	}
	public ModelBlock getModelCr() {
		return modelCr;
	}
	public void setModelCr(ModelBlock modelCr) {
		this.modelCr = modelCr;
	}
	
	public String toString(){
		StringBuffer output = new StringBuffer();
		for(ModelBlock modelBlockY : lstModelY){
			output.append(modelBlockY.toString());
		}
		output.append(modelCb.toString());
		output.append(modelCr.toString());
		return output.toString();
	}
	
}
