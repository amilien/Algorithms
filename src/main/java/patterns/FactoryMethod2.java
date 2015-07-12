package patterns;

public class FactoryMethod2 {

	private static FactoryMethod2 test;
	
	abstract class Product {
	}
	
	class Product1 extends Product {
	}
	
	class Product2 extends Product {
	}
	
	abstract class Creator {
		abstract Product factoryMethod();
	}
	
	class Creator1 extends Creator {
		@Override
		Product factoryMethod() {
			return new Product1();
		}
	}
	
	class Creator2 extends Creator {
		@Override
		Product factoryMethod() {
			return new Product2();
		}
	}
	
    public static void main(String[] args) {
    	test = new FactoryMethod2();
    	Creator creator1 = test.new Creator1();
    	Product product1 = creator1.factoryMethod();
    	Creator creator2 = test.new Creator2();
    	Product product2 = creator1.factoryMethod();
    }

}
