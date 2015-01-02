package main.file.jpeg.segment.enums;
public enum EnumComponentId {

        Y(0),
        Cb(1),
        Cr(2);

        private Integer value;

        EnumComponentId(Integer value) {
            this.setValue(value);
        }

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
    }
