package patterns;

/**
 *  ¬°¬ä¬Õ¬Ö¬Ý¬ñ¬Ö¬ä ¬Ü¬à¬ß¬ã¬ä¬â¬å¬Ú¬â¬à¬Ó¬Ñ¬ß¬Ú¬Ö ¬ã¬Ý¬à¬Ø¬ß¬à¬Ô¬à ¬à¬Ò¬ì¬Ö¬Ü¬ä¬Ñ ¬à¬ä ¬Ö¬Ô¬à ¬á¬â¬Ö¬Õ¬ã¬ä¬Ñ¬Ó¬Ý¬Ö¬ß¬Ú¬ñ, ¬ä¬Ñ¬Ü ¬é¬ä¬à ¬Ó ¬â¬Ö¬Ù¬å¬Ý¬î¬ä¬Ñ¬ä¬Ö ¬à¬Õ¬ß¬à¬Ô¬à ¬Ú ¬ä¬à¬Ô¬à ¬Ø¬Ö
 *  ¬á¬â¬à¬è¬Ö¬ã¬ã¬Ñ ¬Ü¬à¬ß¬ã¬ä¬â¬å¬Ú¬â¬à¬Ó¬Ñ¬ß¬Ú¬ñ ¬Þ¬à¬Ô¬å¬ä ¬á¬à¬Ý¬å¬é¬Ñ¬ä¬î¬ã¬ñ ¬â¬Ñ¬Ù¬ß¬í¬Ö ¬á¬â¬Ö¬Õ¬ã¬ä¬Ñ¬Ó¬Ý¬Ö¬ß¬Ú¬ñ. 
 * @author Mike
 */

public class Builder {

	private final int mandatory1;
	private final int mandatory2;
	private final int optional1;
	private final int optional2;
	
	private Builder(RealBuilder builder) {
		mandatory1 = builder.mandatory1;
		mandatory2 = builder.mandatory2;
		optional1 = builder.optional1;
		optional2 = builder.optional2;
	}
	
	public int getMandatroy1() {
		return mandatory1;
	}
	
	// ...etc with getters for all members
	
	public static class RealBuilder {

		private final int mandatory1;
		private final int mandatory2;
		private int optional1;
		private int optional2;
		
		public RealBuilder(int mandatory1, int mandatory2) {
			this.mandatory1 = mandatory1;
			this.mandatory2 = mandatory2;
		}
		
		public RealBuilder setOptional1(int optional1) {
			this.optional1 = optional1;
			return this;
		}

		// ...etc with setters for optional members
		
		public Builder build() {
			return new Builder(this);
		}
	}
	
	public static void main(String[] args) {
		Builder instance = new Builder.RealBuilder(0, 0).setOptional1(0).build();
	}

}
