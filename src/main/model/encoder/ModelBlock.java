package main.model.encoder;

import java.util.LinkedList;

public class ModelBlock {

	int id = 0;
	
	private ModelDC dc;
	private LinkedList<ModelAC> ac = new LinkedList<ModelAC>();
	
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

	public LinkedList<ModelAC> getAc() {
		return ac;
	}

	public void setAc(LinkedList<ModelAC> ac) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
