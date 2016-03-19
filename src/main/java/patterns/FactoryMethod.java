package patterns;

/**
 * ����֬լ���ѬӬݬ�֬� ���լܬݬѬ��Ѭ� �ڬ߬�֬��֬۬� �լݬ� ���٬լѬ߬ڬ� ��ܬ٬֬ެ�ݬ���� �߬֬ܬ�����Ԭ� �ܬݬѬ���. ���߬�ެ� ��ݬ�ӬѬެ�,
 * ���ѬҬ�ڬܬ� �լ֬ݬ֬Ԭڬ��֬� ���٬լѬ߬ڬ� ��Ҭ�֬ܬ��� �߬Ѭ�ݬ֬լ߬ڬܬѬ� ���լڬ�֬ݬ��ܬ�Ԭ� �ܬݬѬ���.
 * ����� ���٬Ӭ�ݬ�֬� �ڬ���ݬ�٬�ӬѬ�� �� �ܬ�լ� ����Ԭ�Ѭެެ� �߬� ���֬�ڬ�ڬ�֬�ܬڬ� �ܬݬѬ���,
 * �� �ެѬ߬ڬ��ݬڬ��ӬѬ�� �ѬҬ���Ѭܬ�߬�ެ� ��Ҭ�֬ܬ�Ѭެ� �߬� �Ҭ�ݬ֬� �Ӭ���ܬ�� ����Ӭ߬�.
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
