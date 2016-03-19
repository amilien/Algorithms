package patterns;

/**
 * Предназначен для динамического подключения дополнительного поведения к объекту.
 * @author Mike
 */

public class Decorator {

	public interface InterfaceComponent {
		void doOperation();
	}
	 
	class MainComponent implements InterfaceComponent {
	 
		@Override
		public void doOperation() {
			System.out.print("World!");
		}	
	}
	 
	abstract class AbstractDecorator implements InterfaceComponent {
		protected InterfaceComponent component;
	 
		public AbstractDecorator(InterfaceComponent c) {
			component = c;
		}
	 
		@Override
		public void doOperation() {
			component.doOperation();
		}
	 
        public void newOperation() {
        	System.out.println(" [Do Nothing]");
        }
	}
	 
	class DecoratorSpace extends AbstractDecorator {
	 
		public DecoratorSpace(InterfaceComponent c) {
			super(c);
		}
	 
		@Override
		public void doOperation() {
			System.out.print(" ");
			super.doOperation();
		}
	 
        @Override
        public void newOperation() {
            System.out.println(" [New space operation]");
        }	
	}
	 
	class DecoratorHello extends AbstractDecorator {
	 
		public DecoratorHello(InterfaceComponent c) {
			super(c);
		}
	 
		@Override
		public void doOperation() {
			System.out.print("Hello");
			super.doOperation();
		}	
	 
        @Override
        public void newOperation() {
            System.out.println(" [New hello operation]");
        }
	}
	 
	public static void main (String... s) {
		Decorator instance = new Decorator();
		AbstractDecorator c = instance.new DecoratorHello(instance.new DecoratorSpace(instance.new MainComponent()));
		c.doOperation();
        //c.newOperation();
    }

}
