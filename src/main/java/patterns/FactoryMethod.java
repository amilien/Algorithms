package patterns;

/**
 * ¬±¬â¬Ö¬Õ¬à¬ã¬ä¬Ñ¬Ó¬Ý¬ñ¬Ö¬ä ¬á¬à¬Õ¬Ü¬Ý¬Ñ¬ã¬ã¬Ñ¬Þ ¬Ú¬ß¬ä¬Ö¬â¬æ¬Ö¬Û¬ã ¬Õ¬Ý¬ñ ¬ã¬à¬Ù¬Õ¬Ñ¬ß¬Ú¬ñ ¬ï¬Ü¬Ù¬Ö¬Þ¬á¬Ý¬ñ¬â¬à¬Ó ¬ß¬Ö¬Ü¬à¬ä¬à¬â¬à¬Ô¬à ¬Ü¬Ý¬Ñ¬ã¬ã¬Ñ. ¬ª¬ß¬í¬Þ¬Ú ¬ã¬Ý¬à¬Ó¬Ñ¬Þ¬Ú,
 * ¬¶¬Ñ¬Ò¬â¬Ú¬Ü¬Ñ ¬Õ¬Ö¬Ý¬Ö¬Ô¬Ú¬â¬å¬Ö¬ä ¬ã¬à¬Ù¬Õ¬Ñ¬ß¬Ú¬Ö ¬à¬Ò¬ì¬Ö¬Ü¬ä¬à¬Ó ¬ß¬Ñ¬ã¬Ý¬Ö¬Õ¬ß¬Ú¬Ü¬Ñ¬Þ ¬â¬à¬Õ¬Ú¬ä¬Ö¬Ý¬î¬ã¬Ü¬à¬Ô¬à ¬Ü¬Ý¬Ñ¬ã¬ã¬Ñ.
 * ¬¿¬ä¬à ¬á¬à¬Ù¬Ó¬à¬Ý¬ñ¬Ö¬ä ¬Ú¬ã¬á¬à¬Ý¬î¬Ù¬à¬Ó¬Ñ¬ä¬î ¬Ó ¬Ü¬à¬Õ¬Ö ¬á¬â¬à¬Ô¬â¬Ñ¬Þ¬Þ¬í ¬ß¬Ö ¬ã¬á¬Ö¬è¬Ú¬æ¬Ú¬é¬Ö¬ã¬Ü¬Ú¬Ö ¬Ü¬Ý¬Ñ¬ã¬ã¬í,
 * ¬Ñ ¬Þ¬Ñ¬ß¬Ú¬á¬å¬Ý¬Ú¬â¬à¬Ó¬Ñ¬ä¬î ¬Ñ¬Ò¬ã¬ä¬â¬Ñ¬Ü¬ä¬ß¬í¬Þ¬Ú ¬à¬Ò¬ì¬Ö¬Ü¬ä¬Ñ¬Þ¬Ú ¬ß¬Ñ ¬Ò¬à¬Ý¬Ö¬Ö ¬Ó¬í¬ã¬à¬Ü¬à¬Þ ¬å¬â¬à¬Ó¬ß¬Ö.
 * @author Mike
 */

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
		Creator[] creators = { instance.new ConcreteCreator1(), instance.new ConcreteCreator2() };
		for (Creator creator: creators) {
			Product product = creator.factoryMethod();
		}
    }

}
