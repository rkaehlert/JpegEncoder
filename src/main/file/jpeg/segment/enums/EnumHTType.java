package main.file.jpeg.segment.enums;
public enum EnumHTType {
		AC(1),
		DC(0);
		
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