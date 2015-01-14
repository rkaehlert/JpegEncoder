package main.file.jpeg.segment.enums;

  public enum EnumSubSampling {

        NONE(0x22),
        FACTOR_2(0x11);

        private int value;

        EnumSubSampling(int value) {
            this.setValue(value);
        }

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

    }