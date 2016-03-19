package patterns;

/**
 * ����լ֬ݬ�֬� �ܬ�߬����ڬ��ӬѬ߬ڬ� ��ݬ�ج߬�Ԭ� ��Ҭ�֬ܬ�� ��� �֬Ԭ� ���֬լ��ѬӬݬ֬߬ڬ�, ��Ѭ� ���� �� ��֬٬�ݬ��Ѭ��
 * ��լ߬�Ԭ� �� ���Ԭ� �ج� �����֬��� �ܬ�߬����ڬ��ӬѬ߬ڬ� �ެ�Ԭ�� ���ݬ��Ѭ���� ��Ѭ٬߬�� ���֬լ��ѬӬݬ֬߬ڬ�.
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
