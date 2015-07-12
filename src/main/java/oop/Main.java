package oop;

public class Main {
	
	class A {
	    void m1(A a) {
	        System.out.print("A");
	    }
	}

	class B extends A {
	    final void m1(B b) {
	        System.out.print("B");
	    }
	}

	class C extends B {
	    void m1(A c) {
	        System.out.print("C");
	    }
	}

	    public static void main(String[] args) {
	    	Main m = new Main();
	        A c1 = m.new C();
	        c1.m1(m.new B());
	    }
	
	/*String variable;
    protected int a;
    
	class T {
		int t = 2;
	}
	
	void setT(T t) {
		System.out.println(t.t);
		t = new T();
		System.out.println(t.t);
		t.t = 1;
		System.out.println(t.t);
		t = null;
	}
	
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main m = new Main();
        T t = m.new T();
        m.setT(t);
        System.out.println(t.t);
    }*/

}
