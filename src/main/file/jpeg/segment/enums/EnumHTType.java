package main.file.jpeg.segment.enums;
public enum EnumHTType {
	
		DC(0),
		AC(1);
		
		private Integer value = null;
		
		private EnumHTType(Integer value){
			this.setValue(value);
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}