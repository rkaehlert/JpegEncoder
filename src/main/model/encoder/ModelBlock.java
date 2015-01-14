package main.model.encoder;

import java.util.LinkedList;
import java.util.List;

public class ModelBlock {

	private ModelDC dc;
	private List<ModelAC> ac = new LinkedList<ModelAC>();
	
	public ModelBlock(){
		
	}
	
	public ModelBlock(ModelDC dc){
		this.dc = dc;
	}
	
	public ModelDC getDc() {
		return dc;
	}
	public void setDc(ModelDC dc) {
		this.dc = dc;
	}

	public List<ModelAC> getAc() {
		return ac;
	}

	public void setAc(List<ModelAC> ac) {
		this.ac = ac;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(dc.toString());
		for(ModelAC modelAC : this.ac){
			buffer.append(modelAC.toString());
		}
		return buffer.toString();
	}
	
}
