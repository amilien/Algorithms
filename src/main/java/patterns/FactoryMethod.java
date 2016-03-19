package patterns;

/**
 * ���������� ��������� ��� �������� �������, �� ��������� ���������� ������� � ���,
 * ����� ����� ��������������. ��������� ����� ��������� ������ ������������ �������� ����������.
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
