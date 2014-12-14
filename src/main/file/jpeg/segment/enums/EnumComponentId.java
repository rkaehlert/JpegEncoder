package main.file.jpeg.segment.enums;
public enum EnumComponentId {

        Y(1),
        Cb(2),
        Cr(3);

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
