package patterns;

public class AbstractFactory2 {

	static AbstractFactory2 instance;
	
	interface Shape {
		void draw();
	}
	
	class Circle implements Shape {
		@Override
		public void draw() {
		}
	}
	
	interface Color {
		void fill();
	}
	
	class Red implements Color {
		@Override
		public void fill() {
		}
	}
	
	abstract class AbstractFactory {
		abstract Shape getShape(int type);
		abstract Color getColor(int color);
	}
	
	class ShapeFactory extends AbstractFactory {
		@Override
		Shape getShape(int type) {
			Shape s = null;
			switch (type) {
				case 0:
					s = new Circle();
					break;
			}
			return s;
		}
		
		@Override
		Color getColor(int color) {
			return null;
		}
	}
	
	class ColorFactory extends AbstractFactory {
		@Override
		Shape getShape(int type) {
			return null;
		}
		
		@Override
		Color getColor(int color) {
			Color c = null;
			switch (color) {
				case 0:
					c = new Red();
					break;
			}
			return c;
		}
	}
	
	static class ProducerFactory { // not static!
		static AbstractFactory getFactory(boolean isShape) {
			if (isShape)
				return instance.new ShapeFactory();
			else
				return instance.new ColorFactory();
		}
	}
	
    public static void main(String[] args) {
    	instance = new AbstractFactory2();
    	AbstractFactory shapeFactory = ProducerFactory.getFactory(true);
    	Shape shape = shapeFactory.getShape(0);
    	shape.draw();
    	AbstractFactory colorFactory = ProducerFactory.getFactory(false);
    	Color color = colorFactory.getColor(0);
    	color.fill();
	}

}
