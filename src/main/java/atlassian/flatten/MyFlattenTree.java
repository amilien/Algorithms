package atlassian.flatten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFlattenTree<T> implements FlattenTree<T> {
	
    private final Function<T, T> mMyLeftFunction = new Function<T, T>() {
        @Override
        public T apply(final T t) {
            return t;
        }
    };
    
	private final Function<Triple<Tree<T>>, List<T>> mMyRightFunction = new Function<Triple<Tree<T>>, List<T>>() {
        @Override
        public List<T> apply(final Triple<Tree<T>> t) {
            final List<T> res = new ArrayList<T>();
            res.addAll(flattenInOrder(t.left()));
            res.addAll(flattenInOrder(t.middle()));
            res.addAll(flattenInOrder(t.right()));
            return res;
        }
    };

    public List<T> flattenInOrder(final Tree<T> tree) {
    	if (tree == null)
            throw new IllegalArgumentException("Error: tree is null.");
        Either<T, Triple<Tree<T>>> either = tree.get();
        if (either.isLeft())
            return Arrays.asList(either.ifLeft(mMyLeftFunction));
        else
        	return either.ifRight(mMyRightFunction);
    }

}
