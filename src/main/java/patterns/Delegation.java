package patterns;

/**
 * Возможность изменить поведение конкретного экземпляра объекта вместо создания нового класса путем наследования.
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
