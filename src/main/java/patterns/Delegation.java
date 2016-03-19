package patterns;

/**
 * ���Ҭ�֬ܬ� �Ӭ߬֬�߬� �Ӭ��ѬجѬ֬� �߬֬ܬ������ ���Ӭ֬լ֬߬ڬ�, �߬� �� ��֬Ѭݬ�߬���� ��֬�֬լѬ׬� ���Ӭ֬���Ӭ֬߬߬����
 * �٬� �Ӭ���ݬ߬֬߬ڬ� ����Ԭ� ���Ӭ֬լ֬߬ڬ� ��Ӭ�٬Ѭ߬߬�ެ� ��Ҭ�֬ܬ��.
 * @author Mike
 */

public class Delegation {

	class A {
		public void m() {
			System.out.println("A.a()");
		}
	}
	
	class B {

		A a = new A();
		
		public void m() {
			System.out.println("B.a()");
			a.m();
		}
	}

	public static void main(String[] args) {
		Delegation delegation = new Delegation();
		B b = delegation.new B();
		b.m();
	}

}
