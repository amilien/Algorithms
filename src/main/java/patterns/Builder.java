package patterns;

/**
 *  ����լ֬ݬ�֬� �ܬ�߬����ڬ��ӬѬ߬ڬ� ��ݬ�ج߬�Ԭ� ��Ҭ�֬ܬ�� ��� �֬Ԭ� ���֬լ��ѬӬݬ֬߬ڬ�, ��Ѭ� ���� �� ��֬٬�ݬ��Ѭ�� ��լ߬�Ԭ� �� ���Ԭ� �ج�
 *  �����֬��� �ܬ�߬����ڬ��ӬѬ߬ڬ� �ެ�Ԭ�� ���ݬ��Ѭ���� ��Ѭ٬߬�� ���֬լ��ѬӬݬ֬߬ڬ�. 
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
