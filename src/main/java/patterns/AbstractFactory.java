package patterns;

/**
 * ¬±¬â¬Ö¬Õ¬à¬ã¬ä¬Ñ¬Ó¬Ý¬ñ¬Ö¬ä ¬Ú¬ß¬ä¬Ö¬â¬æ¬Ö¬Û¬ã ¬Õ¬Ý¬ñ ¬ã¬à¬Ù¬Õ¬Ñ¬ß¬Ú¬ñ ¬ã¬Ö¬Þ¬Ö¬Û¬ã¬ä¬Ó ¬Ó¬Ù¬Ñ¬Ú¬Þ¬à¬ã¬Ó¬ñ¬Ù¬Ñ¬ß¬ß¬í¬ç ¬Ú¬Ý¬Ú ¬Ó¬Ù¬Ñ¬Ú¬Þ¬à¬Ù¬Ñ¬Ó¬Ú¬ã¬Ú¬Þ¬í¬ç ¬à¬Ò¬ì¬Ö¬Ü¬ä¬à¬Ó,
 * ¬ß¬Ö ¬ã¬á¬Ö¬è¬Ú¬æ¬Ú¬è¬Ú¬â¬å¬ñ ¬Ú¬ç ¬Ü¬à¬ß¬Ü¬â¬Ö¬ä¬ß¬í¬ç ¬Ü¬Ý¬Ñ¬ã¬ã¬à¬Ó.
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
