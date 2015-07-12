package yandex;

import java.util.ArrayList;

public class GCTest {

    static class A {
        private String myName;
        public A(String myName) {
            this.myName = myName;
        }
        
        @Override
        protected void finalize() {
        	System.out.println("fin: "+myName);
        }
    }

    public static void main(String[] args) {
        A a1 = new A("a1");
        A a2 = new A("a2");
        ArrayList<A> list = new ArrayList<A>();
        list.add(a1);
        A[] mas = new A[2];
        mas[0] = a2;
        a2 = a1;
        clear(mas);
        a1 = null;
        a2 = null;
        System.gc();
        try{Thread.sleep(1000);}catch(InterruptedException e){}
    }

    private static void clear(A[] mas) {
        mas = null;
	}
}
