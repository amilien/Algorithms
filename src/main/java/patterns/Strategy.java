package patterns;

/**
 * Enables an algorithm's behavior to be selected at runtime.
 * @author Mike
 */
public class Strategy {

	interface IStrategy {
		public void doOperation();
	}
	
	class Strategy1 implements IStrategy {
		@Override
		public void doOperation() {
		}
	}

	class Strategy2 implements IStrategy {
		@Override
		public void doOperation() {
		}
	}

	class Product {
		void useStrategy(IStrategy strategy) {
			strategy.doOperation();
		}
	}
	
	public static void main(String[] args) {
		Strategy instance = new Strategy();
		IStrategy strategy;
		if (true)
			strategy = instance.new Strategy1();
		else
			strategy = instance.new Strategy2();
		Product product = instance.new Product();
		product.useStrategy(strategy);
	}

}
