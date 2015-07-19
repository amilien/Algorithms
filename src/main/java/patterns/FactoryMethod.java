package patterns;

public class FactoryMethod {

	abstract class Product {
	}
	
	class ConcreteProduct1 extends Product {
	}
	
	class ConcreteProduct2 extends Product {
	}
	
	abstract class Creator {
		abstract Product factoryMethod();
	}
	
	class ConcreteCreator1 extends Creator {
		@Override
		Product factoryMethod() {
			return new ConcreteProduct1();
		}
	}
	
	class ConcreteCreator2 extends Creator {
		@Override
		Product factoryMethod() {
			return new ConcreteProduct2();
		}
	}
	
    public static void main(String[] args) {
    	FactoryMethod instance = new FactoryMethod();
    	Creator creator;
    	if (true)
    		creator = instance.new ConcreteCreator1();
    	else
    		creator = instance.new ConcreteCreator2();
    	Product product = creator.factoryMethod();
    }

}
