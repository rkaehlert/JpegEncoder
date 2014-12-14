package main.file.jpeg.segment.enums;

 public enum EnumJPEGResolutionUnit {

        NO_UNIT((byte) 0),
        DOTS_PER_INCH((byte) 1),
        DOTS_PER_CM((byte) 2);

        private byte value;

        EnumJPEGResolutionUnit(byte value) {
            this.setValue(value);
        }

		public byte getValue() {
			return value;
		}

		public void setValue(byte value) {
			this.value = value;
		}
    }
