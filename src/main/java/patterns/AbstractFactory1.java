package patterns;

public class AbstractFactory1 {

    public abstract class FurnitureFactory {
    	public abstract Chair createChair();
    	public abstract Bed createBed();
    }
    
    public interface Chair {
    	public void sit();
    }
    
    public interface Bed {
    	public void lie();
    }
    
    public class MyChair implements Chair {
    	public void sit() {
    	}
    }
    
    public class MyBed implements Bed {
    	public void lie() {
    	}
    }
    
    public class Furniture extends FurnitureFactory { 
    	
    	public Chair createChair() {
    		return new MyChair();
    	}
    	
    	public Bed createBed() {
    		return new MyBed();
    	}
    }

    public void createFurniture(FurnitureFactory factory) {
    	Chair chair = factory.createChair();
    	Bed bed = factory.createBed();
    }
    
    public static void main(String[] args) {

	}

}
