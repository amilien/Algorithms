package patterns;

public class FactoryMethod1 {
	
	private static FactoryMethod1 test;
	
	enum FurnitureType {
		CHAIR, BED
	}
	
    abstract class Furniture {
    	
    	private FurnitureType mType;
    	
    	public Furniture(FurnitureType type) {
    		mType = type;
    	}
    	
    	protected abstract void create();
    	
    }
    
    class Chair extends Furniture {
    	
    	public Chair() {
    		super(FurnitureType.CHAIR);
    		create();
    	}
    	
    	@Override
    	protected void create() {
    		System.out.println("Chair created.");
    	}
    }
    
    class Bed extends Furniture {
    	
    	public Bed() {
    		super(FurnitureType.BED);
    		create();
    	}
    	
    	@Override
    	protected void create() {
    		System.out.println("Bed created.");
    	}
    }
    
    static class FurnitureFactory {
    	
    	public static Furniture createFurniture(FurnitureType type) {
    		Furniture furniture = null;
    		switch (type) {
	    		case CHAIR:
	    			furniture = test.new Chair();
	    			break;
	    		case BED:
	    			furniture = test.new Bed();
	    			break;
    		}
    		return furniture;
    	}
    }
    
    public static void main(String[] args) {
    	test = new FactoryMethod1();
    	Furniture chair = FurnitureFactory.createFurniture(FurnitureType.CHAIR);
    	Furniture bed = FurnitureFactory.createFurniture(FurnitureType.BED);
    }
}
