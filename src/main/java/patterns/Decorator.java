package patterns;

public class Decorator {

	interface Shape {
		void draw();
	}
	
	class Circle implements Shape {
		@Override
		public void draw() {
			System.out.println("Circle.draw()");
		}
	}
	
	public abstract class ShapeDecorator implements Shape {
		protected Shape decoratedShape;

		public ShapeDecorator(Shape decoratedShape) {
			this.decoratedShape = decoratedShape;
		}

		public void draw() {
			decoratedShape.draw();
		}	
	}
	
	public class RedShapeDecorator extends ShapeDecorator {

		public RedShapeDecorator(Shape decoratedShape) {
			super(decoratedShape);		
		}

		@Override
		public void draw() {
			decoratedShape.draw();	       
		    setRedBorder(decoratedShape);
		}

		private void setRedBorder(Shape decoratedShape){
			System.out.println("Border Color: Red");
		}
	}
	
	
	public static void main(String[] args) {
		Decorator instance = new Decorator();
		Shape circle = instance.new Circle();
		Shape redCircle = instance.new RedShapeDecorator(circle);
		System.out.println("Circle with normal border");
		circle.draw();
		System.out.println("Circle with red border");
		redCircle.draw();
	}

}
