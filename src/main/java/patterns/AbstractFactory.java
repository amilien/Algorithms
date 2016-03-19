package patterns;

/**
 * Предоставляет интерфейс для создания семейств взаимосвязанных или не взаимозависимых объектов, не специфицируя их конкретных классов.
 * @author Mike
 */

public class AbstractFactory {

	abstract class ProductA {
	}
	
	class ConcreteProductA1 extends ProductA {
	}
	
	class ConcreteProductA2 extends ProductA {
	}

	abstract class ProductB {
	}
	
	class ConcreteProductB1 extends ProductB {
	}
	
	class ConcreteProductB2 extends ProductB {
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
		Factory[] factories = { instance.new ConcreteFactory1(), instance.new ConcreteFactory2() };
		for (Factory factory: factories) {
			instance.createProducts(factory);
		}
	}

}
