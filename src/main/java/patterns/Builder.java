package patterns;

/**
 * ¬°¬ä¬Õ¬Ö¬Ý¬ñ¬Ö¬ä ¬Ü¬à¬ß¬ã¬ä¬â¬å¬Ú¬â¬à¬Ó¬Ñ¬ß¬Ú¬Ö ¬ã¬Ý¬à¬Ø¬ß¬à¬Ô¬à ¬à¬Ò¬ì¬Ö¬Ü¬ä¬Ñ ¬à¬ä ¬Ö¬Ô¬à ¬á¬â¬Ö¬Õ¬ã¬ä¬Ñ¬Ó¬Ý¬Ö¬ß¬Ú¬ñ, ¬ä¬Ñ¬Ü ¬é¬ä¬à ¬Ó ¬â¬Ö¬Ù¬å¬Ý¬î¬ä¬Ñ¬ä¬Ö
 * ¬à¬Õ¬ß¬à¬Ô¬à ¬Ú ¬ä¬à¬Ô¬à ¬Ø¬Ö ¬á¬â¬à¬è¬Ö¬ã¬ã¬Ñ ¬Ü¬à¬ß¬ã¬ä¬â¬å¬Ú¬â¬à¬Ó¬Ñ¬ß¬Ú¬ñ ¬Þ¬à¬Ô¬å¬ä ¬á¬à¬Ý¬å¬é¬Ñ¬ä¬î¬ã¬ñ ¬â¬Ñ¬Ù¬ß¬í¬Ö ¬á¬â¬Ö¬Õ¬ã¬ä¬Ñ¬Ó¬Ý¬Ö¬ß¬Ú¬ñ.
 * @author Mike
 */

public class Builder {

	private int mandatory;
	private int optional;

	private Builder() {
	}
	
	public int getMandatory() {
		return mandatory;
	}

	public int getOptional() {
		return optional;
	}

	public static RealBuilder newBuilder() {
		return new Builder().new RealBuilder();
	}

	public class RealBuilder {

		private RealBuilder() {
		}

		public RealBuilder setMandatory(int mandatory) {
			Builder.this.mandatory = mandatory;
			return this;
		}

		public RealBuilder setOptional(int optional) {
			Builder.this.optional = optional;
			return this;
		}

		public Builder build() {
			return Builder.this;
		}
	}
	
	public static void main(String[] args) {
		Builder builder = Builder.newBuilder()
				.setMandatory(0)
				.setOptional(0)
				.build();
	}

}
