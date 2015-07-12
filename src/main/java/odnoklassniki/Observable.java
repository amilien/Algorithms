package odnoklassniki;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Observable {

	// use weak references for the case when developer forgets to call removeListener
    private ArrayList<WeakReference<BasicListener>> listenersList = new ArrayList<WeakReference<BasicListener>>();
    
    public interface BasicListener {
        void onChanged(Observable observable);
    }

    class MyListener implements BasicListener {

        public void onChanged(Observable observable) {
            observable.removeListener(this);
        }
    }

    public synchronized void addListener(BasicListener l) {
    	listenersList.add(new WeakReference<BasicListener>(l));
    }
    
    public synchronized void removeListener(BasicListener l) {
    	listenersList.remove(l);
    }
    
    protected synchronized void notifyOnChanged() {
    	// create a copy of collection to avoid ConcurrentModificationException
    	List<WeakReference<BasicListener>> duplicateList = new ArrayList<WeakReference<BasicListener>>(listenersList);
    	for (int i = 0; i < duplicateList.size(); i++) {
    		((BasicListener) duplicateList.get(i)).onChanged(this);
    	}
    }
}

