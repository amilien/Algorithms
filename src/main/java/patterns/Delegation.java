package patterns;

/**
 * ¬°¬Ò¬ì¬Ö¬Ü¬ä ¬Ó¬ß¬Ö¬ê¬ß¬Ö ¬Ó¬í¬â¬Ñ¬Ø¬Ñ¬Ö¬ä ¬ß¬Ö¬Ü¬à¬ä¬à¬â¬à¬Ö ¬á¬à¬Ó¬Ö¬Õ¬Ö¬ß¬Ú¬Ö, ¬ß¬à ¬Ó ¬â¬Ö¬Ñ¬Ý¬î¬ß¬à¬ã¬ä¬Ú ¬á¬Ö¬â¬Ö¬Õ¬Ñ¬×¬ä ¬à¬ä¬Ó¬Ö¬ä¬ã¬ä¬Ó¬Ö¬ß¬ß¬à¬ã¬ä¬î
 * ¬Ù¬Ñ ¬Ó¬í¬á¬à¬Ý¬ß¬Ö¬ß¬Ú¬Ö ¬ï¬ä¬à¬Ô¬à ¬á¬à¬Ó¬Ö¬Õ¬Ö¬ß¬Ú¬ñ ¬ã¬Ó¬ñ¬Ù¬Ñ¬ß¬ß¬à¬Þ¬å ¬à¬Ò¬ì¬Ö¬Ü¬ä¬å.
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
