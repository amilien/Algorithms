package oop;

public class C extends B {

	public C() {
		System.out.println("C()");
	}
	
	public C(int a) {
		System.out.println("C(a)");
	}
	
	public static void main(String[] args) {
		C c = new C(0);
	}

}
