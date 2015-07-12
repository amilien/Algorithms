package oop;

public class B extends A {

	int i;
	
	public B() {
		System.out.println("B()");
	}
	
	public void m() {
		System.out.println("B.m()");
	}

	public static void main(String[] args) {
		B b = new B();
		A a = b;
		a.m();
		B b1 = (B)a;
		System.out.println(b instanceof A);
	}

}
