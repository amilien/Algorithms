package patterns;

public class AbstractFactory {

	interface ProductA {
	}
	
	class ConcreteProductA1 implements ProductA {
	}
	
	class ConcreteProductA2 implements ProductA {
	}
	
	interface ProductB {
	}
	
	class ConcreteProductB1 implements ProductB {
	}
	
	class ConcreteProductB2 implements ProductB {
	}
	
	abstract class Factory {
		abstract ProductA createProductA();
		abstract ProductB createProductB();
	}
	
	class ConcreteFactory1 extends Factory {
		ProductA createProductA() {
			return new ConcreteProductA1();
		}
		ProductB createProductB() {
			return new ConcreteProductB1();
		}
	}
	
	class ConcreteFactory2 extends Factory {
		ProductA createProductA() {
			return new ConcreteProductA2();
		}
		ProductB createProductB() {
			return new ConcreteProductB2();
		}
	}
	
	void createProducts(Factory factory) {
		ProductA product1 = factory.createProductA();
		ProductB product2 = factory.createProductB();
	}
	
	public static void main(String[] args) {
		AbstractFactory instance = new AbstractFactory();
		Factory factory;
		if (true)
			factory = instance.new ConcreteFactory1();
		else
			factory = instance.new ConcreteFactory2();
		instance.createProducts(factory);
	}

}
