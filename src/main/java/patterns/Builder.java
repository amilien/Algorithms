package patterns;

/**
 * Отделяет конструирование сложного объекта от его представления, так что в результате одного и
 * того же процесса конструирования могут получаться разные представления.
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
