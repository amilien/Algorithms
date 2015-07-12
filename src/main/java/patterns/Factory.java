package patterns;

public class Factory {

	private static final String SQUARE = "Square";
	private static final String CIRCLE = "Circle";
	
	interface Shape {
		void draw();
	}
	
	class Square implements Shape {
		public void draw() {
			System.out.println("Square");
		}
	}
	
	class Circle implements Shape {
		public void draw() {
			System.out.println("Circle");
		}
	}

	class ShapeFactory {
		public Shape getShape(String type) {
			Shape shape = null;
			if (SQUARE.equals(type))
				shape = new Square();
			else if (CIRCLE.equals(type))
				shape = new Circle();
			return shape;
		}
	}
	
	public static void main(String[] args) {
		Factory factory = new Factory();
		ShapeFactory shapeFactory = factory.new ShapeFactory();
		Shape circle = shapeFactory.getShape(CIRCLE);
		Shape square = shapeFactory.getShape(SQUARE);
		circle.draw();
		square.draw();
	}

}
