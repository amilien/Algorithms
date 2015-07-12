package patterns;

/*
NON-LAZY SINGLETONS

1. Easy, thread-safe

public class Singleton {
    public static final Singleton INSTANCE = new Singleton();
}

2. Easy, thread-safe

public enum Singleton {
    INSTANCE;
}

LAZY SINGLETONS

1. low performance

public class Singleton {
    private static Singleton instance;
    
    public static synchronized Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}

2. high performance (double-checked locking + volatile)

public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        Singleton localInstance = instance;
        if (localInstance == null) {
            synchronized (Singleton.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new Singleton();
            }
        }
        return localInstance;
    }
}
*/

// 3. On demand holder idiom

public class Singleton {
    
	private Singleton() {
	}
	
	private static class SingletonHolder {
        private static final Singleton HOLDER_INSTANCE = new Singleton();
    }
        
    public static Singleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
