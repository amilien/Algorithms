package patterns;

/**
 * ¬±¬â¬Ö¬Õ¬ß¬Ñ¬Ù¬ß¬Ñ¬é¬Ö¬ß ¬Õ¬Ý¬ñ ¬Õ¬Ú¬ß¬Ñ¬Þ¬Ú¬é¬Ö¬ã¬Ü¬à¬Ô¬à ¬á¬à¬Õ¬Ü¬Ý¬ð¬é¬Ö¬ß¬Ú¬ñ ¬Õ¬à¬á¬à¬Ý¬ß¬Ú¬ä¬Ö¬Ý¬î¬ß¬à¬Ô¬à ¬á¬à¬Ó¬Ö¬Õ¬Ö¬ß¬Ú¬ñ ¬Ü ¬à¬Ò¬ì¬Ö¬Ü¬ä¬å. 
 * ¬±¬â¬Ö¬Õ¬à¬ã¬ä¬Ñ¬Ó¬Ý¬ñ¬Ö¬ä ¬Ô¬Ú¬Ò¬Ü¬å¬ð ¬Ñ¬Ý¬î¬ä¬Ö¬â¬ß¬Ñ¬ä¬Ú¬Ó¬å ¬á¬â¬Ñ¬Ü¬ä¬Ú¬Ü¬Ö ¬ã¬à¬Ù¬Õ¬Ñ¬ß¬Ú¬ñ ¬á¬à¬Õ¬Ü¬Ý¬Ñ¬ã¬ã¬à¬Ó ¬ã ¬è¬Ö¬Ý¬î¬ð ¬â¬Ñ¬ã¬ê¬Ú¬â¬Ö¬ß¬Ú¬ñ ¬æ¬å¬ß¬Ü¬è¬Ú¬à¬ß¬Ñ¬Ý¬î¬ß¬à¬ã¬ä¬Ú.
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
